import java.util.*;

public abstract class Nave {
    protected String nombre;
    protected int ataque_pts;
    protected int alcance_movimiento;
    protected int alcance_disparo;
    protected int hp;
    protected int direccion; // 0: Horizontal, 1: Cualquier dirección
    protected boolean vivo;
    protected int fila;
    protected int columna;

    public Nave() {
        this.vivo = true;
    }

    public abstract String mover(int fila, int columna, Nave[][] tablero);

    public String atacar(Nave objetivo, Nave[][] tablero) {
        if (objetivo == null) {
            return "No hay enemigo en esa posición.";
        }

        int distanciaFila = Math.abs(this.fila - objetivo.fila);
        int distanciaColumna = Math.abs(this.columna - objetivo.columna);

        if ((direccion == 0 && distanciaFila != 0) || 
            (direccion == 1 && distanciaFila > alcance_disparo && distanciaColumna > alcance_disparo)) {
            return "El enemigo está fuera de alcance.";
        }

        objetivo.hp -= this.ataque_pts;
        if (objetivo.hp <= 0) {
            objetivo.vivo = false;
        }
        return "Ataque realizado con éxito.";
    }

    public void comprobarEstado() {
        this.vivo = this.hp > 0;
    }

    protected boolean posicionValidaTablero(int fila, int columna) {
        return fila >= 0 && fila < 16 && columna >= 0 && columna < 16;
    }

    @Override
    public String toString() {
        return "Nave [nombre=" + nombre + ", ataque_pts=" + ataque_pts + ", alcance_movimiento=" + alcance_movimiento
                + ", alcance_disparo=" + alcance_disparo + ", hp=" + hp + ", direccion=" + direccion + ", vivo=" + vivo
                + ", fila=" + fila + ", columna=" + columna + "]";
    }
}
