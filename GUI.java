import javax.swing.*;
import java.awt.*;

public class GUI {
    private static final int TAMANO = 16;
    private final JFrame frameJugador1;
    private final JFrame frameJugador2;
    private final Tablero tablero;

    public GUI(Tablero tablero, Jugador<NaveTerricola> jugador1, Jugador<NaveAlienigena> jugador2) {
        this.tablero = tablero;

        // Inicializa los tableros para cada jugador
        frameJugador1 = crearVistaJugador("Jugador 1 (Terrícolas)", tablero.getVistaParcial(8, 15));
        frameJugador2 = crearVistaJugador("Jugador 2 (Alienígenas)", tablero.getVistaParcial(0, 7));
    }

    private JFrame crearVistaJugador(String titulo, String[][] vista) {
        JFrame frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(TAMANO + 1, TAMANO + 1)); // +1 para enumeraciones

        // Agregar etiquetas para numeración
        frame.add(new JLabel("")); // Esquina vacía
        for (int i = 0; i < TAMANO; i++) {
            frame.add(new JLabel(String.valueOf(i + 1), SwingConstants.CENTER));
        }

        for (int i = 0; i < TAMANO; i++) {
            frame.add(new JLabel(String.valueOf(i + 1), SwingConstants.CENTER)); // Números de fila
            for (int j = 0; j < TAMANO; j++) {
                JButton celda = new JButton(vista[i][j].equals("Oscuro") ? "?" : vista[i][j]);
                celda.setEnabled(false); // No interactivo por ahora
                frame.add(celda);
            }
        }

        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    public void mostrar() {
        frameJugador1.setVisible(true);
        frameJugador2.setVisible(true);
    }
}
