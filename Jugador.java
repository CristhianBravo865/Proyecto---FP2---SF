import java.util.ArrayList;
import java.util.List;

public class Jugador<T extends Nave> {
    private String nombre;
    private List<T> naves;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.naves = new ArrayList<>();
    }

    public void agregarNave(T nave) {
        naves.add(nave);
    }

    public List<T> getNaves() {
        return naves;
    }

    public boolean tieneNaves() {
        return !naves.isEmpty();
    }

    public String getNombre() {
        return nombre;
    }
}
