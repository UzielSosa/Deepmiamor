package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Demo2D extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final Color BG_COLOR = new Color(240, 240, 240);

    private final Random random = new Random();

    public Demo2D() {
        // Establecemos el tamaño preferido del panel
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Llama al método de la superclase para limpiar el panel
        Graphics2D g2d = (Graphics2D) g;
        // Activa el renderizado para lograr gráficos más suaves
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Dibuja componentes
        drawGrid(g2d, 50, Color.LIGHT_GRAY); // Usa un tamaño de celda de 50 píxeles
        drawBasicShapes(g2d);               // Dibuja formas básicas
        drawTransformations(g2d);           // Dibuja con transformaciones
        drawTransparencyDemo(g2d);          // Transparencias
        drawTextDemo(g2d);                  // Ejemplo de texto
    }

    private void drawGrid(Graphics2D g2d, int spacing, Color color) {
        g2d.setColor(color);
        for (int x = 0; x < getWidth(); x += spacing) {
            g2d.drawLine(x, 0, x, getHeight());
        }
        for (int y = 0; y < getHeight(); y += spacing) {
            g2d.drawLine(0, y, getWidth(), y);
        }
    }

    private void drawBasicShapes(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect(100, 100, 100, 100); // Rectángulo Azul
        g2d.setColor(Color.RED);
        g2d.fillOval(250, 100, 100, 100); // Círculo Rojo
        g2d.setColor(Color.GREEN);
        g2d.drawLine(400, 100, 500, 200); // Línea Verde
    }

    private TexturePaint createTexture() {
        int tileSize = 10;
        BufferedImage image = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.YELLOW);
        g2.fillRect(0, 0, tileSize, tileSize);
        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, tileSize - 1, tileSize - 1);
        g2.dispose();
        return new TexturePaint(image, new Rectangle(0, 0, tileSize, tileSize));
    }

    private void drawTransformations(Graphics2D g2d) {
        g2d.setColor(Color.MAGENTA);
        AffineTransform originalTransform = g2d.getTransform();
        g2d.translate(150, 300);
        g2d.rotate(Math.toRadians(45));
        g2d.scale(1.5, 1.5);
        g2d.fillRect(-25, -25, 50, 50); // Cuadrado con transformaciones
        g2d.setTransform(originalTransform);
    }

    private void drawTransparencyDemo(Graphics2D g2d) {
        g2d.setColor(Color.CYAN);
        g2d.fillRect(500, 300, 100, 100);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(550, 350, 100, 100);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }

    private void drawTextDemo(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Serif", Font.BOLD, 24));
        g2d.drawString("Ejemplo de Texto", 100, 500);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Demo 2D");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Demo2D());
            frame.pack(); // Toma el tamaño preferido del componente
            frame.setLocationRelativeTo(null); // Centra la ventana
            frame.setVisible(true); // Muestra la ventana
        });
    }
}