# Sistema de Estudiantes Avanzado

Sistema de consola para gestionar estudiantes (codigo, nombre y promedio de 0.0 a 100.0).
Permite registrar, buscar por codigo, actualizar el promedio, eliminar, ver el ranking
completo y obtener el top N. Combina tres colecciones del Java Collections Framework,
cada una elegida para lo que hace mejor.

## Por que cada coleccion

- **HashMap<String, Estudiante> (porCodigo):** se usa para buscar, registrar y eliminar
  por codigo en tiempo O(1) promedio. Es una busqueda por clave, justo el problema
  que un ArrayList resuelve mal (recorrer toda la lista, O(n)). Tambien permite
  detectar codigos duplicados al registrar con `containsKey`.
- **TreeSet<Estudiante> (ranking):** mantiene a los estudiantes siempre ordenados por
  promedio descendente, usando el orden natural de `Estudiante` (`Comparable`), con
  desempate por codigo. Asi el ranking nunca se ordena a mano: cada insercion cuesta
  O(log n) y el recorrido sale ya ordenado. El desempate por codigo evita que dos
  estudiantes con el mismo promedio se tomen como duplicados y uno desaparezca.
- **ArrayList<Estudiante> (resultado de topN y listarRanking):** el top N se devuelve
  como una copia en ArrayList y nunca como el TreeSet interno. Asi quien usa el sistema
  recibe una lista simple, con acceso por indice, y no puede modificar ni romper el
  ranking desde afuera (encapsulamiento).

Las dos colecciones internas guardan los mismos objetos y se actualizan juntas en
`registrar`, `actualizarPromedio` y `eliminar`. Al actualizar un promedio, el
estudiante se saca del TreeSet antes de cambiarlo y se reinserta despues, porque el
TreeSet calcula la posicion al insertar y un objeto mutado dentro quedaria mal ubicado.

## Estructura

- `src/modelo/Estudiante.java` - datos, orden natural, equals y hashCode por codigo
- `src/servicio/SistemaEstudiantes.java` - HashMap + TreeSet sincronizados
- `src/Principal.java` - menu de consola

## Como compilar y ejecutar

```
cd semana05
javac -d out src/modelo/Estudiante.java src/servicio/SistemaEstudiantes.java src/Principal.java
java -cp out Principal
```

## Ejemplo de salida

Tras registrar A1 Ana (85), B2 Luis (92.5) y C3 Rosa (85), la opcion 4 (Ranking) muestra:

```
#B2 Luis (92.5)
#A1 Ana (85.0)
#C3 Rosa (85.0)
```

Despues de actualizar el promedio de C3 a 99, la opcion 5 (Top N) con N = 2 muestra:

```
[#C3 Rosa (99.0), #B2 Luis (92.5)]
```
