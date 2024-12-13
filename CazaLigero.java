public class CazaLigero extends NaveTerricola {
    private boolean esquivoPrimerAtaque;

    public CazaLigero() {
        this.nombre = "Caza Ligero";
        this.vida = 100;
        this.movimiento = 3;
        this.daño = 25;
        this.alcance = 1;
        this.esquivoPrimerAtaque = false;
    }

    @Override
    public void habilidadEspecial() {
        if (!esquivoPrimerAtaque) {
            esquivoPrimerAtaque = true;
            System.out.println("Caza Ligero esquivará el próximo ataque.");
        } else {
            System.out.println("Caza Ligero ya utilizó su habilidad especial.");
        }
    }

    @Override
    public void atacar(Nave objetivo, Tablero tablero) {
        super.atacar(objetivo, tablero);
        esquivoPrimerAtaque = false; // Se desactiva la habilidad al atacar
    }
}
