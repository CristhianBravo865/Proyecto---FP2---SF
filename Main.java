import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        NaveAlienigena na=new NaveAlienigena();
        NaveTerricola nt =new NaveTerricola();
        SwingUtilities.invokeLater(() -> {
                    JFrame frame = new JFrame("Space Fight");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setSize(800, 600);
                    frame.setContentPane(new SpaceFight(na, nt)); // Pasar solo las naves
                    frame.setVisible(true);
                });
    }
}
