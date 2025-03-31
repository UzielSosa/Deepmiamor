package Main;

import view.DrawPanel;
import view.ToolbarPanel;
import utils.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Configurar ventana principal
            JFrame frame = new JFrame("PaintFJMP - Proyecto de Gráficos 2D");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // 2. Inicializar componentes principales
            DrawPanel drawPanel = new DrawPanel();
            ToolbarPanel toolbarPanel = new ToolbarPanel(drawPanel);

            // 3. Configurar menú superior
            JMenuBar menuBar = createMenuBar(drawPanel);
            frame.setJMenuBar(menuBar);

            // 4. Ensamblar interfaz
            frame.add(toolbarPanel, BorderLayout.WEST);
            frame.add(drawPanel, BorderLayout.CENTER);

            // 5. Mostrar ventana
            frame.pack();
            frame.setMinimumSize(new Dimension(1000, 700));
            frame.setLocationRelativeTo(null); // Centrar
            frame.setVisible(true);
        });
    }

    private static JMenuBar createMenuBar(DrawPanel drawPanel) {
        JMenuBar menuBar = new JMenuBar();

        // Menú Archivo
        JMenu fileMenu = new JMenu("Archivo");
        JMenuItem newItem = new JMenuItem("Nuevo");
        newItem.addActionListener(e -> drawPanel.limpiarLienzo());

        JMenuItem saveItem = new JMenuItem("Guardar");
        saveItem.addActionListener(e -> {
            // Lógica para guardar imagen
            JOptionPane.showMessageDialog(null, "Funcionalidad en desarrollo");
        });

        JMenuItem exitItem = new JMenuItem("Salir");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Menú Herramientas
        JMenu toolsMenu = new JMenu("Herramientas");
        JMenuItem spiroItem = new JMenuItem("Espirógrafo");
        spiroItem.addActionListener(e -> openUtility(new Spirograph()));

        JMenuItem demoItem = new JMenuItem("Demo 2D");
        demoItem.addActionListener(e -> openUtility(new Demo2D()));

        toolsMenu.add(spiroItem);
        toolsMenu.add(demoItem);

        // Menú Ayuda
        JMenu helpMenu = new JMenu("Ayuda");
        JMenuItem aboutItem = new JMenuItem("Acerca de");
        aboutItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                    "PaintFJMP v1.0\nProyecto de Gráficos por Computadora\n© 2023",
                    "Acerca de",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        helpMenu.add(aboutItem);

        // Ensamblar menú
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private static void openUtility(JPanel utilityPanel) {
        JDialog dialog = new JDialog();
        dialog.setTitle(utilityPanel.getClass().getSimpleName());
        dialog.add(utilityPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}