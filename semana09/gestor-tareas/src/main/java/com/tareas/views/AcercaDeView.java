package com.tareas.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "acerca", layout = MainLayout.class)
public class AcercaDeView extends VerticalLayout {

    // Constructor: aqui se arma el contenido de la pantalla
    public AcercaDeView() {
        add(new H2("Acerca de"),
            new Paragraph("Gestor de Tareas con navegacion multi-vista, "
                    + "hecho con Vaadin Router, RouterLink y AppLayout."));
    }
}
