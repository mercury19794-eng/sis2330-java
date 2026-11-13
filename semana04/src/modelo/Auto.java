package modelo;

public class Auto extends Vehiculo {
    private int numPuertas;

    // Constructor: super(...) apunta al constructor de Vehiculo; aqui se guarda el dato propio
    public Auto(String marca, String modelo, double velocidadMax, int numPuertas) {
        super(marca, modelo, velocidadMax);
        this.numPuertas = numPuertas;
    }

    public int getNumPuertas() {
        return numPuertas;
    }

    @Override
    public String describir() {
        return "Auto " + marca + " " + modelo + ", " + numPuertas + " puertas, "
                + velocidadMax + " km/h";
    }
}
