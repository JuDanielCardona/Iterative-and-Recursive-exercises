/**
 *  Ejecicio 4.1
 *  Presentado por: 
 *                  Juan Daniel Cardona Urbano
 *                  Juan Esteban Mosquera Zapata
 *                  Juan Alejandro Brito
 * 
 *  El método principal del programa que solicita al usuario el tamaño de la matriz, genera matrices en espiral
 *  utilizando enfoques iterativos y recursivos, y muestra los tiempos de ejecución de ambos en pantalla.
 */
package Exercise_41;
import javax.swing.JOptionPane;
public class main {
    public static void main(String[] args) {
        // Variables para el tamaño de la matriz, la matriz generada de forma iterativa y la temporización
        int n = 0;
        int[][] matriz_iterativa = null;
        long start_time_capture_nano;
        long end_time_capture_nano;
        long start_time_capture_millis;
        long end_time_capture_millis;
        long execution_time_nano;
        long execution_time_millis;

        // Solicitar al usuario el tamaño de la matriz mediante un cuadro de diálogo
        String matrix_size = JOptionPane.showInputDialog("Ingrese el tamaño de la matriz:");

        try {
            // Convertir el tamaño de la matriz a un entero
            n = Integer.parseInt(matrix_size);
            if (n <= 0) {
                throw new IllegalArgumentException("El tamaño de la matriz debe ser un entero mayor que 0.");
            }

            // Generar y mostrar la matriz en espiral utilizando el enfoque iterativo
            System.out.println("------Ejecución iterativa finalizada------\n");
            start_time_capture_nano = System.nanoTime();
            start_time_capture_millis = System.currentTimeMillis();
            matriz_iterativa = fillSpiral_iterative(n);
            end_time_capture_nano = System.nanoTime();
            end_time_capture_millis = System.currentTimeMillis();
            execution_time_nano = end_time_capture_nano - start_time_capture_nano;
            execution_time_millis = end_time_capture_millis - start_time_capture_millis;
            imprimirMatriz(matriz_iterativa);
            System.out.println("\nTiempo de ejecución (nanosegundos): " + execution_time_nano + "\n");
            System.out.println("Tiempo de ejecución (milisegundos): " + execution_time_millis + "\n");

            // Generar y mostrar la matriz en espiral utilizando el enfoque recursivo
            System.out.println("\n\n------Ejecución recursiva finalizada------\n");
            start_time_capture_nano = System.nanoTime();
            start_time_capture_millis = System.currentTimeMillis();
            int[][] matriz_recursiva = new int[n][n];
            fillSpiral_recursive(matriz_recursiva, 1, 0, 0, n - 1);
            end_time_capture_nano = System.nanoTime();
            end_time_capture_millis = System.currentTimeMillis();
            execution_time_nano = end_time_capture_nano - start_time_capture_nano;
            execution_time_millis = end_time_capture_millis - start_time_capture_millis;
            imprimirMatriz(matriz_recursiva);
            System.out.println("\nTiempo de ejecución (nanosegundos): " + execution_time_nano + "\n");
            System.out.println("Tiempo de ejecución (milisegundos): " + execution_time_millis + "\n");

        } catch (NumberFormatException e) {
            System.err.println("Entrada inválida: Por favor ingrese un número entero válido para el tamaño de la matriz.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método recursivo para llenar la matriz en espiral.
     *
     * @param matriz         La matriz que se está llenando
     * @param num            El número inicial para llenar la matriz
     * @param inicioFila     El índice de fila inicial
     * @param inicioColumna  El índice de columna inicial
     * @param fin            El índice final
     * @return               La matriz en espiral generada
     */
    static void fillSpiral_recursive(int[][] matriz, int num, int inicioFila, int inicioColumna, int fin) {
        if (inicioFila > fin || inicioColumna > fin) {
            return;
        }

        for (int i = inicioFila; i <= fin; i++) {
            matriz[i][inicioColumna] = num++;
        }

        for (int i = inicioColumna + 1; i <= fin; i++) {
            matriz[fin][i] = num++;
        }

        for (int i = fin - 1; i >= inicioFila; i--) {
            matriz[i][fin] = num++;
        }

        for (int i = fin - 1; i > inicioColumna; i--) {
            matriz[inicioFila][i] = num++;
        }

        fillSpiral_recursive(matriz, num, inicioFila + 1, inicioColumna + 1, fin - 1);
    }

    /**
     * Método iterativo para llenar la matriz en espiral.
     *
     * @param n El tamaño de la matriz
     * @return La matriz en espiral generada
     */
    static int[][] fillSpiral_iterative(int n) {
        int[][] matriz = new int[n][n];
        int num = 1;
        int inicioFila = 0;
        int finFila = n - 1;
        int inicioColumna = 0;
        int finColumna = n - 1;

        while (inicioFila <= finFila && inicioColumna <= finColumna) {

            for (int i = inicioFila; i <= finFila; i++) {
                matriz[i][inicioColumna] = num++;
            }
            inicioColumna++;

            for (int i = inicioColumna; i <= finColumna; i++) {
                matriz[finFila][i] = num++;
            }
            finFila--;

            for (int i = finFila; i >= inicioFila; i--) {
                matriz[i][finColumna] = num++;
            }
            finColumna--;

            for (int i = finColumna; i >= inicioColumna; i--) {
                matriz[inicioFila][i] = num++;
            }
            inicioFila++;
        }
        return matriz;
    }

    /**
     * Método para imprimir la matriz.
     *
     * @param matriz La matriz que se va a imprimir
     */
    static void imprimirMatriz(int[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

}
