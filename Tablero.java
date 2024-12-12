public class Tablero {
    private final int filas = 8;
    private final int columnas = 8;
    private Nave[][] casillas; // Representa el tablero de 8x8
    private Nave[] navesHumanas; // Arreglo de naves humanas
    private Nave[] navesAlienigenas; // Arreglo de naves alienígenas

    public Tablero(int numNavesHumanas, int numNavesAlienigenas) {
        casillas = new Nave[filas][columnas];
        navesHumanas = new Nave[numNavesHumanas];
        navesAlienigenas = new Nave[numNavesAlienigenas];
    }

    // Método para colocar una nave en el tablero
    public boolean colocarNave(Nave nave, int fila, int columna) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas && casillas[fila][columna] == null) {
            casillas[fila][columna] = nave;
            nave.setPosicion(fila, columna);
            return true;
        }
        return false;
    }

    // Método para mover una nave a una nueva posición
    public boolean moverNave(Nave nave, int nuevaFila, int nuevaColumna) {
        int filaActual = nave.getFila();
        int columnaActual = nave.getColumna();

        if (nuevaFila >= 0 && nuevaFila < filas && nuevaColumna >= 0 && nuevaColumna < columnas && casillas[nuevaFila][nuevaColumna] == null) {
            casillas[filaActual][columnaActual] = null; // Vacía la casilla actual
            casillas[nuevaFila][nuevaColumna] = nave; // Coloca la nave en la nueva casilla
            nave.setPosicion(nuevaFila, nuevaColumna);
            return true;
        }
        return false;
    }

    // Método para eliminar una nave del tablero
    public void eliminarNave(Nave nave) {
        int fila = nave.getFila();
        int columna = nave.getColumna();
        casillas[fila][columna] = null;
    }

    // Método para actualizar el tablero (por ejemplo, después de un turno)
    public void actualizarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (casillas[i][j] != null) {
                    casillas[i][j].realizarAccion(); // Cada nave puede realizar una acción
                }
            }
        }
    }

    // Mostrar el tablero en consola (para pruebas)
    public void mostrarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (casillas[i][j] == null) {
                    System.out.print("."); // Casilla vacía
                } else {
                    System.out.print(casillas[i][j].getSimbolo()); // Representación de la nave
                }
            }
            System.out.println();
        }
    }

    public Nave[] getNavesHumanas() {
        return navesHumanas;
    }

    public Nave[] getNavesAlienigenas() {
        return navesAlienigenas;
    }
}
