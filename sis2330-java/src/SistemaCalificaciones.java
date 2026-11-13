import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class SistemaCalificaciones {

    static double leerNota(Scanner sc, String mensaje) {
        double nota = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print(mensaje);
                nota = sc.nextDouble();
                if (nota < 0 || nota > 100) {
                    System.out.println("Error: la nota debe estar entre 0 y 100.");
                    continue;
                }
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: ingrese un numero valido.");
                sc.next();
            }
        }
        return nota;
    }

    static double promedio(double[] notas) {
        double suma = 0;
        for (double n : notas) {
            suma += n;
        }
        return suma / notas.length;
    }

    static double maximo(double[] notas) {
        double max = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > max) {
                max = notas[i];
            }
        }
        return max;
    }

    static double minimo(double[] notas) {
        double min = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] < min) {
                min = notas[i];
            }
        }
        return min;
    }

    static String estado(double promedio) {
        if (promedio >= 51) {
            return "Aprobado";
        }
        return "Reprobado";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);

        // arreglos donde se guardan los nombres y las notas
        String[] nombres = new String[5];
        double[][] notas = new double[5][3];

        for (int i = 0; i < 5; i++) {
            System.out.print("Nombre del estudiante " + (i + 1) + ": ");
            nombres[i] = sc.nextLine();
            for (int j = 0; j < 3; j++) {
                notas[i][j] = leerNota(sc, "  Nota " + (j + 1) + ": ");
            }
            sc.nextLine();
        }

        System.out.println();
        System.out.println("=== REPORTE FINAL ===");
        System.out.printf("%-15s %9s %7s %7s %-10s%n", "Nombre", "Promedio", "Maximo", "Minimo", "Estado");
        for (int i = 0; i < 5; i++) {
            double prom = promedio(notas[i]);
            System.out.printf("%-15s %9.2f %7.2f %7.2f %-10s%n",
                    nombres[i], prom, maximo(notas[i]), minimo(notas[i]), estado(prom));
        }

        sc.close();
    }
}
