public class Fundamentos {

    public static void main(String[] args) {
        int edad = 20;
        double nota = 85.5;
        boolean aprobado = nota >= 51;
        char letra = 'A';
        System.out.println("Edad: " + edad + ", nota: " + nota + ", aprobado: " + aprobado + ", letra: " + letra);

        System.out.println("7 / 2 = " + (7 / 2));
        System.out.println("7.0 / 2 = " + (7.0 / 2));
        System.out.println("(double) 7 / 3 = " + ((double) 7 / 3));
        System.out.println("17 % 5 = " + (17 % 5));

        int puntaje = 75;
        if (puntaje >= 90) {
            System.out.println("Excelente");
        } else if (puntaje >= 51) {
            System.out.println("Aprobado");
        } else {
            System.out.println("Reprobado");
        }

        int dia = 3;
        switch (dia) {
            case 1:
                System.out.println("Lunes");
                break;
            case 2:
                System.out.println("Martes");
                break;
            case 3:
                System.out.println("Miercoles");
                break;
            default:
                System.out.println("Otro dia");
        }

        String tipo = switch (dia) {
            case 6, 7 -> "Fin de semana";
            case 1, 2, 3, 4, 5 -> "Dia laboral";
            default -> "Dia invalido";
        };
        System.out.println("Tipo de dia: " + tipo);

        for (int i = 1; i <= 5; i++) {
            System.out.println("for: " + i);
        }

        int contador = 1;
        while (contador <= 3) {
            System.out.println("while: " + contador);
            contador++;
        }

        int n = 0;
        do {
            System.out.println("do-while: " + n);
            n++;
        } while (n < 2);

        // arreglo 1D donde se guardan las notas
        int[] notas = {85, 90, 78};
        int suma = 0;
        for (int i = 0; i < notas.length; i++) {
            suma += notas[i];
        }
        System.out.println("Suma de notas: " + suma);
        for (int x : notas) {
            System.out.println("nota: " + x);
        }

        // matriz 2D: arreglo de arreglos
        int[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        for (int f = 0; f < m.length; f++) {
            for (int c = 0; c < m[f].length; c++) {
                System.out.print(m[f][c] + " ");
            }
            System.out.println();
        }

        System.out.println("Promedio de notas: " + promedio(notas));
        System.out.println("Promedio de 5.0 y 7.0: " + promedio(5.0, 7.0));
        imprimirLinea();
    }

    static double promedio(int[] valores) {
        int suma = 0;
        for (int v : valores) {
            suma += v;
        }
        return (double) suma / valores.length;
    }

    static double promedio(double a, double b) {
        return (a + b) / 2;
    }

    static void imprimirLinea() {
        System.out.println("------------------");
    }
}
