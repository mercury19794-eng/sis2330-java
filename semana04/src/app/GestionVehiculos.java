package app;

import modelo.Auto;
import modelo.AutoElectrico;
import modelo.Camion;
import modelo.Electrico;
import modelo.Moto;
import modelo.Vehiculo;
import java.util.ArrayList;

public class GestionVehiculos {
    public static void main(String[] args) {
        // Aqui se crea la lista que guarda todos los vehiculos, declarada con el tipo padre
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();

        vehiculos.add(new Auto("Toyota", "Corolla", 180, 4));
        vehiculos.add(new Moto("Honda", "CB500", 190, 500));
        vehiculos.add(new Camion("Volvo", "FH16", 120, 25.5));
        vehiculos.add(new AutoElectrico("Tesla", "Model 3", 225, 4, 500));

        for (Vehiculo v : vehiculos) {
            System.out.println(v.describir());
            if (v instanceof Electrico e) {
                e.cargarBateria();
            }
        }
    }
}
