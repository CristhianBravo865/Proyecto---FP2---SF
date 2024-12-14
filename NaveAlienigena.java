public class NaveAlienigena extends Nave {
    public NaveAlienigena() {
        this.nombre = "Nave Alienígena";
        this.ataque_pts = 100;
        this.hp = 100;
        this.alcance_movimiento = 3;
        this.alcance_disparo = 4;
        this.direccion = 1; // Movimiento en cualquier dirección
    }

    @Override
    public String mover(int fila, int columna, Nave[][] tablero) {
        if (!posicionValidaTablero(fila, columna)) {
            return "Movimiento fuera del tablero.";
        }

        int distanciaFila = Math.abs(this.fila - fila);
        int distanciaColumna = Math.abs(this.columna - columna);
        if (distanciaFila > alcance_movimiento || distanciaColumna > alcance_movimiento) {
            return "Movimiento fuera de alcance.";
        }

        if (tablero[fila][columna] != null) {
            return "Movimiento inválido: hay otra nave en esa posición.";
        }

        tablero[this.fila][this.columna] = null;
        this.fila = fila;
        this.columna = columna;
        tablero[fila][columna] = this;
        return "Movimiento realizado con éxito.";
    }

    @Override
    public String atacar(Nave objetivo, Nave[][] tablero) {
        return super.atacar(objetivo, tablero);
    }
}
