package utils.exercises;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class Ex5RectSlider extends JPanel {
    private int rectSize = 50;
    private Color rectColor = Color.RED;

    public Ex5RectSlider() {
        // Slider para tamaño
        JSlider sizeSlider = new JSlider(10, 200, 50);
        sizeSlider.addChangeListener(e -> {
            rectSize = sizeSlider.getValue();
            repaint();
        });

        // Selector de color
        JButton colorButton = new JButton("Cambiar Color");
        colorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Elige color", rectColor);
            if (newColor != null) {
                rectColor = newColor;
                repaint();
            }
        });

        // Panel de controles
        JPanel controlPanel = new JPanel(new GridLayout(2, 1));
        controlPanel.add(sizeSlider);
        controlPanel.add(colorButton);

        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Centrar el rectángulo
        int x = getWidth()/2 - rectSize/2;
        int y = getHeight()/2 - rectSize/2;

        g2d.setColor(rectColor);
        g2d.fillRect(x, y, rectSize, rectSize);
    }
}