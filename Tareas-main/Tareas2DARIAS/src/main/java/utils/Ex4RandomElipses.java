package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;

public class Ex4RandomElipses extends JPanel {
    private static final int NUM_ELIPSES = 50;
    private static final int MAX_SIZE = 100;
    private static final int MIN_SIZE = 20;

    private Ellipse2D[] elipses = new Ellipse2D[NUM_ELIPSES];
    private Color[] colores = new Color[NUM_ELIPSES];
    private Random random = new Random();

    public Ex4RandomElipses() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
        generarElipses();

        // Botón para regenerar
        JButton regenerateBtn = new JButton("Generar Nuevas Elipses");
        regenerateBtn.addActionListener(e -> {
            generarElipses();
            repaint();
        });

        setLayout(new BorderLayout());
        add(regenerateBtn, BorderLayout.SOUTH);
    }

    private void generarElipses() {
        for (int i = 0; i < NUM_ELIPSES; i++) {
            int width = MIN_SIZE + random.nextInt(MAX_SIZE - MIN_SIZE);
            int height = MIN_SIZE + random.nextInt(MAX_SIZE - MIN_SIZE);
            int x = random.nextInt(getPreferredSize().width - width);
            int y = random.nextInt(getPreferredSize().height - height);

            elipses[i] = new Ellipse2D.Double(x, y, width, height);
            colores[i] = new Color(
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256),
                    100 + random.nextInt(156) // Alpha entre 100-255
            );
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Configuración de calidad
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar todas las elipses
        for (int i = 0; i < NUM_ELIPSES; i++) {
            g2d.setColor(colores[i]);
            g2d.fill(elipses[i]);

            // Borde opcional
            g2d.setColor(colores[i].darker());
            g2d.draw(elipses[i]);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ejercicio 4: Elipses Aleatorias");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Ex4RandomElipses());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}