import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SpaceFight extends JPanel implements ActionListener, KeyListener {
    private Nave naveJugador1;
    private Nave naveJugador2;
    private Timer timer;
    private ArrayList<Rectangle> meteoritos;
    private ArrayList<Rectangle> proyectiles1;
    private ArrayList<Rectangle> proyectiles2;

    private int anchoNave = 50, altoNave = 30;
    private int anchoProyectil = 5, altoProyectil = 10;

    private boolean w, a, s, d; // Controles para Jugador 1
    private boolean up, down, left, right; // Controles para Jugador 2

    public SpaceFight(Nave jugador1, Nave jugador2) {
        this.naveJugador1 = jugador1;
        this.naveJugador2 = jugador2;

        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);
        // Posiciones iniciales de las naves
        int centroX = 400 - anchoNave / 2;
        this.naveJugador1.setColumna(centroX);
        this.naveJugador1.setFila(500);

        this.naveJugador2.setColumna(centroX);
        this.naveJugador2.setFila(45);

        timer = new Timer(16, this);
        meteoritos = new ArrayList<>();
        proyectiles1 = new ArrayList<>();
        proyectiles2 = new ArrayList<>();

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moverNaves();
        moverProyectiles();
        moverMeteoritos();
        verificarColisiones();
        repaint(); // Redibujar el panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar naves con colores
        g.setColor(naveJugador1 instanceof NaveTerricola ? Color.BLUE : Color.GREEN);
        g.fillRect(naveJugador1.getColumna(), naveJugador1.getFila(), anchoNave, altoNave);

        g.setColor(naveJugador2 instanceof NaveTerricola ? Color.BLUE : Color.GREEN);
        g.fillRect(naveJugador2.getColumna(), naveJugador2.getFila(), anchoNave, altoNave);

        // Dibujar proyectiles
        g.setColor(Color.YELLOW);
        for (Rectangle proyectil : proyectiles1) {
            g.fillRect(proyectil.x, proyectil.y, proyectil.width, proyectil.height);
        }
        for (Rectangle proyectil : proyectiles2) {
            g.fillRect(proyectil.x, proyectil.y, proyectil.width, proyectil.height);
        }

        // Dibujar meteoritos
        g.setColor(Color.GRAY);
        for (Rectangle meteorito : meteoritos) {
            g.fillRect(meteorito.x, meteorito.y, meteorito.width, meteorito.height);
        }

        // Dibujar barra de vida
        g.setColor(Color.WHITE);
        g.drawString("Jugador 1 HP: " + naveJugador1.getHp(), 10, 20);
        g.drawString("Jugador 2 HP: " + naveJugador2.getHp(), 400, 20);
    }

    private void moverNaves() {
        if (w && naveJugador1.getFila() > 0)
            naveJugador1.setFila(naveJugador1.getFila() - 5);
        if (s && naveJugador1.getFila() < getHeight() - altoNave)
            naveJugador1.setFila(naveJugador1.getFila() + 5);
        if (a && naveJugador1.getColumna() > 0)
            naveJugador1.setColumna(naveJugador1.getColumna() - 5);
        if (d && naveJugador1.getColumna() < getWidth() - anchoNave)
            naveJugador1.setColumna(naveJugador1.getColumna() + 5);

        if (up && naveJugador2.getFila() > 0)
            naveJugador2.setFila(naveJugador2.getFila() - 5);
        if (down && naveJugador2.getFila() < getHeight() - altoNave)
            naveJugador2.setFila(naveJugador2.getFila() + 5);
        if (left && naveJugador2.getColumna() > 0)
            naveJugador2.setColumna(naveJugador2.getColumna() - 5);
        if (right && naveJugador2.getColumna() < getWidth() - anchoNave)
            naveJugador2.setColumna(naveJugador2.getColumna() + 5);
    }

    private void moverProyectiles() {
        for (int i = 0; i < proyectiles1.size(); i++) {
            Rectangle p = proyectiles1.get(i);
            p.y -= 10;
            if (p.y < 0)
                proyectiles1.remove(i);
        }
        for (int i = 0; i < proyectiles2.size(); i++) {
            Rectangle p = proyectiles2.get(i);
            p.y += 10;
            if (p.y > getHeight())
                proyectiles2.remove(i);
        }

        // Disparo automático
        if (Math.random() < 0.02) {
            proyectiles1.add(new Rectangle(naveJugador1.getColumna() + anchoNave / 2, naveJugador1.getFila(),
                    anchoProyectil, altoProyectil));
            proyectiles2.add(new Rectangle(naveJugador2.getColumna() + anchoNave / 2, naveJugador2.getFila() + altoNave,
                    anchoProyectil, altoProyectil));
        }
    }

    private void moverMeteoritos() {
        if (Math.random() < 0.03) { // Generar meteoritos al azar
            meteoritos.add(new Rectangle(new Random().nextInt(getWidth()), 0, 20, 20));
        }

        for (int i = 0; i < meteoritos.size(); i++) {
            Rectangle meteorito = meteoritos.get(i);
            meteorito.y += 5;
            if (meteorito.y > getHeight())
                meteoritos.remove(i);
        }
    }

    private void verificarColisiones() {
        for (Rectangle meteorito : meteoritos) {
            if (meteorito.intersects(naveJugador1.getColumna(), naveJugador1.getFila(), anchoNave, altoNave)) {
                naveJugador1.setHp(naveJugador1.getHp() - 10);
                meteorito.setLocation(-100, -100); // Eliminar meteorito
            }
            if (meteorito.intersects(naveJugador2.getColumna(), naveJugador2.getFila(), anchoNave, altoNave)) {
                naveJugador2.setHp(naveJugador2.getHp() - 10);
                meteorito.setLocation(-100, -100); // Eliminar meteorito
            }
        }

        for (Rectangle proyectil : proyectiles1) {
            if (proyectil.intersects(naveJugador2.getColumna(), naveJugador2.getFila(), anchoNave, altoNave)) {
                naveJugador2.setHp(naveJugador2.getHp() - naveJugador1.getAtaque_pts());
                proyectil.setLocation(-100, -100); // Eliminar proyectil
            }
        }
        for (Rectangle proyectil : proyectiles2) {
            if (proyectil.intersects(naveJugador1.getColumna(), naveJugador1.getFila(), anchoNave, altoNave)) {
                naveJugador1.setHp(naveJugador1.getHp() - naveJugador2.getAtaque_pts());
                proyectil.setLocation(-100, -100); // Eliminar proyectil
            }
        }

        if (naveJugador1.getHp() <= 0 || naveJugador2.getHp() <= 0) {
            timer.stop();
            String ganador = naveJugador1.getHp() <= 0 ? "Jugador 2 gana" : "Jugador 1 gana";
            JOptionPane.showMessageDialog(this, ganador, "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
            ((Window) this.getTopLevelAncestor()).dispose(); // Cerrar ventana
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> w = true;
            case KeyEvent.VK_A -> a = true;
            case KeyEvent.VK_S -> s = true;
            case KeyEvent.VK_D -> d = true;
            case KeyEvent.VK_UP -> up = true;
            case KeyEvent.VK_DOWN -> down = true;
            case KeyEvent.VK_LEFT -> left = true;
            case KeyEvent.VK_RIGHT -> right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> w = false;
            case KeyEvent.VK_A -> a = false;
            case KeyEvent.VK_S -> s = false;
            case KeyEvent.VK_D -> d = false;
            case KeyEvent.VK_UP -> up = false;
            case KeyEvent.VK_DOWN -> down = false;
            case KeyEvent.VK_LEFT -> left = false;
            case KeyEvent.VK_RIGHT -> right = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
