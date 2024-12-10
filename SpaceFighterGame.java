import javax.swing.*;

public class SpaceFighterGame extends JFrame {
    public SpaceFighterGame() {
        setTitle("Space Fighter");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel Para juego
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SpaceFighterGame game = new SpaceFighterGame();
            game.setVisible(true);
        });
    }
}