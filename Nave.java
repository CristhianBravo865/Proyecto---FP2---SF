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
    protected int filaSF;
    protected int columnaSF;

    public Nave() {
        this.vivo = true;
    }

    public abstract String mover(int fila, int columna, Nave[][] tablero);

    public abstract String atacar(int[] coords_objetivo, Nave[][] tablero);

    public abstract String atacarAvanzado(int[] coords_objetivo, Nave[][] tablero);

    public void comprobarEstado() {
        this.vivo = this.hp > 0;
    }

    protected boolean posicionValidaTablero(int fila, int columna) {
        return fila >= 0 && fila < 16 && columna >= 0 && columna < 16;
    }
    
    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public int getAtaque_pts() {
        return ataque_pts;
    }

    public void setAtaque_pts(int ataque_pts) {
        this.ataque_pts = ataque_pts;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFilaSF() {
        return filaSF;
    }

    public void setFilaSF(int filaSF) {
        this.filaSF = filaSF;
    }

    public int getColumnaSF() {
        return columnaSF;
    }

    public void setColumnaSF(int columnaSF) {
        this.columnaSF = columnaSF;
    }

    @Override
    public String toString() {
        return "Nave [nombre=" + nombre + ", ataque_pts=" + ataque_pts + ", alcance_movimiento=" + alcance_movimiento
                + ", alcance_disparo=" + alcance_disparo + ", hp=" + hp + ", direccion=" + direccion + ", vivo=" + vivo
                + ", fila=" + fila + ", columna=" + columna + "]";
    }

}
