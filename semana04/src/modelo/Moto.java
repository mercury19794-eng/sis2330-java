package modelo;

public class Moto extends Vehiculo {
    private int cilindrada;

    // Constructor: super(...) apunta al constructor de Vehiculo; aqui se guarda el dato propio
    public Moto(String marca, String modelo, double velocidadMax, int cilindrada) {
        super(marca, modelo, velocidadMax);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    @Override
    public String describir() {
        return "Moto " + marca + " " + modelo + ", " + cilindrada + " cc, "
                + velocidadMax + " km/h";
    }
}
