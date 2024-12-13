public class Interceptor extends NaveAlienigena {
    public Interceptor() {
        this.nombre = "Interceptor";
        this.vida = 100;
        this.movimiento = 3;
        this.daño = 25;
        this.alcance = 1;
    }

    @Override
    public void habilidadEspecial() {
        // El daño aumenta si hay 2 o más interceptores cercanos
        System.out.println("Interceptor se prepara para aumentar daño si hay aliados cerca.");
    }
}
