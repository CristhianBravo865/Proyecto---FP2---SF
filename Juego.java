public class Juego {
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    
    public void iniciarJuego() {
        // Lógica para iniciar el juego
    }
    
    public void gestionarTurno() {
        // Lógica para gestionar turnos
    }

    public boolean haPerdido(Jugador jugador) {
        return jugador.getNaves().isEmpty(); // El jugador pierde si no tiene naves
    }
}