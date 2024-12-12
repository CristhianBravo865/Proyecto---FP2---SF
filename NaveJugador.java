import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NaveJugador extends Nave {
    public NaveJugador(int x, int y) {
        super(x, y, 50, 50); // Dimensiones para el jugador
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.CYAN); // Color de la nave 
        g.fillRect(x, y, ancho, alto); // RECTANGULO DE MOMENTO
    }

    // Método para mover la nave
    public void mover(int dx, int dy) {
        x += dx;
        y += dy;
    }

}
