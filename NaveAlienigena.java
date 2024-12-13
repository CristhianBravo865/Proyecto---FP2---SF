public abstract class NaveAlienigena extends Nave {
    @Override
    public void mover(int fila, int columna, Tablero tablero) {
        if (posicionValida(fila, columna, tablero)) {
            tablero.getTablero()[filaActual][columnaActual] = " ";
            tablero.getTablero()[fila][columna] = nombre;
            filaActual = fila;
            columnaActual = columna;
        } else {
            System.out.println("Movimiento no válido.");
        }
    }

    @Override
    public void atacar(Nave objetivo, Tablero tablero) {
        int distancia = Math.abs(filaActual - objetivo.filaActual) + Math.abs(columnaActual - objetivo.columnaActual);
        if (distancia <= alcance) {
            objetivo.setVida(objetivo.getVida() - daño);
            System.out.println(nombre + " atacó a " + objetivo.getNombre() + " infligiendo " + daño + " de daño.");
        } else {
            System.out.println("Objetivo fuera de alcance.");
        }
    }
}
