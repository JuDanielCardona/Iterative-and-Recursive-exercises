/**
 *  Ejecicio 4.5
 *  Presentado por: 
 *                  Juan Daniel Cardona Urbano
 *                  Juan Esteban Mosquera Zapata
 *                  Juan Alejandro Brito
 * 
 * Este programa encuentra los números polidivisibles en una matriz de enteros
 * y calcula sus tiempos de ejecución tanto de forma iterativa como recursiva.
 */
package Exercise_45;
import java.util.ArrayList;
import java.util.List;
public class main {
    public static void main(String[] args) {
        // Matriz de ejemplo
        int[][] matriz = {
                {2016, 789584, 201},
                {4233, 42, 423}
        };

        // Verificación y cálculo del tiempo de ejecución de la solución iterativa
        long startTimeIterativoMillis = System.currentTimeMillis();
        long startTimeIterativoNano = System.nanoTime();
        List<Integer> polidivisiblesIterativo = obtenerPolidivisiblesIterativo(matriz);
        long endTimeIterativoMillis = System.currentTimeMillis();
        long endTimeIterativoNano = System.nanoTime();

        // Verificación y cálculo del tiempo de ejecución de la solución recursiva
        long startTimeRecursivoMillis = System.currentTimeMillis();
        long startTimeRecursivoNano = System.nanoTime();
        List<Integer> polidivisiblesRecursivo = obtenerPolidivisiblesRecursivo(matriz);
        long endTimeRecursivoMillis = System.currentTimeMillis();
        long endTimeRecursivoNano = System.nanoTime();

        // Impresión de los resultados y tiempos de ejecución
        System.out.println("\nNúmeros polidivisibles (Iterativo): " + polidivisiblesIterativo);
        System.out.println("Tiempo de ejecución (Iterativo) (Millis): " + (endTimeIterativoMillis - startTimeIterativoMillis) + " milisegundos");
        System.out.println("Tiempo de ejecución (Iterativo) (Nano): " + (endTimeIterativoNano - startTimeIterativoNano) + " nanosegundos\n");

        System.out.println("\nNúmeros polidivisibles (Recursivo): " + polidivisiblesRecursivo);
        System.out.println("Tiempo de ejecución (Recursivo) (Millis): " + (endTimeRecursivoMillis - startTimeRecursivoMillis) + " milisegundos");
        System.out.println("Tiempo de ejecución (Recursivo) (Nano): " + (endTimeRecursivoNano - startTimeRecursivoNano) + " nanosegundos\n");
    }

    /**
     * Función para verificar si un número es polidivisible de manera iterativa.
     *
     * @param num El número a verificar.
     * @return true si el número es polidivisible, false en caso contrario.
     */
    public static boolean esPolidivisibleIterativo(int num) {
        String numStr = String.valueOf(num);
        for (int i = 1; i <= numStr.length(); i++) {
            String substr = numStr.substring(0, i);
            int subNum = Integer.parseInt(substr);
            if (subNum % i != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Función auxiliar recursiva para verificar si un número es polidivisible.
     *
     * @param numStr Representación en cadena del número.
     * @param index  Índice actual en el proceso recursivo.
     * @return true si el número es polidivisible, false en caso contrario.
     */
    private static boolean esPolidivisibleRecursivoAux(String numStr, int index) {
        if (index > numStr.length()) {
            return true;
        }
        String substr = numStr.substring(0, index);
        int subNum = Integer.parseInt(substr);
        if (subNum % index != 0) {
            return false;
        }
        return esPolidivisibleRecursivoAux(numStr, index + 1);
    }

    /**
     * Función para verificar si un número es polidivisible de manera recursiva.
     *
     * @param num El número a verificar.
     * @return true si el número es polidivisible, false en caso contrario.
     */
    public static boolean esPolidivisibleRecursivo(int num) {
        String numStr = String.valueOf(num);
        return esPolidivisibleRecursivoAux(numStr, 1);
    }

    /**
     * Función para obtener los números polidivisibles de una matriz de enteros de manera iterativa.
     *
     * @param matriz La matriz de enteros.
     * @return Una lista que contiene los números polidivisibles encontrados.
     */
    public static List<Integer> obtenerPolidivisiblesIterativo(int[][] matriz) {
        List<Integer> polidivisibles = new ArrayList<>();
        for (int[] fila : matriz) {
            for (int num : fila) {
                if (num > 0 && esPolidivisibleIterativo(num)) {
                    polidivisibles.add(num);
                }
            }
        }
        return polidivisibles;
    }

    /**
     * Función para obtener los números polidivisibles de una matriz de enteros de manera recursiva.
     *
     * @param matriz La matriz de enteros.
     * @return Una lista que contiene los números polidivisibles encontrados.
     */
    public static List<Integer> obtenerPolidivisiblesRecursivo(int[][] matriz) {
        List<Integer> polidivisibles = new ArrayList<>();
        for (int[] fila : matriz) {
            for (int num : fila) {
                if (num > 0 && esPolidivisibleRecursivo(num)) {
                    polidivisibles.add(num);
                }
            }
        }
        return polidivisibles;
    }
}
