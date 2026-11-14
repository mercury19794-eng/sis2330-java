import servicio.SistemaEstudiantes;
import modelo.Estudiante;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        // Puntero importante: un solo sistema para toda la sesión
        SistemaEstudiantes sistema = new SistemaEstudiantes();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n1. Registrar 2. Buscar 3. Actualizar 4. Ranking 5. Top N 6. Eliminar 7. Salir");
            System.out.print("Opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());

                switch (opcion) {
                    case 1: {
                        System.out.print("Codigo: ");
                        String cod = sc.nextLine().trim();
                        System.out.print("Nombre: ");
                        String nom = sc.nextLine().trim();
                        System.out.print("Promedio (0.0 a 100.0): ");
                        double prom = Double.parseDouble(sc.nextLine().trim());
                        boolean ok = sistema.registrar(new Estudiante(cod, nom, prom));
                        System.out.println(ok ? "Registrado" : "Codigo duplicado");
                        break;
                    }
                    case 2: {
                        System.out.print("Codigo a buscar: ");
                        Estudiante e = sistema.buscarPorCodigo(sc.nextLine().trim());
                        System.out.println(e != null ? e : "No encontrado");
                        break;
                    }
                    case 3: {
                        System.out.print("Codigo: ");
                        String c2 = sc.nextLine().trim();
                        System.out.print("Nuevo promedio: ");
                        double np = Double.parseDouble(sc.nextLine().trim());
                        System.out.println(sistema.actualizarPromedio(c2, np) ? "OK" : "No encontrado");
                        break;
                    }
                    case 4: {
                        for (Estudiante r : sistema.listarRanking()) {
                            System.out.println(r);
                        }
                        break;
                    }
                    case 5: {
                        System.out.print("N: ");
                        int n = Integer.parseInt(sc.nextLine().trim());
                        System.out.println(sistema.topN(n));
                        break;
                    }
                    case 6: {
                        System.out.print("Codigo a eliminar: ");
                        System.out.println(sistema.eliminar(sc.nextLine().trim()) ? "Eliminado" : "No encontrado");
                        break;
                    }
                    case 7:
                        System.out.println("Adios.");
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Debes escribir un numero valido.");
                opcion = 0;
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
                opcion = 0;
            }
        } while (opcion != 7);

        sc.close();
    }
}
