import javax.swing.JDialog;
import java.awt.Frame;

public class NaveAlienigena extends Nave {
    public NaveAlienigena() {
        this.nombre = "Nave Alienígena";
        this.ataque_pts = 100;
        this.hp = 100;
        this.alcance_movimiento = 3;
        this.alcance_disparo = 4;
        this.direccion = 1; // Movimiento en cualquier dirección
    }

    @Override
    public String mover(int fila, int columna, Nave[][] tablero) {
        if (!posicionValidaTablero(fila, columna)) {
            return "Movimiento fuera del tablero.";
        }

        if (Math.abs(this.fila - fila) > alcance_movimiento || Math.abs(this.columna - columna) > alcance_movimiento) {
            return "Movimiento fuera de alcance.";
        }

        if (tablero[fila][columna] != null) {
            return "Movimiento inválido: hay otra nave en esa posición.";
        }

        tablero[this.fila][this.columna] = null; // Limpia la posición actual
        this.fila = fila;
        this.columna = columna;
        tablero[fila][columna] = this; // Coloca la nave en la nueva posición

        return "Movimiento realizado con éxito.";
    }

    @Override
    public String atacar(int[] coords_objetivo, Nave[][] tablero) {
        Nave nave_objetivo = tablero[coords_objetivo[0]][coords_objetivo[1]];
        if (nave_objetivo == null) {
            return "No hay nada en esa posición.";
        }

        if (nave_objetivo instanceof NaveAlienigena) {
            return "No se permite atacar aliados";
        } else {

            int distanciaFila = Math.abs(this.fila - coords_objetivo[0]);
            int distanciaColumna = Math.abs(this.columna - coords_objetivo[1]);

            if ((direccion == 0 && distanciaFila != 0) ||
                    (direccion == 1 && distanciaFila > alcance_disparo && distanciaColumna > alcance_disparo)) {
                return "El enemigo está fuera de alcance.";
            } else {
                nave_objetivo.hp -= this.ataque_pts;
                nave_objetivo.comprobarEstado();
                return "Ataque realizado con éxito.";
            }
        }
    }

    @Override
    public String atacarAvanzado(int[] coords_objetivo, Nave[][] tablero) {
        Nave nave_objetivo = tablero[coords_objetivo[0]][coords_objetivo[1]];
        if (nave_objetivo == null) {
            return "No hay nada en esa posición.";
        }

        if (nave_objetivo instanceof NaveAlienigena) {
            return "No se permite atacar aliados.";
        } else {
            int distanciaFila = Math.abs(this.fila - coords_objetivo[0]);
            int distanciaColumna = Math.abs(this.columna - coords_objetivo[1]);

            if ((direccion == 0 && distanciaFila != 0) ||
                    (direccion == 1 && distanciaFila > alcance_disparo && distanciaColumna > alcance_disparo)) {
                return "El enemigo está fuera de alcance.";
            } else {
                // Crear y lanzar un JDialog modal
                JDialog dialog = new JDialog((Frame) null, "Space Fight", true); // Modal
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setSize(800, 600);
                dialog.setResizable(false);
                dialog.setLocationRelativeTo(null);

                // Configurar el panel SpaceFight con las naves
                SpaceFight spaceFight = new SpaceFight(this, nave_objetivo);
                dialog.setContentPane(spaceFight);
                dialog.setVisible(true);
                this.comprobarEstado();
                nave_objetivo.comprobarEstado();
                return "\nBatalla avanzada finalizada.";
            }
        }
    }

    @Override
    public String toString() {
        return "NaveAlienigena []" + super.toString();
    }
}
