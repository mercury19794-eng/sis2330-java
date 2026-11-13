package modelo;

public class Camion extends Vehiculo {
    private double capacidadCarga;

    // Constructor: super(...) apunta al constructor de Vehiculo; aqui se guarda el dato propio
    public Camion(String marca, String modelo, double velocidadMax, double capacidadCarga) {
        super(marca, modelo, velocidadMax);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    @Override
    public String describir() {
        return "Camion " + marca + " " + modelo + ", carga " + capacidadCarga + " t, "
                + velocidadMax + " km/h";
    }
}
