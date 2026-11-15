# Gestor de Tareas (Vaadin)

## Que hace
Aplicacion web hecha con Vaadin y Spring Boot. Permite escribir la descripcion
de una tarea, agregarla a una lista en memoria y verla como texto plano en
pantalla. Si el campo esta vacio, muestra un aviso y no agrega nada.

## Requisitos
- JDK 17
- Maven 3.8 o superior

## Como se ejecuta
Luego abrir http://localhost:8080/ en el navegador. La primera vez Vaadin
descarga dependencias y tarda un poco.

## Estructura
- `Application.java` - punto de entrada (@SpringBootApplication)
- `Tarea.java` - record con id, descripcion y completada
- `views/TareasView.java` - vista unica con @Route("")
- `application.properties` - configuracion (puerto 8080)

## Que se aprendio
- Una vista Vaadin es una clase Java anotada con @Route que extiende un layout.
- La interfaz se arma en el constructor y los componentes se agregan con add(...).
- Los eventos se manejan con lambdas que corren en el servidor.
- Los datos viven en memoria y se pierden al reiniciar.

## Que quedo pendiente
Marcar tareas como completadas y guardarlas en una base de datos.
