# Gestion de Vehiculos

Jerarquia de vehiculos con clase abstracta, tres subclases y una interfaz.

## Estructura

- `Vehiculo` (abstracta) -> `Auto` -> `AutoElectrico`
- `Vehiculo` -> `Moto`, `Camion`
- `Electrico` (interfaz) implementada solo por `AutoElectrico`

## Como se ejecuta

    cd semana04
    javac src/modelo/*.java src/app/*.java
    java -cp src app.GestionVehiculos

## Que se aprendio

Herencia con extends y super, polimorfismo con @Override y binding dinamico,
clases abstractas, interfaces e instanceof.
