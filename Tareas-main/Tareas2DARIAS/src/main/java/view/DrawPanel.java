package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;

public class DrawPanel extends JPanel {
    private Color colorActual = Color.BLACK;
    private int grosorTrazo = 3;
    private boolean relleno = false;
    private String herramientaActual = "LÍNEA";
    private Point puntoInicial;
    private Shape formaActual;
    private ArrayList<Shape> formas = new ArrayList<>();
    private ArrayList<Color> colores = new ArrayList<>();
    private ArrayList<Integer> grosores = new ArrayList<>();
    private ArrayList<Boolean> rellenos = new ArrayList<>();

    public DrawPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));

        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                puntoInicial = e.getPoint();
                formaActual = crearForma(e.getPoint());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                formaActual = crearForma(e.getPoint());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                formas.add(formaActual);
                colores.add(colorActual);
                grosores.add(grosorTrazo);
                rellenos.add(relleno);
                formaActual = null;
                repaint();
            }
        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    private Shape crearForma(Point puntoFinal) {
        int x = Math.min(puntoInicial.x, puntoFinal.x);
        int y = Math.min(puntoInicial.y, puntoFinal.y);
        int width = Math.abs(puntoFinal.x - puntoInicial.x);
        int height = Math.abs(puntoFinal.y - puntoInicial.y);

        switch (herramientaActual) {
            case "LÍNEA":
                return new Line2D.Double(puntoInicial, puntoFinal);
            case "RECTÁNGULO":
                return new Rectangle2D.Double(x, y, width, height);
            case "ELIPSE":
                return new Ellipse2D.Double(x, y, width, height);
            case "TRIÁNGULO":
                Path2D triangulo = new Path2D.Double();
                triangulo.moveTo(puntoInicial.x, puntoFinal.y);
                triangulo.lineTo(puntoFinal.x, puntoFinal.y);
                triangulo.lineTo((puntoInicial.x + puntoFinal.x)/2, puntoInicial.y);
                triangulo.closePath();
                return triangulo;
            case "ESTRELLA":
                return crearEstrella(puntoInicial, puntoFinal);
            case "DIBUJO LIBRE":
                if (formaActual == null) {
                    Path2D path = new Path2D.Double();
                    path.moveTo(puntoInicial.x, puntoInicial.y);
                    return path;
                } else {
                    ((Path2D)formaActual).lineTo(puntoFinal.x, puntoFinal.y);
                    return formaActual;
                }
            default:
                return new Rectangle2D.Double(x, y, width, height);
        }
    }

    private Shape crearEstrella(Point centro, Point radio) {
        int puntas = 5;
        double radioExterno = centro.distance(radio);
        double radioInterno = radioExterno / 2.5;

        Path2D estrella = new Path2D.Double();

        for (int i = 0; i < puntas * 2; i++) {
            double angulo = i * Math.PI / puntas;
            double radioActual = (i % 2 == 0) ? radioExterno : radioInterno;
            double x = centro.x + radioActual * Math.cos(angulo - Math.PI/2);
            double y = centro.y + radioActual * Math.sin(angulo - Math.PI/2);

            if (i == 0) {
                estrella.moveTo(x, y);
            } else {
                estrella.lineTo(x, y);
            }
        }
        estrella.closePath();
        return estrella;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Configurar calidad de renderizado
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar todas las formas guardadas
        for (int i = 0; i < formas.size(); i++) {
            g2d.setColor(colores.get(i));
            g2d.setStroke(new BasicStroke(grosores.get(i)));

            if (rellenos.get(i)) {
                g2d.fill(formas.get(i));
            } else {
                g2d.draw(formas.get(i));
            }
        }

        // Dibujar la forma actual (si existe)
        if (formaActual != null) {
            g2d.setColor(colorActual);
            g2d.setStroke(new BasicStroke(grosorTrazo));

            if (relleno) {
                g2d.fill(formaActual);
            } else {
                g2d.draw(formaActual);
            }
        }
    }

    // Métodos requeridos por ToolbarPanel
    public void setHerramientaActual(String herramienta) {
        this.herramientaActual = herramienta;
    }

    public Color getColorActual() {
        return colorActual;
    }

    public void setColorActual(Color color) {
        this.colorActual = color;
    }

    public void setGrosorTrazo(int grosor) {
        this.grosorTrazo = grosor;
    }

    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    public void limpiarLienzo() {
        formas.clear();
        colores.clear();
        grosores.clear();
        rellenos.clear();
        repaint();
    }
}