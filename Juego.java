public class Juego {
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;

    public Juego() {
        Tablero tablero = new Tablero();
        Jugador<NaveTerricola> jugador1 = new Jugador<>("Jugador 1");
        Jugador<NaveAlienigena> jugador2 = new Jugador<>("Jugador 2");
        // Aqui se puden iniciar agregar las naves

        //new GUI(tablero, jugador1, jugador2).mostrar();
    }

    public void iniciarJuego() {
        jugador1.agregarNave(new CazaLigero());
        jugador2.agregarNave(new Interceptor());

        // Posicionamiento de naves
        tablero.posicionarNaves(jugador1, 8, 15); // Parte inferior para el jugador 1
        tablero.posicionarNaves(jugador2, 0, 7); // Parte superior para el jugador 2
        
        new GUI(tablero, jugador1, jugador2).mostrar();

        // Para cambios posteriores
        

    }

    public void gestionarTurno() {
        // Lógica para gestionar turnos
    }

    public boolean haPerdido(Jugador jugador) {
        return jugador.getNaves().isEmpty(); // El jugador pierde si no tiene naves
    }
}