import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GamePanel extends JPanel {
    private int playerX = 375; // Posición inicial del juga
    private int playerY = 500;
    private boolean left, right, up, down; // Flags movimiento

    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        Timer timer = new Timer(10, e -> updateGame());
        timer.start();
    }

    public void handleKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> left = true;
            case KeyEvent.VK_RIGHT -> right = true;
            case KeyEvent.VK_UP -> up = true;
            case KeyEvent.VK_DOWN -> down = true;
        }
    }

    public void handleKeyRelease(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> left = false;
            case KeyEvent.VK_RIGHT -> right = false;
            case KeyEvent.VK_UP -> up = false;
            case KeyEvent.VK_DOWN -> down = false;
        }
    }

    private void updateGame() {
        if (left) playerX -= 5;
        if (right) playerX += 5;
        if (up) playerY -= 5;
        if (down) playerY += 5;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar al jugador
        g.setColor(Color.CYAN);
        g.fillRect(playerX, playerY, 50, 50); // Representa la nave
    }
}