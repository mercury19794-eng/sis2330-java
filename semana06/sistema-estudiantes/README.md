# Sistema de Estudiantes (Maven + JUnit)

## Descripcion

Proyecto Maven que implementa un sistema de estudiantes con registro, busqueda por
codigo, actualizacion de promedio, eliminacion y ranking ordenado por promedio
descendente. Usa un `HashMap` para buscar por codigo en O(1) y un `TreeSet` para
mantener el ranking siempre ordenado. Incluye una suite de 17 pruebas con JUnit 5.

## Estructura

```
sistema-estudiantes/
  pom.xml
  src/main/java/Principal.java
  src/main/java/modelo/Estudiante.java
  src/main/java/servicio/SistemaEstudiantes.java
  src/test/java/servicio/SistemaEstudiantesTest.java
```

## Como compilar, probar y empaquetar

```
mvn compile                 # compila
mvn test                    # compila y corre los 17 tests
mvn package                 # tests + genera target/sistema-estudiantes-1.0-SNAPSHOT.jar
mvn clean package           # igual, borrando antes target/
java -jar target/sistema-estudiantes-1.0-SNAPSHOT.jar
mvn exec:java               # ejecuta Principal desde Maven
```

## Tests

1. `registrarEstudianteNuevoDevuelveTrueYAumentaLaCantidad` - registro normal.
2. `registrarConCodigoDuplicadoDevuelveFalseYNoAgregaOtro` - codigo repetido.
3. `buscarPorCodigoExistenteDevuelveElEstudiante` - busqueda exitosa.
4. `buscarPorCodigoInexistenteDevuelveNull` - busqueda sin resultado.
5. `actualizarPromedioDeEstudianteExistenteDevuelveTrue` - actualizacion normal.
6. `actualizarPromedioDeEstudianteInexistenteDevuelveFalse` - actualizar a quien no existe.
7. `actualizarPromedioReordenaElRanking` - el ranking cambia al subir un promedio.
8. `actualizarPromedioInvalidoLanzaExcepcionYConservaAlEstudiante` - un promedio invalido no deja al estudiante fuera del ranking.
9. `eliminarEstudianteExistenteDevuelveTrueYLoQuitaDelRanking` - eliminacion normal.
10. `eliminarEstudianteInexistenteDevuelveFalse` - eliminar a quien no existe.
11. `listarRankingConColeccionVaciaDevuelveListaVacia` - caso borde sin datos.
12. `listarRankingOrdenaPorPromedioDescendente` - orden del ranking.
13. `listarRankingConPromediosIgualesDesempataPorCodigo` - empate de promedios.
14. `topNConNMayorAlTotalDevuelveTodosSinLanzarExcepcion` - N mayor que el total.
15. `topNDevuelveSoloLosMejoresN` - top N normal.
16. `crearEstudianteConPromedioFueraDeRangoLanzaExcepcion` - promedio menor a 0 o mayor a 100.
17. `crearEstudianteConCodigoVacioLanzaExcepcion` - codigo en blanco.

Cada test usa `@BeforeEach` para partir de un `SistemaEstudiantes` vacio.

## Salida real de mvn test

```
[[1;34mINFO[m] [1;32mTests run: 17, Failures: 0, Errors: 0, Skipped: 0[m
```
