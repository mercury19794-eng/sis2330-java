package com.tareas.views;

import java.util.ArrayList;
import java.util.List;

import com.tareas.Tarea;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = "", layout = MainLayout.class)
public class TareasView extends VerticalLayout {

    // Lista estatica en memoria donde se guardan las tareas: la comparte TareaDetalleView
    private static final List<Tarea> TAREAS = new ArrayList<>();
    private static long siguienteId = 1;

    private final Grid<Tarea> grid = new Grid<>(Tarea.class, false);
    private final TextField descripcion = new TextField("Descripcion");
    private final Checkbox completada = new Checkbox("Completada");
    private final Binder<TareaEditable> binder = new Binder<>(TareaEditable.class);

    // Puntero a la fila seleccionada: null significa que se esta creando una nueva
    private Tarea seleccionActual;

    // Constructor: aqui se arma la tabla, el formulario y los botones
    public TareasView() {
        grid.addColumn(Tarea::id).setHeader("ID");
        grid.addColumn(Tarea::descripcion).setHeader("Descripcion");
        grid.addColumn(t -> t.completada() ? "Si" : "No").setHeader("Completada");
        // Columna con un RouterLink por fila hacia /tareas/{id}
        grid.addComponentColumn(t -> new RouterLink("Ver detalle", TareaDetalleView.class, t.id()))
                .setHeader("Detalle");
        grid.asSingleSelect().addValueChangeListener(evento -> cargarEnFormulario(evento.getValue()));

        binder.forField(descripcion)
                .asRequired("La descripcion es obligatoria")
                .bind(TareaEditable::getDescripcion, TareaEditable::setDescripcion);
        binder.forField(completada)
                .bind(TareaEditable::isCompletada, TareaEditable::setCompletada);

        Button guardar = new Button("Guardar", evento -> guardar());
        Button eliminar = new Button("Eliminar", evento -> eliminar());
        Button limpiar = new Button("Nuevo", evento -> cargarEnFormulario(null));

        VerticalLayout formulario = new VerticalLayout(descripcion, completada,
                new HorizontalLayout(guardar, eliminar, limpiar));

        add(new HorizontalLayout(grid, formulario));
        refrescarGrid();
        cargarEnFormulario(null);
    }

    private void cargarEnFormulario(Tarea tarea) {
        seleccionActual = tarea;
        TareaEditable editable = new TareaEditable();
        if (tarea != null) {
            editable.setId(tarea.id());
            editable.setDescripcion(tarea.descripcion());
            editable.setCompletada(tarea.completada());
        }
        binder.readBean(editable);
    }

    private void guardar() {
        TareaEditable editable = new TareaEditable();
        if (!binder.writeBeanIfValid(editable)) {
            Notification.show("Revisa los datos del formulario");
            return;
        }

        if (seleccionActual == null) {
            // Aqui se guarda la tarea nueva en la lista
            TAREAS.add(new Tarea(siguienteId++, editable.getDescripcion(), editable.isCompletada()));
        } else {
            // Se busca por id (no por referencia) y se reemplaza por una Tarea nueva
            long id = seleccionActual.id();
            TAREAS.removeIf(t -> t.id() == id);
            TAREAS.add(new Tarea(id, editable.getDescripcion(), editable.isCompletada()));
        }
        refrescarGrid();
        cargarEnFormulario(null);
    }

    private void eliminar() {
        if (seleccionActual == null) {
            Notification.show("Selecciona una tarea para eliminar");
            return;
        }
        TAREAS.removeIf(t -> t.id() == seleccionActual.id());
        refrescarGrid();
        cargarEnFormulario(null);
    }

    private void refrescarGrid() {
        grid.setItems(TAREAS);
    }

    // Lo usa TareaDetalleView: devuelve null si el id no existe
    public static Tarea buscarPorId(long id) {
        return TAREAS.stream()
                .filter(t -> t.id() == id)
                .findFirst()
                .orElse(null);
    }
}
