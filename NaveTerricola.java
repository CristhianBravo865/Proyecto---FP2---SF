public class NaveTerricola extends Nave {
    public NaveTerricola() {
        this.nombre = "Nave Terrícola";
        this.ataque_pts = 100;
        this.hp = 100;
        this.alcance_movimiento = 3;
        this.alcance_disparo = 4;
        this.direccion = 0; // Movimiento horizontal por defecto
    }

    @Override
    public String mover(int fila, int columna, Nave[][] tablero) {
        if (!posicionValidaTablero(fila, columna)) {
            return "Movimiento fuera del tablero.";
        }

        if (Math.abs(this.columna - columna) > alcance_movimiento) {
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
