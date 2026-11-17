package com.tareas.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    // Constructor: aqui se arma la barra superior y el menu lateral
    public MainLayout() {
        DrawerToggle toggle = new DrawerToggle();

        H1 titulo = new H1("Gestor de Tareas");
        titulo.getStyle().set("font-size", "var(--lumo-font-size-l)").set("margin", "0");

        // Menu lateral: cada RouterLink apunta a una vista con @Route
        VerticalLayout menu = new VerticalLayout(
                new RouterLink("Lista de Tareas", TareasView.class),
                new RouterLink("Acerca de", AcercaDeView.class));

        addToNavbar(toggle, titulo);
        addToDrawer(menu);
    }
}
