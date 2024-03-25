
/**
*  Ejecicio 4.4
*  Presentado por: 
*                  Juan Daniel Cardona Urbano
*                  Juan Esteban Mosquera Zapata
*                  Juan Alejandro Brito
* 
 * Método principal que inicializa la matriz de palabras y llama a los métodos
 * para validar el juego de palabras tanto de manera iterativa como recursiva.
 * 
 */
package Exercise_44;

public class main {
    public static void main(String[] args) {
        try {
            String[][] palabras = new String[3][3];
            palabras[0][0] = "sien";
            palabras[0][1] = "encima";
            palabras[0][2] = "mapa";
            palabras[1][0] = "pata";
            palabras[1][1] = "tapa";
            palabras[1][2] = "papa";
            palabras[2][0] = "pato";
            palabras[2][1] = "toma";
            palabras[2][2] = "mama";

            long start_time_capture_nano;
            long end_time_capture_nano;
            long start_time_capture_millis;
            long end_time_capture_millis;
            long execution_time_nano;
            long execution_time_millis;

            System.out.println("------Ejecución iterativa finalizada------\n");
            start_time_capture_nano = System.nanoTime();
            start_time_capture_millis = System.currentTimeMillis();
            boolean resultado = validarSilabas(palabras);
            end_time_capture_nano = System.nanoTime();
            end_time_capture_millis = System.currentTimeMillis();
            execution_time_nano = end_time_capture_nano - start_time_capture_nano;
            execution_time_millis = end_time_capture_millis - start_time_capture_millis;
            System.out
                    .println("Resultado de juego de palabras (iterativo): " + (resultado ? "Correcto" : "Incorrecto"));
            System.out.println("Tiempo de ejecución (nanosegundos): " + execution_time_nano);
            System.out.println("Tiempo de ejecución (milisegundos): " + execution_time_millis + "\n");

            System.out.println("\n\n------Ejecución recursiva finalizada------\n");
            start_time_capture_nano = System.nanoTime();
            start_time_capture_millis = System.currentTimeMillis();
            resultado = validarSilabasRecursivo(palabras, 0, 0);
            end_time_capture_nano = System.nanoTime();
            end_time_capture_millis = System.currentTimeMillis();
            execution_time_nano = end_time_capture_nano - start_time_capture_nano;
            execution_time_millis = end_time_capture_millis - start_time_capture_millis;
            System.out
                    .println("Resultado de juego de palabras (recursivo): " + (resultado ? "Correcto" : "Incorrecto"));
            System.out.println("Tiempo de ejecución (nanosegundos): " + execution_time_nano);
            System.out.println("Tiempo de ejecución (milisegundos): " + execution_time_millis + "\n");
        } catch (Exception e) {
            System.out.println("Se produjo una excepción: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método que valida un juego de palabras de manera iterativa.
     * 
     * @param palabras La matriz de palabras a validar.
     * @return true si el juego de palabras es válido, false de lo contrario.
     */
    public static boolean validarSilabas(String[][] palabras) {
        try {
            for (int i = 0; i < palabras.length; i++) {
                for (int j = 0; j < palabras[i].length; j++) {
                    String p1 = palabras[i][j];
                    String p2;

                    // Si es la última palabra de la fila, comparamos con la primera palabra de la
                    // siguiente fila
                    if (j == palabras[i].length - 1) {
                        if (i == palabras.length - 1) // Última fila, no hay siguiente fila
                            continue;
                        p2 = palabras[i + 1][0];
                    } else {
                        p2 = palabras[i][j + 1];
                    }

                    String silaba = p1.substring(p1.length() - 2);

                    if (!p2.toLowerCase().startsWith(silaba.toLowerCase())) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error durante la validación iterativa: " + e.getMessage(), e);
        }
    }

    /**
     * Método que valida un juego de palabras de manera recursiva.
     * 
     * @param palabras La matriz de palabras a validar.
     * @param fila     La fila actual en la matriz de palabras.
     * @param columna  La columna actual en la matriz de palabras.
     * @return true si el juego de palabras es válido, false de lo contrario.
     */
    public static boolean validarSilabasRecursivo(String[][] palabras, int fila, int columna) {
        try {
            if (fila == palabras.length - 1 && columna == palabras[fila].length - 1) {
                return true; // Si llegamos al final, todas las silabas coinciden
            }

            String p1 = palabras[fila][columna];
            String p2;

            if (columna == palabras[fila].length - 1) {
                if (fila == palabras.length - 1) {
                    return false; // Última fila, no hay siguiente fila
                }
                p2 = palabras[fila + 1][0];
                if (!p2.toLowerCase().startsWith(p1.substring(p1.length() - 2).toLowerCase())) {
                    return false; // No coinciden las silabas
                }
                return validarSilabasRecursivo(palabras, fila + 1, 0); // Pasamos a la siguiente fila
            } else {
                p2 = palabras[fila][columna + 1];
                if (!p2.toLowerCase().startsWith(p1.substring(p1.length() - 2).toLowerCase())) {
                    return false; // No coinciden las silabas
                }
                return validarSilabasRecursivo(palabras, fila, columna + 1); // Pasamos a la siguiente columna
            }
        } catch (Exception e) {
            throw new RuntimeException("Error durante la validación recursiva: " + e.getMessage(), e);
        }
    }
}
