import java.util.*;

public class Flota<T extends Nave> {
    private ArrayList<T> misNaves;
    private static int num_flotas = 0;
    private static final int num_max_naves = 1;
    private Random random = new Random();
    private int num_naves = 0;

    public Flota() {
        num_flotas++;
        this.misNaves = new ArrayList<>();
    }

    public ArrayList<T> getMisNaves() {
        return misNaves;
    }

    public void eliminarNave(T nave) {
        misNaves.remove(nave);
        num_naves--;
    }

    public void eliminarNave(int index) {
        if (num_naves > 1) {
            misNaves.remove(index);
            num_naves--;
        } else {
            System.out.println("La flota debe tener al menos una nave para continuar.");
        }
    }

    public static <T extends Nave> void eliminarNave(T nave, Flota<T> flota) {
        flota.getMisNaves().remove(nave);
        flota.num_naves--;
    }

    public void agregarALaFlota(T nave) {
        if (num_naves < num_max_naves) {
            misNaves.add(nave);
            num_naves++;
        } else {
            System.out.println("Número máximo de naves alcanzado.");
        }
    }

    public boolean flotaVacia() {
        return num_naves == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Flota ").append(num_flotas).append(":\n");
        sb.append("Número de Naves: ").append(num_naves).append("\n");

        for (T nave : misNaves) {
            sb.append(nave).append("\n");
        }
        return sb.toString();
    }
}
