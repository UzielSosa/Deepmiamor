package utils.exercises;

import javax.swing.*;
import java.awt.*;

public class BasicShapesPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Configurar renderizado suave
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar formas básicas con diferentes colores
        g2d.setColor(Color.RED);
        g2d.fillRect(50, 50, 100, 80);

        g2d.setColor(Color.BLUE);
        g2d.fillOval(200, 50, 100, 80);

        g2d.setColor(Color.GREEN);
        g2d.fillRoundRect(350, 50, 100, 80, 20, 20);

        g2d.setColor(Color.MAGENTA);
        int[] xPoints = {50, 150, 100};
        int[] yPoints = {200, 200, 150};
        g2d.fillPolygon(xPoints, yPoints, 3);

        g2d.setColor(Color.BLACK);
        g2d.drawString("Formas Básicas en Java 2D", 50, 300);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 350);
    }
}