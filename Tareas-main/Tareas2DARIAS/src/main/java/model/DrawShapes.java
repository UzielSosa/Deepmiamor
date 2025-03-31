package model;

import java.awt.*;
import java.awt.geom.*;

public class DrawShapes {

    // === FIGURAS BÁSICAS === //

    /**
     * Dibuja una línea entre dos puntos.
     * @param g2d       Objeto Graphics2D.
     * @param x1, y1    Punto inicial.
     * @param x2, y2    Punto final.
     * @param color     Color de la línea.
     * @param grosor    Grosor del trazo (px).
     */
    public static void dibujarLinea(Graphics2D g2d, int x1, int y1, int x2, int y2, Color color, float grosor) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(grosor));
        g2d.draw(new Line2D.Double(x1, y1, x2, y2));
    }

    /**
     * Dibuja una elipse o círculo.
     * @param relleno   Si es true, rellena la figura.
     */
    public static void dibujarElipse(Graphics2D g2d, int x, int y, int width, int height, Color color, boolean relleno) {
        g2d.setColor(color);
        Ellipse2D elipse = new Ellipse2D.Double(x, y, width, height);
        if (relleno) g2d.fill(elipse);
        else g2d.draw(elipse);
    }

    /**
     * Dibuja un rectángulo.
     */
    public static void dibujarRectangulo(Graphics2D g2d, int x, int y, int width, int height, Color color, boolean relleno) {
        g2d.setColor(color);
        Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
        if (relleno) g2d.fill(rect);
        else g2d.draw(rect);
    }

    // === FIGURAS COMPLEJAS === //

    /**
     * Dibuja una estrella con puntas personalizadas.
     * @param puntas    Número de puntas (ej: 5).
     * @param radio     Radio externo.
     * @param radioInt  Radio interno (para estrella hueca).
     */
    public static void dibujarEstrella(Graphics2D g2d, int x, int y, int puntas, int radio, int radioInt, Color color) {
        GeneralPath star = new GeneralPath();
        double angulo = Math.PI / puntas;

        for (int i = 0; i < 2 * puntas; i++) {
            double r = (i % 2 == 0) ? radio : radioInt;
            double theta = i * angulo;
            double px = x + r * Math.sin(theta);
            double py = y + r * Math.cos(theta);

            if (i == 0) star.moveTo(px, py);
            else star.lineTo(px, py);
        }
        star.closePath();

        g2d.setColor(color);
        g2d.fill(star);
    }

    /**
     * Dibuja un triángulo equilátero.
     */
    public static void dibujarTriangulo(Graphics2D g2d, int x, int y, int base, int altura, Color color, boolean relleno) {
        int[] xPoints = {x, x + base, x + base / 2};
        int[] yPoints = {y, y, y - altura};

        g2d.setColor(color);
        if (relleno) g2d.fillPolygon(xPoints, yPoints, 3);
        else g2d.drawPolygon(xPoints, yPoints, 3);
    }

    /**
     * Dibuja un polígono regular.
     * @param lados     Número de lados (ej: 6 para hexágono).
     */
    public static void dibujarPoligono(Graphics2D g2d, int x, int y, int lados, int radio, Color color, boolean relleno) {
        Polygon poligono = new Polygon();
        double angulo = 2 * Math.PI / lados;

        for (int i = 0; i < lados; i++) {
            int px = x + (int) (radio * Math.cos(i * angulo));
            int py = y + (int) (radio * Math.sin(i * angulo));
            poligono.addPoint(px, py);
        }

        g2d.setColor(color);
        if (relleno) g2d.fill(poligono);
        else g2d.draw(poligono);
    }

    /**
     * Dibuja un corazón usando GeneralPath.
     */
    public static void dibujarCorazon(Graphics2D g2d, int x, int y, int size, Color color) {
        GeneralPath corazon = new GeneralPath();
        corazon.moveTo(x, y + size / 4);
        corazon.curveTo(
                x, y,
                x - size / 2, y,
                x - size / 2, y + size / 4
        );
        corazon.curveTo(
                x - size / 2, y + size / 2,
                x, y + size,
                x, y + size
        );
        corazon.curveTo(
                x, y + size,
                x + size / 2, y + size / 2,
                x + size / 2, y + size / 4
        );
        corazon.curveTo(
                x + size / 2, y,
                x, y,
                x, y + size / 4
        );
        corazon.closePath();

        g2d.setColor(color);
        g2d.fill(corazon);
    }

    // === MÉTODOS AUXILIARES === //

    /**
     * Limpia el lienzo (fondo blanco).
     */
    public static void limpiarLienzo(Graphics2D g2d, int width, int height) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
    }
}