# Gestor de Tareas - Grid y CRUD

## Descripcion
Aplicacion web Vaadin con tabla editable (Grid), formulario validado
(Binder) y operaciones CRUD completas sobre tareas.

## Como ejecutar
Abrir http://localhost:8080

## Estructura
- com.tareas.Tarea -- record inmutable (id, descripcion, completada)
- com.tareas.views.TareaEditable -- clase mutable para el Binder
- com.tareas.views.TareasView -- vista con Grid y formulario

## Nota
TareaEditable existe porque Binder necesita setters y Tarea es inmutable.
Los datos se pierden al reiniciar (almacenamiento en memoria).
