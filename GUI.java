import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private static final int TAMANO = 16;
    private Tablero tablero;
    private JButton[][] botonesTablero;
    private JTextArea consola;

    public GUI() {
        tablero = new Tablero();
        botonesTablero = new JButton[TAMANO][TAMANO];

        JFrame frame = new JFrame("GUI - Juego de Naves");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panelControles = new JPanel();
        panelControles.setLayout(new GridLayout(2, 3));

        JLabel labelFila = new JLabel("Fila:");
        JTextField fieldFila = new JTextField();

        JLabel labelColumna = new JLabel("Columna:");
        JTextField fieldColumna = new JTextField();

        JButton botonMover = new JButton("Mover");

        panelControles.add(labelFila);
        panelControles.add(fieldFila);
        panelControles.add(new JLabel());
        panelControles.add(labelColumna);
        panelControles.add(fieldColumna);
        panelControles.add(botonMover);

        JPanel panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(TAMANO + 1, TAMANO + 1));
        panelTablero.add(new JLabel(""));
        for (int i = 0; i < TAMANO; i++) {
            panelTablero.add(new JLabel(String.valueOf(i + 1), SwingConstants.CENTER));
        }

        for (int i = 0; i < TAMANO; i++) {
            panelTablero.add(new JLabel(String.valueOf(i + 1), SwingConstants.CENTER));
            for (int j = 0; j < TAMANO; j++) {
                JButton celda = new JButton();
                celda.setEnabled(false);
                botonesTablero[i][j] = celda;
                panelTablero.add(celda);
            }
        }

        consola = new JTextArea(10, 50);
        consola.setEditable(false);
        JScrollPane scrollConsola = new JScrollPane(consola);

        frame.add(panelControles, BorderLayout.NORTH);
        frame.add(panelTablero, BorderLayout.CENTER);
        frame.add(scrollConsola, BorderLayout.SOUTH);

        // Acción del botón Mover
        botonMover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int fila = Integer.parseInt(fieldFila.getText()) - 1;
                    int columna = Integer.parseInt(fieldColumna.getText()) - 1;

                    if (fila < 0 || fila >= TAMANO || columna < 0 || columna >= TAMANO) {
                        JOptionPane.showMessageDialog(scrollConsola, "\nPosición fuera del tablero.");
                        return;
                    }

                    Nave nave = tablero.obtenerNave(fila, columna);
                    if (nave == null) {
                         JOptionPane.showMessageDialog(scrollConsola,"\nNo hay ninguna nave en la posición seleccionada.");
                        return;
                    }

                    int nuevaFila = (fila + 1) % TAMANO; // Ejemplo: mover una fila abajo
                    int nuevaColumna = columna; // Mantener la misma columna

                    consola.append(nave.mover(nuevaFila, nuevaColumna, tablero.getTablero_arreglod()));
                    actualizarTableroVisual();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(scrollConsola,"\nPor favor, ingrese valores válidos para fila y columna.");
                }
            }
        });

        frame.pack();
        frame.setVisible(true);

        // Inicializar el tablero con naves
        inicializarNaves();
        actualizarTableroVisual();
    }

    private void inicializarNaves() {
        for (int i = 0; i < 5; i++) {
            tablero.getFlota1().agregarALaFlota(new NaveTerricola());
            tablero.getFlota2().agregarALaFlota(new NaveTerricola());
        }
        tablero.posicionarNaves(tablero.getFlota1(), 0, 7);
        tablero.posicionarNaves(tablero.getFlota2(), 8, 15);
    }

    private void actualizarTableroVisual() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                if (tablero.obtenerNave(i, j) != null) {
                    botonesTablero[i][j].setBackground(Color.RED);
                } else {
                    botonesTablero[i][j].setBackground(null);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
