public class Main {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();

        // Creación de jugadores
        Jugador<NaveTerricola> jugador1 = new Jugador<>("Jugador 1");
        jugador1.agregarNave(new CazaLigero());

        Jugador<NaveAlienigena> jugador2 = new Jugador<>("Jugador 2");
        jugador2.agregarNave(new Interceptor());

        // Posicionamiento de naves
        tablero.posicionarNaves(jugador1, 8, 15); // Parte inferior para el jugador 1
        tablero.posicionarNaves(jugador2, 0, 7);  // Parte superior para el jugador 2

        // Mostrar GUI
        new GUI(tablero, jugador1, jugador2).mostrar();
    }
}
