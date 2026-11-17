package com.tareas.views;

import com.tareas.Tarea;
import com.tareas.TareaService;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = "tareas", layout = MainLayout.class)
public class TareaDetalleView extends VerticalLayout implements HasUrlParameter<Long> {

    // El servicio lo inyecta Spring por el constructor
    private final TareaService servicio;

    // Aqui se guarda el texto del detalle; setParameter lo rellena despues
    private final Paragraph contenido = new Paragraph();

    // Constructor: recibe el servicio y arma la pantalla (el id aun no se conoce)
    public TareaDetalleView(TareaService servicio) {
        this.servicio = servicio;
        add(new H2("Detalle de la tarea"),
            contenido,
            new RouterLink("Volver a la lista", TareasView.class));
    }

    @Override
    public void setParameter(BeforeEvent evento, Long id) {
        // Puntero a la tarea encontrada: null si el id no existe
        Tarea tarea = servicio.buscarPorId(id);

        if (tarea == null) {
            contenido.setText("No existe ninguna tarea con id " + id);
            return;
        }
        String estado = tarea.completada() ? "completada" : "pendiente";
        contenido.setText("Tarea #" + tarea.id() + ": " + tarea.descripcion() + " (" + estado + ")");
    }
}
