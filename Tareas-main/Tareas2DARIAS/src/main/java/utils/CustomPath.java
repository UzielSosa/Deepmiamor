package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

public class CustomPath extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final Color BG_COLOR = Color.WHITE;

    private List<List<Point2D>> paths = new ArrayList<>();
    private List<Point2D> currentPath = new ArrayList<>();
    private Color currentColor = Color.BLUE;
    private float strokeWidth = 3.0f;
    private boolean isDrawing = false;

    public CustomPath() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(BG_COLOR);
        setupMouseListeners();
        setupControls();
    }

    private void setupMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    isDrawing = true;
                    currentPath = new ArrayList<>();
                    currentPath.add(e.getPoint());
                    paths.add(currentPath);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDrawing = false;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDrawing && SwingUtilities.isLeftMouseButton(e)) {
                    currentPath.add(e.getPoint());
                    repaint();
                }
            }
        });
    }

    private void setupControls() {
        JPanel controlPanel = new JPanel();

        // Selector de color
        JButton colorBtn = new JButton("Color");
        colorBtn.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Elige Color", currentColor);
            if (newColor != null) {
                currentColor = newColor;
            }
        });

        // Control de grosor
        JSlider widthSlider = new JSlider(1, 20, (int)strokeWidth);
        widthSlider.addChangeListener(e -> {
            strokeWidth = widthSlider.getValue();
        });

        // Botón de limpiar
        JButton clearBtn = new JButton("Limpiar");
        clearBtn.addActionListener(e -> {
            paths.clear();
            repaint();
        });

        controlPanel.add(colorBtn);
        controlPanel.add(new JLabel("Grosor:"));
        controlPanel.add(widthSlider);
        controlPanel.add(clearBtn);

        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Configuración de calidad
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar todos los caminos
        for (List<Point2D> path : paths) {
            if (path.size() > 1) {
                drawPath(g2d, path);
            }
        }
    }

    private void drawPath(Graphics2D g2d, List<Point2D> path) {
        g2d.setColor(currentColor);
        g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        Path2D path2D = new Path2D.Double();
        path2D.moveTo(path.get(0).getX(), path.get(0).getY());

        for (int i = 1; i < path.size(); i++) {
            path2D.lineTo(path.get(i).getX(), path.get(i).getY());
        }

        g2d.draw(path2D);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Custom Path - Dibujo Libre");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new CustomPath());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}