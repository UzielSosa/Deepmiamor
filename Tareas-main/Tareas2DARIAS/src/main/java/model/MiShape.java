package model;

import java.awt.*;
import java.awt.Shape; // Esta es la interfaz de AWT

/**
 * Representa una forma geométrica personalizada.
 * Se renombra a "MiShape" para evitar colisión.
 */
public class MiShape {
    private final Shape forma; // java.awt.Shape
    private final Color color;
    private final float grosor;
    private final boolean relleno;

    public MiShape(Shape forma, Color color, float grosor, boolean relleno) {
        this.forma = forma;
        this.color = color;
        this.grosor = grosor;
        this.relleno = relleno;
    }

    // Getters
    public Shape getForma() { return forma; }
    public Color getColor() { return color; }
    public float getGrosor() { return grosor; }
    public boolean isRelleno() { return relleno; }
}