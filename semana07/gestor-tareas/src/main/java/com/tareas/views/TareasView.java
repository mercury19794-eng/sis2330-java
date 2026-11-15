package com.tareas.views;

import java.util.ArrayList;
import java.util.List;

import com.tareas.Tarea;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class TareasView extends VerticalLayout {

    // Lista en memoria donde se guardan las tareas (se pierde al reiniciar)
    private final List<Tarea> tareas = new ArrayList<>();

    // Contador del id: se incrementa con cada tarea agregada
    private long siguienteId = 1;

    private final TextField campoDescripcion = new TextField("Descripcion de la tarea");
    private final Div listaTareas = new Div();

    // Constructor: aqui se arma toda la interfaz
    public TareasView() {
        Button botonAgregar = new Button("Agregar", evento -> agregarTarea());

        add(new H2("Gestor de Tareas"), campoDescripcion, botonAgregar, listaTareas);

        refrescarLista();
    }

    private void agregarTarea() {
        String descripcion = campoDescripcion.getValue().trim();

        if (descripcion.isEmpty()) {
            Notification.show("Escribe una descripcion antes de agregar");
            return;
        }

        // Aqui se guarda la nueva tarea en la lista
        tareas.add(new Tarea(siguienteId, descripcion, false));
        siguienteId++;

        campoDescripcion.clear();
        refrescarLista();
    }

    private void refrescarLista() {
        listaTareas.removeAll();

        if (tareas.isEmpty()) {
            listaTareas.add(new Paragraph("Aun no hay tareas."));
            return;
        }

        for (Tarea tarea : tareas) {
            String estado = tarea.completada() ? "[hecha]" : "[pendiente]";
            listaTareas.add(new Paragraph(tarea.id() + " - " + tarea.descripcion() + " " + estado));
        }
    }
}
