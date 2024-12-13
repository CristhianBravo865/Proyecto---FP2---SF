public abstract class Nave {
    protected String nombre;
    protected int vida;
    protected int movimiento;
    protected int daño;
    protected int alcance;
    protected int filaActual;
    protected int columnaActual;

    public abstract void mover(int fila, int columna, Tablero tablero);
    public abstract void atacar(Nave objetivo, Tablero tablero);
    public abstract void habilidadEspecial();

    // Verifica si una posición está dentro del rango del tablero
    protected boolean posicionValida(int fila, int columna, Tablero tablero) {
        return fila >= 0 && fila < 16 && columna >= 0 && columna < 16 
               && tablero.getTablero()[fila][columna].equals(" ");
    }

    // Getters y setters
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public boolean estaDestruida() {
        return vida <= 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(int movimiento) {
        this.movimiento = movimiento;
    }

    public int getDaño() {
        return daño;
    }

    public void setDaño(int daño) {
        this.daño = daño;
    }

    public int getAlcance() {
        return alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }
}
