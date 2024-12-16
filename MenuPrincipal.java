import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MenuPrincipal {
    private JFrame frameMenu;

    public MenuPrincipal() {
        // Crear la ventana del menú
        frameMenu = new JFrame("Turn-Based Space-Wars");
        frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMenu.setLayout(new BorderLayout());

        // Panel para el título
        JPanel panelTitulo = new JPanel();
        JLabel titulo = new JLabel("Turn-Based Space-Wars");
        titulo.setFont(new Font("Serif", Font.BOLD, 32));
        titulo.setForeground(Color.CYAN);
        panelTitulo.setBackground(Color.BLACK);
        panelTitulo.add(titulo);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1, 10, 10));
        panelBotones.setBackground(Color.DARK_GRAY);

        // Crear botones
        JButton botonJuegoSimple = new JButton("Juego Simple");
        JButton botonJuegoAvanzado = new JButton("Juego Avanzado");
        JButton botonListaGanadores = new JButton("Lista de Ganadores");
        JButton botonCerrarJuego = new JButton("Cerrar Juego");

        // Agregar los botones al panel
        panelBotones.add(botonJuegoSimple);
        panelBotones.add(botonJuegoAvanzado);
        panelBotones.add(botonListaGanadores);
        panelBotones.add(botonCerrarJuego);

        // Añadir paneles a la ventana
        frameMenu.add(panelTitulo, BorderLayout.NORTH);
        frameMenu.add(panelBotones, BorderLayout.CENTER);

        // Acción para "Juego Simple"
        botonJuegoSimple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMenu.dispose(); // Cierra el menú principal
                SwingUtilities.invokeLater(GUI::new); // Lanza el juego actual
            }
        });

        // Acción para "Juego Avanzado"
        botonJuegoAvanzado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frameMenu, 
                        "La modalidad de Juego Avanzado aún no está implementada.", 
                        "Juego Avanzado", 
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Acción para "Lista de Ganadores"
        botonListaGanadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarListaGanadores();
            }
        });

        // Acción para "Cerrar Juego"
        botonCerrarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(frameMenu, 
                        "¿Seguro que deseas salir del juego?", 
                        "Cerrar Juego", 
                        JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    System.exit(0); // Cierra la aplicación
                }
            }
        });

        // Configurar la ventana principal
        frameMenu.setSize(400, 300);
        frameMenu.setLocationRelativeTo(null);
        frameMenu.setVisible(true);
    }

    // Método para leer y mostrar la lista de ganadores
    private void mostrarListaGanadores() {
        StringBuilder ganadores = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("ganador.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                ganadores.append(linea).append("\n");
            }
            if (ganadores.length() == 0) {
                ganadores.append("No hay ganadores registrados aún.");
            }
        } catch (IOException ex) {
            ganadores.append("Error al leer el archivo de ganadores.");
        }

        JOptionPane.showMessageDialog(frameMenu, 
                ganadores.toString(), 
                "Lista de Ganadores", 
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuPrincipal::new);
    }
}
