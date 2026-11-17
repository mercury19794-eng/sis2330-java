# estadisticas-hs

Proyecto Haskell (Stack) de la Semana 12 de SIS2330: estadisticas de una lista
de estudiantes, donde cada estudiante es una tupla `(nombre, nota)`.

## Modulos

- `src/Estadisticas.hs` - funciones puras: `promedio`, `masAlto`,
  `ordenarPorNota` (con `sortBy`) y `aprobados` (con comprension de lista, nota >= 51)
- `app/Main.hs` - IO: lee N estudiantes por consola y muestra el reporte,
  sin duplicar la logica de `Estadisticas`

## Como compilar y ejecutar
cat >> README.md <<'EOF'

## Ejemplo

Entrada: 3 estudiantes (Ana 80, Luis 40, Eva 90).

Con 0 estudiantes el programa muestra `No se ingresaron estudiantes.`
