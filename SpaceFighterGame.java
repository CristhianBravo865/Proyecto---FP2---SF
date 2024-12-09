import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SpaceFighterGame extends JFrame {
    public SpaceFighterGame() {
        setTitle("Space Fighter");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Panel principal
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        
        // Agregar eventos de teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gamePanel.handleKeyPress(e);
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                gamePanel.handleKeyRelease(e);
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SpaceFighterGame game = new SpaceFighterGame();
            game.setVisible(true);
        });
    }
}