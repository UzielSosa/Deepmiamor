package utils.exercises;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ex4RandomElipses extends JPanel {
    private final Random random = new Random();
    private Timer timer;

    public Ex4RandomElipses() {
        timer = new Timer(1000, e -> repaint());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dibujar 10 elipses aleatorias
        for (int i = 0; i < 10; i++) {
            int width = random.nextInt(100) + 20;
            int height = random.nextInt(100) + 20;
            int x = random.nextInt(getWidth() - width);
            int y = random.nextInt(getHeight() - height);

            Color color = new Color(
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256)
            );

            g2d.setColor(color);
            g2d.drawOval(x, y, width, height);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
}