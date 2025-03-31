package utils;

import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Hello2DV2 {
    /**
     * Dibuja texto con efectos avanzados (versión mejorada de Hello2D)
     * @param g2d Objeto Graphics2D
     * @param text Texto a renderizar
     * @param x Posición X
     * @param y Posición Y
     * @param style Estilo (NORMAL, BOLD, ITALIC)
     * @param size Tamaño de fuente
     * @param color Color principal
     * @param effect Efecto (NONE, SHADOW, OUTLINE, GRADIENT)
     */
    public static void drawEnhancedText(Graphics2D g2d, String text, float x, float y,
                                        int style, float size, Color color,
                                        TextEffect effect) {

        // Configuración inicial
        Font font = new Font("Arial", style, (int)size);
        g2d.setFont(font);

        // Aplicar efectos según el tipo
        switch(effect) {
            case SHADOW:
                drawWithShadow(g2d, text, x, y, color, font);
                break;

            case OUTLINE:
                drawWithOutline(g2d, text, x, y, color, font);
                break;

            case GRADIENT:
                drawWithGradient(g2d, text, x, y, font);
                break;

            default: // NONE
                g2d.setColor(color);
                g2d.drawString(text, x, y);
        }
    }

    private static void drawWithShadow(Graphics2D g2d, String text, float x, float y,
                                       Color color, Font font) {
        // Dibujar sombra
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.drawString(text, x + 2, y + 2);

        // Dibujar texto principal
        g2d.setColor(color);
        g2d.drawString(text, x, y);
    }

    private static void drawWithOutline(Graphics2D g2d, String text, float x, float y,
                                        Color color, Font font) {
        // Crear forma del texto
        TextLayout textLayout = new TextLayout(text, font, g2d.getFontRenderContext());
        Shape outline = textLayout.getOutline(AffineTransform.getTranslateInstance(x, y));

        // Dibujar borde
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.setColor(Color.BLACK);
        g2d.draw(outline);

        // Rellenar texto
        g2d.setColor(color);
        g2d.fill(outline);
    }

    private static void drawWithGradient(Graphics2D g2d, String text, float x, float y,
                                         Font font) {
        // Crear gradiente
        GradientPaint gradient = new GradientPaint(
                x, y, Color.RED,
                x + 100, y + 30, Color.BLUE,
                true // Ciclico
        );

        // Aplicar gradiente
        g2d.setPaint(gradient);
        g2d.drawString(text, x, y);
    }

    /**
     * Renderiza texto en una imagen BufferedImage
     */
    public static BufferedImage renderToImage(String text, int width, int height,
                                              int style, float size, Color color,
                                              TextEffect effect) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // Fondo transparente
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, width, height);
        g2d.setComposite(AlphaComposite.SrcOver);

        // Dibujar texto centrado
        Font font = new Font("Arial", style, (int)size);
        FontMetrics fm = g2d.getFontMetrics(font);
        float x = (width - fm.stringWidth(text)) / 2f;
        float y = (height - fm.getHeight()) / 2f + fm.getAscent();

        drawEnhancedText(g2d, text, x, y, style, size, color, effect);
        g2d.dispose();

        return image;
    }

    public enum TextEffect {
        NONE, SHADOW, OUTLINE, GRADIENT
    }
}