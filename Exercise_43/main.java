/**
 *  Ejecicio 4.3
 *  Presentado por: 
 *                  Juan Daniel Cardona Urbano
 *                  Juan Esteban Mosquera Zapata
 *                  Juan Alejandro Brito
 * 
 * Método principal que ejecuta la comparación de similitud entre palabras en una matriz.
 * Calcula el tiempo de ejecución tanto para una solución iterativa como recursiva.
 * 
 */
package Exercise_43;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        String[][] matriz = {
                { "cama", "acma" },
                { "oso", "rosa" },
                { "tela", "late" },
                { "amor", "roma" },
                { "gato", "toga" },
                { "perro", "roppet" },
                { "hola", "aloh" },
                { "auto", "tuna" },
                { "calle", "lelac" },
                { "abeja", "ajeba" }
        };

        try {
            // Solución Iterativa
            long inicioIterativoMillis = System.currentTimeMillis();
            long inicioIterativoNano = System.nanoTime();
            List<Integer> posicionesIterativo = filasPalabrasSimilares(matriz, false);
            long finIterativoMillis = System.currentTimeMillis();
            long finIterativoNano = System.nanoTime();
            System.out.println("Resultado Iterativo:");
            System.out.println(posicionesIterativo);
            System.out.println(
                    "Tiempo de ejecución Iterativo (Millis): " + (finIterativoMillis - inicioIterativoMillis) + "ms");
            System.out.println(
                    "Tiempo de ejecución Iterativo (Nano): " + (finIterativoNano - inicioIterativoNano) + "ns");

            // Solución Recursiva
            long inicioRecursivoMillis = System.currentTimeMillis();
            long inicioRecursivoNano = System.nanoTime();
            List<Integer> posicionesRecursivo = filasPalabrasSimilares(matriz, true);
            long finRecursivoMillis = System.currentTimeMillis();
            long finRecursivoNano = System.nanoTime();
            System.out.println("\nResultado Recursivo:");
            System.out.println(posicionesRecursivo);
            System.out.println(
                    "Tiempo de ejecución Recursivo (Millis): " + (finRecursivoMillis - inicioRecursivoMillis) + "ms");
            System.out.println(
                    "Tiempo de ejecución Recursivo (Nano): " + (finRecursivoNano - inicioRecursivoNano) + "ns");
        } catch (Exception e) {
            System.out.println("Se produjo una excepción: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Verifica si dos palabras son similares.
     * 
     * @param palabra1    La primera palabra.
     * @param palabra2    La segunda palabra.
     * @param esRecursivo Indica si se debe utilizar la solución recursiva o
     *                    iterativa.
     * @return True si las palabras son similares, False de lo contrario.
     */
    public static boolean sonSimilares(String palabra1, String palabra2, boolean esRecursivo) {
        if (palabra1.length() != palabra2.length()) {
            return false;
        }

        char[] arregloPalabra2 = palabra2.toCharArray();

        if (esRecursivo) {
            return verificarSimilitudRecursivo(palabra1, arregloPalabra2, 0);
        } else {
            return verificarSimilitudIterativo(palabra1, arregloPalabra2);
        }
    }

    /**
     * Función auxiliar recursiva para verificar la similitud de palabras.
     */
    private static boolean verificarSimilitudRecursivo(String palabra1, char[] palabra2, int indice) {
        if (indice == palabra2.length) {
            return true;
        }
        char letra = palabra2[indice];
        int indiceLetra = palabra1.indexOf(letra);
        if (indiceLetra != -1) {
            String nuevaPalabra1 = palabra1.substring(0, indiceLetra) + palabra1.substring(indiceLetra + 1);
            return verificarSimilitudRecursivo(nuevaPalabra1, palabra2, indice + 1);
        }
        return false;
    }

    /**
     * Función auxiliar iterativa para verificar la similitud de palabras.
     */
    private static boolean verificarSimilitudIterativo(String palabra1, char[] palabra2) {
        for (char letra : palabra1.toCharArray()) {
            boolean encontrado = false;
            for (int i = 0; i < palabra2.length; i++) {
                if (letra == palabra2[i]) {
                    encontrado = true;
                    palabra2[i] = '\0'; // Marcar la letra como utilizada
                    break;
                }
            }
            if (!encontrado) {
                return false;
            }
        }
        return true;
    }

    /**
     * Encuentra las posiciones de las filas con palabras similares en una matriz.
     * 
     * @param matriz      La matriz de pares de palabras.
     * @param esRecursivo Indica si se debe utilizar la solución recursiva o
     *                    iterativa.
     * @return Lista de posiciones de filas con palabras similares.
     */
    public static List<Integer> filasPalabrasSimilares(String[][] matriz, boolean esRecursivo) {
        List<Integer> posiciones = new ArrayList<>();

        for (int i = 0; i < matriz.length; i++) {
            String palabra1 = matriz[i][0];
            String palabra2 = matriz[i][1];
            if (sonSimilares(palabra1, palabra2, esRecursivo)) {
                posiciones.add(i); // Agregar la posición de la fila si las palabras son similares
            }
        }

        return posiciones;
    }
}
