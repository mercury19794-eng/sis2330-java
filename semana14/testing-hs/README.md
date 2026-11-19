# Semana 14 - Testing y Estado en Haskell

## Descripcion
Proyecto Stack `testing-hs`: funciones puras sobre notas de estudiantes, una
suite de tests automatizados (HUnit y QuickCheck) y un modulo con el patron
State para contar consultas sin variables mutables.

## Estructura de modulos
- `src/Notas.hs` - tipo Estudiante y funciones puras: promedio, masAlto,
  ordenarPorNota, aprobados y parsearLinea.
- `src/Historial.hs` - conteo de consultas por nombre con State (Map String Int).
- `app/Main.hs` - procesa lineas nombre,nota, muestra el reporte y usa
  Historial con execState.
- `test/Spec.hs` - 6 tests HUnit y una propiedad QuickCheck.

## Como correr
    stack build && stack test && stack run

## Que aprendi
- Las funciones puras se testean sin mocks ni entorno preparado.
- HUnit prueba casos concretos; QuickCheck prueba propiedades con entradas
  aleatorias (ordenar preserva la longitud).
- State acumula estado sin mutacion: modify produce un estado nuevo y
  execState ejecuta la cadena desde un estado inicial.
- parsearLinea devuelve Either String Estudiante en vez de fallar.
