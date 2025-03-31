package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

public class Spirograph extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final Color BG_COLOR = Color.BLACK;

    // Parámetros del espirógrafo
    private int R = 150;  // Radio fijo
    private int r = 60;   // Radio móvil
    private int d = 80;   // Distancia del punto
    private double angle = 0;
    private List<Point2D> points = new ArrayList<>();
    private Color drawingColor = Color.CYAN;

    // Controles UI
    private JSlider sliderR;
    private JSlider sliderr;
    private JSlider sliderd;
    private JButton colorBtn;

    public Spirograph() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(BG_COLOR);
        initControls();
        startAnimation();
    }

    private void initControls() {
        // Panel de controles
        JPanel controlPanel = new JPanel(new GridLayout(4, 1));

        sliderR = createSlider("Radio Fijo (R)", 50, 300, R);
        sliderr = createSlider("Radio Móvil (r)", 10, 150, r);
        sliderd = createSlider("Distancia (d)", 10, 200, d);

        colorBtn = new JButton("Cambiar Color");
        colorBtn.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Elige Color", drawingColor);
            if (newColor != null) {
                drawingColor = newColor;
                repaint();
            }
        });

        controlPanel.add(sliderR);
        controlPanel.add(sliderr);
        controlPanel.add(sliderd);
        controlPanel.add(colorBtn);

        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.SOUTH);
    }

    private JSlider createSlider(String label, int min, int max, int value) {
        JSlider slider = new JSlider(min, max, value);
        slider.setMajorTickSpacing((max - min)/5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e -> {
            R = sliderR.getValue();
            r = sliderr.getValue();
            d = sliderd.getValue();
            points.clear(); // Reiniciar dibujo al cambiar parámetros
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(label), BorderLayout.NORTH);
        panel.add(slider, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        return slider;
    }

    private void startAnimation() {
        Timer timer = new Timer(10, e -> {
            angle += 0.05;
            updateSpirograph();
            repaint();
        });
        timer.start();
    }

    private void updateSpirograph() {
        // Ecuaciones paramétricas del espirógrafo
        double k = (double) r / R;
        double l = (double) d / r;

        double x = (R - r) * Math.cos(angle) + d * Math.cos((1 - k) * angle);
        double y = (R - r) * Math.sin(angle) - d * Math.sin((1 - k) * angle);

        // Centrar en el panel
        Point2D point = new Point2D.Double(
                WIDTH / 2 + x,
                HEIGHT / 2 + y
        );

        points.add(point);

        // Limitar cantidad de puntos para rendimiento
        if (points.size() > 2000) {
            points.remove(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Configuración de calidad
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar la curva
        g2d.setColor(drawingColor);
        if (points.size() > 1) {
            Path2D path = new Path2D.Double();
            path.moveTo(points.get(0).getX(), points.get(0).getY());

            for (int i = 1; i < points.size(); i++) {
                path.lineTo(points.get(i).getX(), points.get(i).getY());
            }

            g2d.draw(path);
        }

        // Dibujar círculos guía (opcional)
        drawGuideCircles(g2d);
    }

    private void drawGuideCircles(Graphics2D g2d) {
        // Círculo fijo
        g2d.setColor(new Color(255, 255, 255, 50));
        g2d.drawOval(
                WIDTH/2 - R,
                HEIGHT/2 - R,
                R*2,
                R*2
        );

        // Círculo móvil (posición actual)
        double mobileX = WIDTH/2 + (R - r) * Math.cos(angle);
        double mobileY = HEIGHT/2 + (R - r) * Math.sin(angle);

        g2d.setColor(new Color(255, 0, 0, 100));
        g2d.drawOval(
                (int)(mobileX - r),
                (int)(mobileY - r),
                r*2,
                r*2
        );

        // Punto de dibujo
        g2d.setColor(Color.YELLOW);
        if (!points.isEmpty()) {
            Point2D lastPoint = points.get(points.size() - 1);
            g2d.fillOval((int)lastPoint.getX() - 3, (int)lastPoint.getY() - 3, 6, 6);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Espirógrafo Matemático");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Spirograph());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}