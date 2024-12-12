import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GamePanel extends JPanel {
    private int playerX = 375; // Posición inicial del jugador
    private int playerY = 500;
    private boolean left, right, up, down; // Flags de movimiento

    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true); // Foco para los eventos del teclado
        requestFocusInWindow(); 

        Timer timer = new Timer(10, e -> updateGame());
        timer.start();

        // Agregar eventos de teclado directamente al panel
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> left = true;
                    case KeyEvent.VK_RIGHT -> right = true;
                    case KeyEvent.VK_UP -> up = true;
                    case KeyEvent.VK_DOWN -> down = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> left = false;
                    case KeyEvent.VK_RIGHT -> right = false;
                    case KeyEvent.VK_UP -> up = false;
                    case KeyEvent.VK_DOWN -> down = false;
                }
            }
        });
    }

    private void updateGame() {
        if (left) playerX -= 5;
        if (right) playerX += 5;
        if (up) playerY -= 5;
        if (down) playerY += 5;

        // Mantener la nave dentro de los límites de la ventana
        playerX = Math.max(0, Math.min(playerX, getWidth() - 50));
        playerY = Math.max(0, Math.min(playerY, getHeight() - 50));

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