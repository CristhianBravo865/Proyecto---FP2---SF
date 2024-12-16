import java.util.*;

public class Tablero {
    private Nave[][] tablero_arreglod; // Tablero de 16x16
    private Flota<NaveTerricola> flota1; // Flota de naves terrestres 1
    private Flota<NaveAlienigena> flota2; // Flota de naves alienigenas 2

    public Tablero() {
        tablero_arreglod = new Nave[16][16];
        flota1 = new Flota<>();
        flota2 = new Flota<>();
    }

    public Flota<NaveTerricola> getFlota1() {
        return flota1;
    }

    public Flota<NaveAlienigena> getFlota2() {
        return flota2;
    }

    public void posicionarNaves(Flota<? extends Nave> flota, int rangoInicio, int rangoFin) {
        Random random = new Random();
        for (Nave nave : flota.getMisNaves()) {
            int fila, columna;

            do {
                fila = random.nextInt(rangoFin - rangoInicio + 1) + rangoInicio;
                columna = random.nextInt(16); // Columnas del 0 al 15
            } while (tablero_arreglod[fila][columna] != null); // Evita posiciones ocupadas

            tablero_arreglod[fila][columna] = nave;
            nave.fila = fila;
            nave.columna = columna;
        }
    }
    

    public void actualizarTablero() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                tablero_arreglod[i][j] = null;
            }
        }

        for (NaveTerricola nave : flota1.getMisNaves()) {
            if (nave.vivo) {
                tablero_arreglod[nave.fila][nave.columna] = nave;
            }
        }

        for (NaveAlienigena nave : flota2.getMisNaves()) {
            if (nave.vivo) {
                tablero_arreglod[nave.fila][nave.columna] = nave;
            }
        }
    }

    public Nave obtenerNave(int f, int c) {
        return tablero_arreglod[f][c];
    }

    public Nave[][] getTablero_arreglod() {
        return tablero_arreglod;
    }

}
