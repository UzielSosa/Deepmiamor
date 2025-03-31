package view;

import view.DrawPanel;
import utils.Demo2D;
import utils.Hello2DPanel;
import utils.Spirograph;
import utils.exercises.Ex3ElipsesSlider;
import utils.exercises.Ex4RandomElipses;
import utils.exercises.Ex5RectSlider;
import utils.exercises.Ex6RandomShapes;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class ToolbarPanel extends JPanel {
    private final DrawPanel drawPanel;
    private JToggleButton[] figuraButtons;
    private JButton colorButton;
    private JSlider grosorSlider;
    private JCheckBox rellenoCheckbox;
    private JButton limpiarButton;
    private JButton utilidadesButton;

    // Configuración de herramientas
    private static final String[] HERRAMIENTAS = {
            "LÍNEA", "RECTÁNGULO", "ELIPSE", "TRIÁNGULO", "ESTRELLA",
            "TEXTO", "DIBUJO LIBRE", "ESPIRÓGRAFO", "DEMO 2D"
    };

    public ToolbarPanel(DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(240, 240, 240));
        initComponents();
    }

    private void initComponents() {
        // Grupo para botones de herramientas
        ButtonGroup toolsGroup = new ButtonGroup();

        // Panel de herramientas con diseño mejorado
        JPanel toolsPanel = new JPanel();
        toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.Y_AXIS));
        toolsPanel.setBorder(new TitledBorder("Herramientas"));
        toolsPanel.setBackground(new Color(240, 240, 240));

        figuraButtons = new JToggleButton[HERRAMIENTAS.length];

        for (int i = 0; i < HERRAMIENTAS.length; i++) {
            figuraButtons[i] = createToolButton(HERRAMIENTAS[i]);

            final String herramienta = HERRAMIENTAS[i];
            figuraButtons[i].addActionListener(e -> {
                if (herramienta.equals("ESPIRÓGRAFO") || herramienta.equals("DEMO 2D")) {
                    abrirUtilidad(herramienta);
                    figuraButtons[0].setSelected(true);
                } else {
                    drawPanel.setHerramientaActual(herramienta);
                }
            });

            toolsGroup.add(figuraButtons[i]);
            toolsPanel.add(figuraButtons[i]);
            toolsPanel.add(Box.createVerticalStrut(5));
        }

        // Panel de estilo mejorado
        JPanel stylePanel = new JPanel();
        stylePanel.setLayout(new BoxLayout(stylePanel, BoxLayout.Y_AXIS));
        stylePanel.setBorder(new TitledBorder("Estilo"));
        stylePanel.setBackground(new Color(240, 240, 240));

        // Selector de color mejorado
        colorButton = new JButton("Color");
        styleButton(colorButton);
        colorButton.addActionListener(e -> {
            Color nuevoColor = JColorChooser.showDialog(
                    this, "Seleccionar Color", drawPanel.getColorActual());
            if (nuevoColor != null) {
                drawPanel.setColorActual(nuevoColor);
                updateColorButton(nuevoColor);
            }
        });

        // Control de grosor mejorado
        grosorSlider = new JSlider(1, 20, 3);
        styleSlider(grosorSlider);
        grosorSlider.addChangeListener(e ->
                drawPanel.setGrosorTrazo(grosorSlider.getValue())
        );

        // Checkbox de relleno mejorado
        rellenoCheckbox = new JCheckBox("Relleno");
        styleCheckbox(rellenoCheckbox);
        rellenoCheckbox.addActionListener(e ->
                drawPanel.setRelleno(rellenoCheckbox.isSelected())
        );

        // Botón de limpiar mejorado
        limpiarButton = new JButton("Limpiar Lienzo");
        styleButton(limpiarButton);
        limpiarButton.addActionListener(e -> drawPanel.limpiarLienzo());

        // Botón de utilidades avanzadas
        utilidadesButton = new JButton("Ejercicios 2D");
        styleButton(utilidadesButton);
        utilidadesButton.addActionListener(e -> mostrarMenuUtilidades());

        // Añadir componentes al panel de estilo
        stylePanel.add(Box.createVerticalStrut(5));
        stylePanel.add(colorButton);
        stylePanel.add(Box.createVerticalStrut(10));
        stylePanel.add(new JLabel("Grosor:"));
        stylePanel.add(grosorSlider);
        stylePanel.add(Box.createVerticalStrut(10));
        stylePanel.add(rellenoCheckbox);
        stylePanel.add(Box.createVerticalStrut(15));
        stylePanel.add(limpiarButton);
        stylePanel.add(Box.createVerticalStrut(15));
        stylePanel.add(utilidadesButton);

        // Añadir al panel principal
        add(toolsPanel);
        add(Box.createVerticalStrut(15));
        add(stylePanel);

        // Seleccionar primera herramienta por defecto
        if (figuraButtons.length > 0) {
            figuraButtons[0].setSelected(true);
            drawPanel.setHerramientaActual(HERRAMIENTAS[0]);
        }
    }

    private JToggleButton createToolButton(String text) {
        JToggleButton btn = new JToggleButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, btn.getPreferredSize().height));
        return btn;
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, btn.getPreferredSize().height));
    }

    private void styleSlider(JSlider slider) {
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBackground(new Color(240, 240, 240));
        slider.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void styleCheckbox(JCheckBox checkbox) {
        checkbox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        checkbox.setBackground(new Color(240, 240, 240));
        checkbox.setFocusPainted(false);
        checkbox.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void abrirUtilidad(String herramienta) {
        JDialog dialog = new JDialog();
        dialog.setTitle(herramienta);
        dialog.setLayout(new BorderLayout());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        try {
            switch (herramienta) {
                case "ESPIRÓGRAFO":
                    dialog.add(new Spirograph(), BorderLayout.CENTER);
                    break;
                case "DEMO 2D":
                    dialog.add(new Demo2D(), BorderLayout.CENTER);
                    break;
            }

            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al abrir " + herramienta + ": " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarMenuUtilidades() {
        JPopupMenu utilMenu = new JPopupMenu();
        utilMenu.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        // Ejercicios básicos
        JMenuItem helloItem = createMenuItem("Hello2D", e -> openUtilityDialog(new Hello2DPanel()));

        // Ejercicios con sliders
        JMenuItem sliderElipsesItem = createMenuItem("Control de Elipses", e -> openUtilityDialog(new Ex3ElipsesSlider()));
        JMenuItem sliderRectItem = createMenuItem("Control de Rectángulos", e -> openUtilityDialog(new Ex5RectSlider()));

        // Ejercicios aleatorios
        JMenuItem randomElipsesItem = createMenuItem("Elipses Aleatorias", e -> openUtilityDialog(new Ex4RandomElipses()));
        JMenuItem randomShapesItem = createMenuItem("Formas Aleatorias", e -> openUtilityDialog(new Ex6RandomShapes()));

        // Agrupar elementos en submenús
        JMenu basicMenu = createSubMenu("Básicos", helloItem);
        JMenu sliderMenu = createSubMenu("Con Sliders", sliderElipsesItem, sliderRectItem);
        JMenu randomMenu = createSubMenu("Aleatorios", randomElipsesItem, randomShapesItem);

        utilMenu.add(basicMenu);
        utilMenu.add(sliderMenu);
        utilMenu.add(randomMenu);

        utilMenu.show(utilidadesButton, 0, utilidadesButton.getHeight());
    }

    private JMenuItem createMenuItem(String text, ActionListener action) {
        JMenuItem item = new JMenuItem(text);
        item.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        item.addActionListener(action);
        return item;
    }

    private JMenu createSubMenu(String title, JMenuItem... items) {
        JMenu menu = new JMenu(title);
        menu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        for (JMenuItem item : items) {
            menu.add(item);
        }
        return menu;
    }

    private void openUtilityDialog(JPanel panel) {
        JDialog dialog = new JDialog();
        dialog.setTitle(panel.getClass().getSimpleName());
        dialog.setLayout(new BorderLayout());
        dialog.add(panel, BorderLayout.CENTER);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    public void updateColorButton(Color color) {
        if (color == null) return;

        colorButton.setBackground(color);
        colorButton.setForeground(
                (color.getRed() + color.getGreen() + color.getBlue() > 382) ?
                        Color.BLACK : Color.WHITE);
    }
}