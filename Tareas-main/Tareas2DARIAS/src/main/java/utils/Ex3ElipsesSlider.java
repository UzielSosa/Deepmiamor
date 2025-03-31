package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Ex3ElipsesSlider extends JPanel {
    private static final int MIN_RADIO = 10;
    private static final int MAX_RADIO = 100;
    private static final int RADIO_INICIAL = 50;

    private int radioX = RADIO_INICIAL;
    private int radioY = RADIO_INICIAL;
    private Color color = Color.BLUE;

    public Ex3ElipsesSlider() {
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Panel de control
        JPanel controlPanel = new JPanel();

        // Slider para radio X
        JSlider sliderX = new JSlider(MIN_RADIO, MAX_RADIO, RADIO_INICIAL);
        sliderX.addChangeListener(e -> {
            radioX = sliderX.getValue();
            repaint();
        });

        // Slider para radio Y
        JSlider sliderY = new JSlider(MIN_RADIO, MAX_RADIO, RADIO_INICIAL);
        sliderY.addChangeListener(e -> {
            radioY = sliderY.getValue();
            repaint();
        });

        // Selector de color
        JButton colorButton = new JButton("Cambiar Color");
        colorButton.addActionListener(e -> {
            Color nuevoColor = JColorChooser.showDialog(this, "Seleccionar Color", color);
            if (nuevoColor != null) {
                color = nuevoColor;
                repaint();
            }
        });

        // Configuración de los sliders
        sliderX.setMajorTickSpacing(20);
        sliderX.setMinorTickSpacing(5);
        sliderX.setPaintTicks(true);
        sliderX.setPaintLabels(true);

        sliderY.setMajorTickSpacing(20);
        sliderY.setMinorTickSpacing(5);
        sliderY.setPaintTicks(true);
        sliderY.setPaintLabels(true);

        // Añadir componentes al panel de control
        controlPanel.add(new JLabel("Radio X:"));
        controlPanel.add(sliderX);
        controlPanel.add(new JLabel("Radio Y:"));
        controlPanel.add(sliderY);
        controlPanel.add(colorButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Configurar renderizado de calidad
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Calcular posición centrada
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Dibujar elipse
        Ellipse2D elipse = new Ellipse2D.Double(
                centerX - radioX,
                centerY - radioY,
                radioX * 2,
                radioY * 2
        );

        g2d.setColor(color);
        g2d.fill(elipse);

        // Dibujar ejes de referencia
        g2d.setColor(Color.BLACK);
        g2d.drawLine(centerX - radioX - 20, centerY, centerX + radioX + 20, centerY);
        g2d.drawLine(centerX, centerY - radioY - 20, centerX, centerY + radioY + 20);

        // Mostrar valores actuales
        g2d.drawString(String.format("Radio X: %d", radioX), 20, 20);
        g2d.drawString(String.format("Radio Y: %d", radioY), 20, 40);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ejercicio 3: Elipses con Sliders");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Ex3ElipsesSlider());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}