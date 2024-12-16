import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private static final int TAMANO = 16;
    private Tablero tablero;
    private JButton[][] botonesTablero;
    private JTextArea consola;
    private int turno;

    public GUI() {
        tablero = new Tablero();
        botonesTablero = new JButton[TAMANO][TAMANO];
        turno = 1; // Inicia con el turno del jugador 1

        JFrame frame = new JFrame("GUI - Juego de Naves");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel labelFila = new JLabel("Fila:");
        JTextField fieldFila = new JTextField();

        JLabel labelColumna = new JLabel("Columna:");
        JTextField fieldColumna = new JTextField();

        JButton botonMover = new JButton("Mover");
        JButton botonAtacar = new JButton("Atacar");

        JPanel panelControles = new JPanel();
        panelControles.setLayout(new GridLayout(2, 4)); // 2 filas y 4 columnas

        panelControles.add(labelFila);
        panelControles.add(fieldFila);
        panelControles.add(botonMover);
        panelControles.add(new JLabel()); // Espacio vacío para mantener la alineación

        panelControles.add(labelColumna);
        panelControles.add(fieldColumna);
        panelControles.add(botonAtacar);
        panelControles.add(new JLabel()); // Otro espacio vacío

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
        //Primeros mensajes por pantalla
        consola.append("\nJugador 1 juega con azules (Naves Terricolas) ");
        consola.append("\nJugador 2 juega con verdes (Naves Alienigenas) ");
        consola.append("\nTurno: Jugador " + (turno % 2 == 1 ? 1 : 2));
        // Acción del botón Mover
        botonMover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int fila = Integer.parseInt(fieldFila.getText()) - 1;
                    int columna = Integer.parseInt(fieldColumna.getText()) - 1;

                    if (fila < 0 || fila >= TAMANO || columna < 0 || columna >= TAMANO) {
                        consola.append("\nPosición fuera del tablero.");
                        return;
                    }

                    Nave nave = tablero.obtenerNave(fila, columna);
                    if (nave == null) {
                        consola.append("\nNo hay ninguna nave en la posición seleccionada.");
                        return;
                    }

                    if ((turno % 2 == 1 && !tablero.getFlota1().getMisNaves().contains(nave)) ||
                            (turno % 2 == 0 && !tablero.getFlota2().getMisNaves().contains(nave))) {
                        consola.append("\nNo puedes mover una nave que no te pertenece.");
                        return;
                    }
                    int nuevaFila = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva fila")) - 1;// -1
                                                                                                               // para
                                                                                                               // pasar
                                                                                                               // a
                                                                                                               // indices
                    int nuevaColumna = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva columna")) - 1;

                    consola.append(nave.mover(nuevaFila, nuevaColumna, tablero.getTablero_arreglod()));
                    tablero.actualizarTablero();
                    fieldFila.setText(null);
                    fieldColumna.setText(null);
                    actualizarTableroVisual();
                    turno++;
                    consola.append("\nTurno: Jugador " + (turno % 2 == 1 ? 1 : 2));
                } catch (NumberFormatException ex) {
                    consola.append("\nPor favor, ingrese valores válidos para fila y columna.");
                }
            }
        });
        // Action Listener Atacar
        botonAtacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int fila = Integer.parseInt(fieldFila.getText()) - 1;
                    int columna = Integer.parseInt(fieldColumna.getText()) - 1;

                    if (fila < 0 || fila >= TAMANO || columna < 0 || columna >= TAMANO) {
                        consola.append("\nPosición fuera del tablero.");
                        return;
                    }

                    Nave nave = tablero.obtenerNave(fila, columna);
                    if (nave == null) {
                        consola.append("\nNo hay ninguna nave en la posición seleccionada.");
                        return;
                    }

                    if ((turno % 2 == 1 && !tablero.getFlota1().getMisNaves().contains(nave)) ||
                            (turno % 2 == 0 && !tablero.getFlota2().getMisNaves().contains(nave))) {
                        consola.append("\nNo puedes mover una nave que no te pertenece.");
                        return;
                    }
                    int nuevaFila = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la fila de la nave a atacar"))
                            - 1;
                    int nuevaColumna = Integer
                            .parseInt(JOptionPane.showInputDialog("Ingrese la columna de la nave a atacar")) - 1;
                    int[] pos_ataque = { nuevaFila, nuevaColumna };
                    consola.append(nave.atacar(pos_ataque, tablero.getTablero_arreglod()));
                    tablero.actualizarTablero();
                    fieldFila.setText(null);
                    fieldColumna.setText(null);
                    actualizarTableroVisual();
                    turno++;
                    consola.append("\nTurno: Jugador " + (turno % 2 == 1 ? 1 : 2));
                } catch (NumberFormatException ex) {
                    consola.append("\nPor favor, ingrese valores válidos para fila y columna.");
                }
            }
        });
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        frame.pack();
        frame.setVisible(true);

        // Inicializar el tablero con naves
        inicializarNaves();
        actualizarTableroVisual();
    }

    private void inicializarNaves() {
        for (int i = 0; i < 5; i++) {
            tablero.getFlota1().agregarALaFlota(new NaveTerricola());
            tablero.getFlota2().agregarALaFlota(new NaveAlienigena());
        }
        tablero.posicionarNaves(tablero.getFlota1(), 0, 7);
        tablero.posicionarNaves(tablero.getFlota2(), 8, 15);
    }

    private void actualizarTableroVisual() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                Nave nave = tablero.obtenerNave(i, j);
                if (nave != null) {
                    if (tablero.getFlota1().getMisNaves().contains(nave)) {
                        botonesTablero[i][j].setBackground(Color.BLUE); // Flota humana
                    } else if (tablero.getFlota2().getMisNaves().contains(nave)) {
                        botonesTablero[i][j].setBackground(Color.GREEN); // Flota alienígena
                    }
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
