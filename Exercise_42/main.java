/**
 * Ejecicio 4.2
 * Presentado por:
 * Juan Daniel Cardona Urbano
 * Juan Esteban Mosquera Zapata
 * Juan Alejandro Brito
 *
 * Este programa procesa una matriz dada para encontrar los menores multiplicadores
 * que, al ser multiplicados por los elementos de la matriz, producen un cuadrado perfecto.
 * Proporciona implementaciones tanto iterativas como recursivas para este procesamiento.
 */
package Exercise_42;

import java.util.Arrays;

public class main {

    public static void main(String[] args) {
        int[][] matriz = {{1, 4, 9, 16},
                {25, 36, 64, 81},
                {5, 10, 18, 6},
                {3, 17, 15, 7}};

        try {
            // Procesamiento iterativo
            long inicioIterativoMillis = System.currentTimeMillis();
            long inicioIterativoNano = System.nanoTime();
            int[][] resultadoIterativo = procesarMatrizIterativo(matriz);
            long finIterativoMillis = System.currentTimeMillis();
            long finIterativoNano = System.nanoTime();
            System.out.println("\nResultado Iterativo:");
            imprimirMatriz(resultadoIterativo);
            System.out.println("Tiempo de ejecución Iterativo (Millis): " + (finIterativoMillis - inicioIterativoMillis) + " ms");
            System.out.println("Tiempo de ejecución Iterativo (Nano): " + (finIterativoNano - inicioIterativoNano) + " ns");

            // Procesamiento recursivo
            long inicioRecursivoMillis = System.currentTimeMillis();
            long inicioRecursivoNano = System.nanoTime();
            int[][] resultadoRecursivo = procesarMatrizRecursivo(matriz);
            long finRecursivoMillis = System.currentTimeMillis();
            long finRecursivoNano = System.nanoTime();
            System.out.println("\nResultado Recursivo:");
            imprimirMatriz(resultadoRecursivo);
            System.out.println("Tiempo de ejecución Recursivo (Millis): " + (finRecursivoMillis - inicioRecursivoMillis) + " ms");
            System.out.println("Tiempo de ejecución Recursivo (Nano): " + (finRecursivoNano - inicioRecursivoNano) + " ns\n");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean esCuadradoPerfecto(int n) {
        int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    }

    public static int menorNumeroMultiplicador(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("El número no puede ser negativo o cero.");
        }
        if (esCuadradoPerfecto(n)) {
            return 1;
        }
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0 && esCuadradoPerfecto(n / i)) {
                return i;
            }
        }
        return n;
    }

    public static int[][] procesarMatrizIterativo(int[][] matriz) {
        if (matriz == null || matriz.length == 0 || matriz[0].length == 0) {
            throw new IllegalArgumentException("La matriz no puede ser nula o vacía.");
        }

        int[][] result = new int[matriz.length][matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                result[i][j] = menorNumeroMultiplicador(matriz[i][j]);
            }
        }

        return result;
    }

    private static int menorNumeroMultiplicadorRecursivo(int n, int divisor) {
        if (n <= 0 || divisor <= 0) {
            throw new IllegalArgumentException("Los argumentos no pueden ser negativos o cero.");
        }
        if (esCuadradoPerfecto(n)) {
            return 1;
        }
        if (divisor > n / 2) {
            return n;
        }
        if (n % divisor == 0 && esCuadradoPerfecto(n / divisor)) {
            return divisor;
        }
        return menorNumeroMultiplicadorRecursivo(n, divisor + 1);
    }

    public static int[][] procesarMatrizRecursivo(int[][] matriz) {
        if (matriz == null || matriz.length == 0 || matriz[0].length == 0) {
            throw new IllegalArgumentException("La matriz no puede ser nula o vacía.");
        }

        int[][] result = new int[matriz.length][matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                result[i][j] = menorNumeroMultiplicadorRecursivo(matriz[i][j], 2);
            }
        }

        return result;
    }

    public static void imprimirMatriz(int[][] matriz) {
        if (matriz == null || matriz.length == 0 || matriz[0].length == 0) {
            throw new IllegalArgumentException("La matriz no puede ser nula o vacía.");
        }

        for (int[] fila : matriz) {
            System.out.println(Arrays.toString(fila));
        }
    }
}
