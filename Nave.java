public abstract class Nave {
    protected int vida;
    protected int movimiento;
    protected int daño;
    protected int alcance;

    public abstract void mover(int fila, int columna, Tablero tablero);
    public abstract void atacar(Nave objetivo, Tablero tablero);
    public abstract void habilidadEspecial();

    // Getters y setters básicos
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public boolean estaDestruida() {
        return vida <= 0;
    }
}
