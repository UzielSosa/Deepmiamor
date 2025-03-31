package utils.exercises;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ex6RandomShapes extends JPanel {
    private final Random random = new Random();
    private Timer timer;

    public Ex6RandomShapes() {
        timer = new Timer(800, e -> repaint());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dibujar 5 formas aleatorias
        for (int i = 0; i < 5; i++) {
            Color color = new Color(
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256)
            );
            g2d.setColor(color);

            int shapeType = random.nextInt(3);
            int x = random.nextInt(getWidth() - 100);
            int y = random.nextInt(getHeight() - 100);
            int width = random.nextInt(100) + 30;
            int height = random.nextInt(100) + 30;

            switch (shapeType) {
                case 0:
                    g2d.fillRect(x, y, width, height);
                    break;
                case 1:
                    g2d.fillOval(x, y, width, height);
                    break;
                case 2:
                    int[] xPoints = {x, x + width/2, x + width};
                    int[] yPoints = {y + height, y, y + height};
                    g2d.fillPolygon(xPoints, yPoints, 3);
                    break;
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }
}