# Gestor de Tareas - Navegacion Multi-Vista

## Que hace
Aplicacion Vaadin con varias pantallas navegables: un listado de tareas con Grid
(con alta, edicion y eliminacion), una vista de detalle por id y una pantalla
"Acerca de". Todas comparten un menu lateral (AppLayout).

## Vistas y rutas
- TareasView: ruta "/" (listado, formulario y enlace "Ver detalle")
- TareaDetalleView: ruta "/tareas/{id}" (recibe el id con HasUrlParameter)
- AcercaDeView: ruta "/acerca" (texto informativo)
- MainLayout: barra superior y menu lateral compartidos

## Como se ejecuta
    cd semana09/gestor-tareas
    mvn spring-boot:run
Luego abrir http://localhost:8080/ en el navegador.

## Que se aprendio
- Varias vistas con @Route y un layout compartido (layout = MainLayout.class).
- RouterLink para navegar sin recargar la pagina.
- HasUrlParameter<Long> para recibir un id desde la URL.
- Manejo del caso "id no encontrado" con un mensaje claro.
- Lista estatica en memoria compartida entre vistas.

## Que quedo pendiente
- Guardar las tareas en una base de datos (hoy se pierden al reiniciar).
