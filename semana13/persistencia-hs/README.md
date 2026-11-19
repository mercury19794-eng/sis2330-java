# Semana 13 - Persistencia de Estudiantes (Haskell III)

## Descripcion
Proyecto Stack que guarda la lista de estudiantes en `estudiantes.csv` y la
vuelve a cargar al iniciar. Las lineas mal formadas se reportan sin detener
la carga de las validas.

## Formato de estudiantes.csv
Una linea por estudiante, con dos campos separados por coma:

    nombre,nota

Ejemplo: `Ana,80.0`. La nota debe ser un numero entre 0 y 100.

## Manejo de errores con Either String Estudiante
`parsearLinea` es pura y devuelve `Left mensaje` si la linea esta mal formada
(cantidad de campos incorrecta, nota no numerica o fuera de rango) o
`Right estudiante` si es valida. Las validaciones se encadenan con `do` y se
detienen en el primer `Left`. Un error es un dato, nunca una excepcion.
`cargarEstudiantes` devuelve `[Either String Estudiante]` y `Main` muestra
los `Left` como avisos y usa solo los `Right`.

## Como compilar y ejecutar
    cd semana13/persistencia-hs
    stack build
    stack run

El archivo `estudiantes.csv` se crea al agregar el primer estudiante y no se
versiona.
