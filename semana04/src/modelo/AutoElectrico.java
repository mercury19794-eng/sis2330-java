package modelo;

public class AutoElectrico extends Auto implements Electrico {
    private int autonomiaKm;

    // Constructor: super(...) apunta al constructor de Auto; aqui se guarda el dato propio
    public AutoElectrico(String marca, String modelo, double velocidadMax,
                         int numPuertas, int autonomiaKm) {
        super(marca, modelo, velocidadMax, numPuertas);
        this.autonomiaKm = autonomiaKm;
    }

    public int getAutonomiaKm() {
        return autonomiaKm;
    }

    @Override
    public String describir() {
        return super.describir() + ", electrico con autonomia " + autonomiaKm + " km";
    }

    @Override
    public void cargarBateria() {
        System.out.println("Cargando bateria de " + marca + " " + modelo + "...");
    }
}
