package app;

import modelo.Tarea;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestorTareas {
    // Aqui se crea y se guarda la lista con todas las tareas
    private static ArrayList<Tarea> tareas = new ArrayList<>();

    private static void agregar(String descripcion) {
        tareas.add(new Tarea(descripcion));
        System.out.println("Tarea agregada.");
    }

    private static void listar() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas.");
            return;
        }
        for (Tarea t : tareas) {
            System.out.println(t);
        }
    }

    private static void completar(int id) {
        for (Tarea t : tareas) {
            if (t.getId() == id) {
                t.marcarCompletada();
                System.out.println("Tarea " + id + " marcada como completada.");
                return;
            }
        }
        System.out.println("No existe una tarea con ID " + id);
    }

    private static void eliminar(int id) {
        boolean eliminado = tareas.removeIf(t -> t.getId() == id);
        System.out.println(eliminado ? "Tarea eliminada." : "No existe una tarea con ID " + id);
    }    // Lee un ID; si no es numero, devuelve -1 sin romper el programa
    private static int leerId(Scanner sc) {
        try {
            int id = sc.nextInt();
            sc.nextLine();
            return id;
        } catch (InputMismatchException e) {
            sc.nextLine();
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== Gestor de Tareas ===");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Listar tareas");
            System.out.println("3. Completar tarea");
            System.out.println("4. Eliminar tarea");
            System.out.println("5. Salir");
            System.out.print("Opcion: ");

            int opcion;
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un numero valido.");
                sc.next();
                continue;
            }
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Descripcion: ");
                    try {
                        agregar(sc.nextLine());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> listar();
                case 3 -> {
                    System.out.print("ID a completar: ");
                    completar(leerId(sc));
                }
                case 4 -> {
                    System.out.print("ID a eliminar: ");
                    eliminar(leerId(sc));
                }
                case 5 -> salir = true;
                default -> System.out.println("Opcion invalida.");
            }
        }
        sc.close();
    }
}
