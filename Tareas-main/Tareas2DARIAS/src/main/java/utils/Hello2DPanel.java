package utils;
import javax.swing.*;
import java.awt.*;

public class Hello2DPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Ejemplo de dibujo básico
        g2d.setColor(Color.BLUE);
        g2d.drawString("Hello 2D!", 50, 50);
        g2d.drawRect(30, 30, 100, 60);
    }
}