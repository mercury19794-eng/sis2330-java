# calculadora-hs

Proyecto Haskell (Stack) de la Semana 11 de SIS2330: una calculadora que separa
la logica pura de la interaccion con el usuario.

## Que hace

El programa pide dos numeros por consola y muestra su suma. El calculo lo hace
la funcion pura `suma` de `Lib`; `Main` solo lee y muestra.

## Como correr


Para probar las funciones de forma interactiva:

## Modulos

- `src/Lib.hs` - funciones puras con firma de tipo: `suma`, `doble`, `promedio`
- `app/Main.hs` - IO: lee dos numeros con `getLine` y delega el calculo a `Lib`
