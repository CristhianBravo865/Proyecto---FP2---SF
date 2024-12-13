import javax.swing.*;
import java.awt.*;

public class GUI {
    private static final int TAMANO = 16;
    private final JFrame frameJugador1;
    private final JFrame frameJugador2;
    private final Tablero tablero;
    private final JTextArea consola; // Nueva área de texto para la "consola"

    public GUI(Tablero tablero, Jugador<NaveTerricola> jugador1, Jugador<NaveAlienigena> jugador2) {
        this.tablero = tablero;

        // Inicializa la "consola" de mensajes
        consola = new JTextArea(10, 50);
        consola.setEditable(false);

        // Inicializa los tableros para cada jugador
        frameJugador1 = crearVistaJugador("Jugador 1 (Terrícolas)", tablero.getVistaParcial(8, 15));
        frameJugador2 = crearVistaJugador("Jugador 2 (Alienígenas)", tablero.getVistaParcial(0, 7));
    }

    private JFrame crearVistaJugador(String titulo, String[][] vista) {
        JFrame frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Crear panel superior para la "consola" de información
        JPanel panelConsola = new JPanel();
        panelConsola.setLayout(new GridLayout(2, 3));

        JLabel labelFila = new JLabel("Fila:");
        JTextField fieldFila = new JTextField();

        JLabel labelColumna = new JLabel("Columna:");
        JTextField fieldColumna = new JTextField();

        JButton botonMover = new JButton("Mover");

        // Agregar componentes al panel
        panelConsola.add(labelFila);
        panelConsola.add(fieldFila);
        panelConsola.add(new JLabel()); // Espaciador
        panelConsola.add(labelColumna);
        panelConsola.add(fieldColumna);
        panelConsola.add(botonMover);

        // Acción del botón (debes implementar la lógica en otro lugar)
        botonMover.addActionListener(e -> {
            try {
                int fila = Integer.parseInt(fieldFila.getText());
                int columna = Integer.parseInt(fieldColumna.getText());
                // Aquí puedes llamar a un método para mover una nave
                agregarMensajeConsola("Mover nave a Fila: " + fila + ", Columna: " + columna);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Por favor, ingresa números válidos en los campos de fila y columna.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Crear el tablero en el centro
        JPanel panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(TAMANO + 1, TAMANO + 1)); // +1 para enumeraciones

        // Agregar etiquetas para numeración
        panelTablero.add(new JLabel("")); // Esquina vacía
        for (int i = 0; i < TAMANO; i++) {
            panelTablero.add(new JLabel(String.valueOf(i + 1), SwingConstants.CENTER));
        }

        for (int i = 0; i < TAMANO; i++) {
            panelTablero.add(new JLabel(String.valueOf(i + 1), SwingConstants.CENTER)); // Números de fila
            for (int j = 0; j < TAMANO; j++) {
                JButton celda = new JButton(vista[i][j].equals("Oscuro") ? "?" : vista[i][j]);
                celda.setEnabled(false); // No interactivo por ahora
                panelTablero.add(celda);
            }
        }

        // Crear panel inferior para la consola
        JPanel panelInferior = new JPanel(new BorderLayout());
        JScrollPane scrollConsola = new JScrollPane(consola);
        panelInferior.add(scrollConsola, BorderLayout.CENTER);

        // Agregar paneles al frame
        frame.add(panelConsola, BorderLayout.NORTH);
        frame.add(panelTablero, BorderLayout.CENTER);
        frame.add(panelInferior, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    public void actualizarVista(Nave naveSeleccionada, JFrame jframeSeleccionado) {
        // Obtener la fila y columna de la nave seleccionada
        int fila = naveSeleccionada.getFilaActual();  // Método para obtener fila
        int columna = naveSeleccionada.getColumnaActual();  // Método para obtener columna
    
        // Calcular la zona alrededor del soldado para mostrar (puedes ajustar el rango)
        int rango = 3;  // Muestra un área de 3x3 alrededor de la nave
        int inicioFila = Math.max(0, fila - rango);
        int finFila = Math.min(TAMANO - 1, fila + rango);
        int inicioColumna = Math.max(0, columna - rango);
        int finColumna = Math.min(TAMANO - 1, columna + rango);
    
        // Obtener la vista parcial basada en la zona alrededor de la nave
        String[][] vistaParcial = tablero.getVistaParcial(inicioFila, finFila);
    
        // Limpiar el JFrame y actualizarlo con la nueva vista
        jframeSeleccionado.getContentPane().removeAll();
        jframeSeleccionado.setLayout(new GridLayout(TAMANO + 1, TAMANO + 1)); // +1 para numeración
    
        // Agregar etiquetas para numeración
        jframeSeleccionado.add(new JLabel("")); // Esquina vacía
        for (int i = 0; i < TAMANO; i++) {
            jframeSeleccionado.add(new JLabel(String.valueOf(i + 1), SwingConstants.CENTER));
        }
    
        // Agregar las celdas para el tablero
        for (int i = 0; i < TAMANO; i++) {
            jframeSeleccionado.add(new JLabel(String.valueOf(i + 1), SwingConstants.CENTER)); // Números de fila
            for (int j = 0; j < TAMANO; j++) {
                String contenidoCelda = vistaParcial[i][j].equals("Oscuro") ? "?" : vistaParcial[i][j];
                JButton celda = new JButton(contenidoCelda);
                celda.setEnabled(false);  // Deshabilitar interacción por ahora
                jframeSeleccionado.add(celda);
            }
        }
    
        // Volver a validar y repintar la vista
        jframeSeleccionado.revalidate();
        jframeSeleccionado.repaint();
    }

    public void mostrar() {
        frameJugador1.setVisible(true);
        frameJugador2.setVisible(true);
    }

    public void agregarMensajeConsola(String mensaje) {
        consola.append(mensaje + "\n");
        consola.setCaretPosition(consola.getDocument().getLength());
    }
}
