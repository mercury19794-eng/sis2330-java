# Gestor de Tareas - Spring Boot + Vaadin + Persistencia

## Que hace
Aplicacion Vaadin con Spring Boot para gestionar tareas: listado con Grid,
alta, edicion, marcado como completada y eliminacion, mas una vista de detalle
por id. Los datos pasan por un TareaService inyectado y se guardan en
tareas.json con Gson, asi que sobreviven al reinicio del servidor. Se empaqueta
como jar ejecutable.

## Archivos
- Tarea.java - record inmutable (id, descripcion, completada)
- TareaService.java - servicio con la logica y la persistencia en tareas.json
- views/TareasView.java - Grid y formulario, usa el servicio
- views/TareaDetalleView.java - detalle por id, usa el servicio
- views/MainLayout.java - layout con menu lateral
- views/TareaEditable.java - clase mutable para el Binder
- application.properties - puerto y modo produccion

## Como se ejecuta
    cd semana10/gestor-tareas
    mvn clean package -Pproduction
    java -jar target/*.jar
Luego abrir http://localhost:8080/ en el navegador. Para cambiar el puerto:
    SERVER_PORT=9090 java -jar target/*.jar
Ejecutar siempre desde la misma carpeta: tareas.json se crea ahi.

## Que se aprendio
- @Service e inyeccion de dependencias por constructor en las vistas.
- Persistencia JSON con Gson y TypeToken.
- synchronized para proteger la lista compartida entre sesiones.
- vaadin.productionMode=true exige construir el frontend: sin el perfil
  production el jar no trae index.html y la app da error 500.
- Empaquetar un jar ejecutable y cambiar el puerto sin tocar el codigo.

## Que quedo pendiente
- Reemplazar tareas.json por una base de datos (JPA) para varios usuarios.
