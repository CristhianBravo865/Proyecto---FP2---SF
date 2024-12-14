import java.util.*;

public class Tablero {
    private Nave[][] tablero; // Tablero de 16x16
    private Flota<NaveTerricola> flota1; // Flota de naves terrestres 1
    private Flota<NaveTerricola> flota2; // Flota de naves terrestres 2

    public Tablero() {
        tablero = new Nave[16][16];
        flota1 = new Flota<>();
        flota2 = new Flota<>();
    }

    public Flota<NaveTerricola> getFlota1() {
        return flota1;
    }

    public Flota<NaveTerricola> getFlota2() {
        return flota2;
    }

    /**
     * Posiciona las naves de una flota en un rango específico del mapa.
     * 
     * @param flota        Flota cuyas naves se posicionarán.
     * @param rangoInicio  Fila inicial del rango.
     * @param rangoFin     Fila final del rango.
     */
    public void posicionarNaves(Flota<NaveTerricola> flota, int rangoInicio, int rangoFin) {
        Random random = new Random();
        for (NaveTerricola nave : flota.getMisNaves()) {
            int fila, columna;

            do {
                fila = random.nextInt(rangoFin - rangoInicio + 1) + rangoInicio;
                columna = random.nextInt(16); // Columnas del 0 al 15
            } while (tablero[fila][columna] != null); // Evita posiciones ocupadas

            tablero[fila][columna] = nave;
            nave.fila = fila;
            nave.columna = columna;
        }
    }

    public void actualizarTablero() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                tablero[i][j] = null;
            }
        }

        for (NaveTerricola nave : flota1.getMisNaves()) {
            if (nave.vivo) {
                tablero[nave.fila][nave.columna] = nave;
            }
        }

        for (NaveTerricola nave : flota2.getMisNaves()) {
            if (nave.vivo) {
                tablero[nave.fila][nave.columna] = nave;
            }
        }
    }
}
