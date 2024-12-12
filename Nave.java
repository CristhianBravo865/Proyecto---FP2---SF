public abstract class Nave {
    private int fila;
    private int columna;
    private char simbolo; // Representación visual de la nave en el tablero

    public Nave(char simbolo) {
        this.simbolo = simbolo;
    }

    // Métodos para obtener la posición actual
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    // Método para establecer la posición de la nave
    public void setPosicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    // Método para obtener el símbolo de la nave
    public char getSimbolo() {
        return simbolo;
    }

    // Método abstracto que define cómo se mueve la nave
    public abstract boolean puedeMoverse(int nuevaFila, int nuevaColumna, Tablero tablero);

    // Método abstracto que define la acción de ataque de la nave
    public abstract void atacar(Nave objetivo, Tablero tablero);

    // Método abstracto para realizar acciones específicas de la nave (por turno)
    public abstract void realizarAccion();

    // Método abstracto que define si una nave puede ser atacada
    public abstract boolean esAtacable();
}
