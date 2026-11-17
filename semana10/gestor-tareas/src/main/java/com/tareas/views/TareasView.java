package com.tareas.views;

import com.tareas.Tarea;
import com.tareas.TareaService;
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

    // El servicio lo inyecta Spring por el constructor: la vista no guarda datos propios
    private final TareaService servicio;

    private final Grid<Tarea> grid = new Grid<>(Tarea.class, false);
    private final TextField descripcion = new TextField("Descripcion");
    private final Checkbox completada = new Checkbox("Completada");
    private final Binder<TareaEditable> binder = new Binder<>(TareaEditable.class);

    // Puntero a la fila seleccionada: null significa que se esta creando una nueva
    private Tarea seleccionActual;

    // Constructor: recibe el servicio y arma la tabla, el formulario y los botones
    public TareasView(TareaService servicio) {
        this.servicio = servicio;

        grid.addColumn(Tarea::id).setHeader("ID");
        grid.addColumn(Tarea::descripcion).setHeader("Descripcion");
        grid.addColumn(t -> t.completada() ? "Si" : "No").setHeader("Completada");
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
        Button nuevo = new Button("Nuevo", evento -> cargarEnFormulario(null));

        VerticalLayout formulario = new VerticalLayout(descripcion, completada,
                new HorizontalLayout(guardar, eliminar, nuevo));

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
            servicio.agregar(editable.getDescripcion());
        } else {
            servicio.actualizar(new Tarea(seleccionActual.id(),
                    editable.getDescripcion(), editable.isCompletada()));
        }
        refrescarGrid();
        cargarEnFormulario(null);
    }

    private void eliminar() {
        if (seleccionActual == null) {
            Notification.show("Selecciona una tarea para eliminar");
            return;
        }
        servicio.eliminar(seleccionActual.id());
        refrescarGrid();
        cargarEnFormulario(null);
    }

    private void refrescarGrid() {
        grid.setItems(servicio.listar());
    }
}
