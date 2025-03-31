package model;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para manejar variables globales del proyecto.
 */
public class GV {
    // === CONFIGURACIÓN DE DIBUJO === //
    public static Color COLOR_ACTUAL = Color.BLACK;
    public static float GROSOR_TRAZO = 1.0f;
    public static boolean RELLENO_ACTIVO = false;

    // === LISTA DE FORMAS DIBUJADAS === //
    public static List<Shape> SHAPES = new ArrayList<>();

    // === CONFIGURACIÓN DE INTERFAZ === //
    public static int ANCHO_LIENZO = 800;
    public static int ALTO_LIENZO = 600;

    // === ESTADO DE LA APLICACIÓN === //
    public static String FIGURA_ACTUAL = "LINEA"; // Valores: "LINEA", "ELIPSE", etc.
    public static String MODO = "DIBUJAR"; // "DIBUJAR" o "SELECCIONAR"

    // === MÉTODOS UTILITARIOS === //

    /**
     * Limpia todas las formas del lienzo.
     */
    public static void limpiarLienzo() {
        SHAPES.clear();
    }

    /**
     * Cambia el color actual.
     */
    public static void setColor(Color color) {
        COLOR_ACTUAL = color;
    }

    /**
     * Añade una nueva forma a la lista.
     */
    public static void agregarShape(Shape shape) {
        SHAPES.add(shape);
    }
}