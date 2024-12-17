import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GUI {
    private static final int TAMANO = 16;
    private Tablero tablero;
    private JButton[][] botonesTablero;
    private JTextArea consola;
    private int turno;
    private boolean modoAvanzado;

    public GUI(boolean modoAvanzado) {
        this();
        this.modoAvanzado = modoAvanzado;
        if (modoAvanzado) {
            consola.append("\nModo avanzado activado: Los ataques llevan a Space Fight.");
        } else {
            consola.append("\nModo simple activado: Ataques simples.");
        }
    }

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

        // Primeros mensajes por pantalla
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

                    int nuevaFila = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva fila")) - 1;
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

        // Acción del botón Atacar
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
                        consola.append("\nNo puedes atacar con una nave que no te pertenece.");
                        return;
                    }

                    int nuevaFila = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la fila de la nave a atacar"))
                            - 1;
                    int nuevaColumna = Integer
                            .parseInt(JOptionPane.showInputDialog("Ingrese la columna de la nave a atacar")) - 1;
                    int[] pos_ataque = { nuevaFila, nuevaColumna };
                    if (modoAvanzado) {
                        consola.append(nave.atacarAvanzado(pos_ataque, tablero.getTablero_arreglod()));
                    } else {
                        consola.append(nave.atacar(pos_ataque, tablero.getTablero_arreglod()));
                    }
                    tablero.actualizarTablero();
                    fieldFila.setText(null);
                    fieldColumna.setText(null);
                    actualizarTableroVisual();

                    if (tablero.getFlota1().flotaDestruida()) {
                        String mensaje = "Gano el jugador 2";
                        JOptionPane.showMessageDialog(scrollConsola, mensaje);
                        guardarGanadorEnArchivo(mensaje);
                        volverAlMenuPrincipal(frame);
                    } else if (tablero.getFlota2().flotaDestruida()) {
                        String mensaje = "Gano el jugador 1";
                        JOptionPane.showMessageDialog(scrollConsola, mensaje);
                        guardarGanadorEnArchivo(mensaje);
                        volverAlMenuPrincipal(frame);
                    }

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

    private void guardarGanadorEnArchivo(String mensaje) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ganador.txt", true))) {
            writer.write(mensaje);
            writer.newLine();
        } catch (IOException ex) {
            consola.append("\nError al guardar el ganador en el archivo.");
        }
    }

    private void volverAlMenuPrincipal(JFrame frameActual) {
        frameActual.dispose(); // Cierra la ventana actual del juego
        SwingUtilities.invokeLater(MenuPrincipal::new); // Vuelve al menú principal
    }
}
