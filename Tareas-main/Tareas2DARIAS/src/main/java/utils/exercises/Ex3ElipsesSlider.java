package utils.exercises;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class Ex3ElipsesSlider extends JPanel {
    private int ellipseWidth = 100;
    private int ellipseHeight = 60;

    public Ex3ElipsesSlider() {
        // Slider para el ancho
        JSlider widthSlider = new JSlider(10, 300, 100);
        widthSlider.addChangeListener(e -> {
            ellipseWidth = widthSlider.getValue();
            repaint();
        });

        // Slider para el alto
        JSlider heightSlider = new JSlider(10, 300, 60);
        heightSlider.addChangeListener(e -> {
            ellipseHeight = heightSlider.getValue();
            repaint();
        });

        // Panel de controles
        JPanel controlPanel = new JPanel(new GridLayout(2, 1));
        controlPanel.add(new JLabel("Ancho:"));
        controlPanel.add(widthSlider);
        controlPanel.add(new JLabel("Alto:"));
        controlPanel.add(heightSlider);

        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Centrar la elipse
        int x = getWidth()/2 - ellipseWidth/2;
        int y = getHeight()/2 - ellipseHeight/2;

        g2d.setColor(Color.BLUE);
        g2d.drawOval(x, y, ellipseWidth, ellipseHeight);
    }
}