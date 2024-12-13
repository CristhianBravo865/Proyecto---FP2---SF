import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Tablero {
    private static final int TAMANO = 16; // Tamaño del tablero 16x16
    private final String[][] tablero;
    private final Random random;

    public Tablero() {
        this.tablero = new String[TAMANO][TAMANO];
        this.random = new Random();
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                tablero[i][j] = " ";
            }
        }
    }

    public void posicionarNaves(Jugador<? extends Nave> jugador, int inicioFila, int finFila) {
        ArrayList<Nave> naves = new ArrayList<>(jugador.getNaves());

        for (Nave nave : naves) {
            boolean posicionada = false;
            while (!posicionada) {
                int fila = random.nextInt(finFila - inicioFila + 1) + inicioFila;
                int columna = random.nextInt(TAMANO);

                if (tablero[fila][columna].equals(" ")) {
                    tablero[fila][columna] = nave.getNombre(); // Supone un método getNombre() en Nave
                    posicionada = true;
                }
            }
        }
    }

    public String[][] getTablero() {
        return tablero;
    }

    public String[][] getVistaParcial(int inicioFila, int finFila) {
        String[][] vista = new String[TAMANO][TAMANO];

        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                if (i >= inicioFila && i <= finFila) {
                    vista[i][j] = tablero[i][j];
                } else {
                    vista[i][j] = "Oscuro"; // Espacios ocultos
                }
            }
        }
        return vista;
    }
}
