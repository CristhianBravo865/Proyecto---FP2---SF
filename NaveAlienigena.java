import java.awt.*;

public abstract class NaveAlienigena {
    protected int x, y; // Posición de la nave
    protected int ancho, alto; // Tamaño de la nave

    public NaveAlienigena(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    // Dibujar la nave
    public abstract void dibujar(Graphics g);

    // Mover Nave - Se deberia implementar??
    public void mover(int dx) {
        x += dx;
    }

    // Puntaje que vale la nave
    public abstract int getPuntaje();
}
