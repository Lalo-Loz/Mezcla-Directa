import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class MezclaDirectaPractica {

    public static void intercalarBloques(String archivoF1, String archivoF2, String archivoSalida, int L) {
        try (
                BufferedReader lector1 = new BufferedReader(new FileReader(archivoF1));
                BufferedReader lector2 = new BufferedReader(new FileReader(archivoF2));
                PrintWriter escritor = new PrintWriter(new FileWriter(archivoSalida))
        ) {
            String linea1 = lector1.readLine();
            String linea2 = lector2.readLine();

            int elementosEscritos1 = 0;
            int elementosEscritos2 = 0;

            while (linea1 != null && linea2 != null) {
                // CASO 1: Todavía quedan elementos por comparar en AMBOS bloques de tamaño L
                if (elementosEscritos1 < L && elementosEscritos2 < L) {
                    int val1 = Integer.parseInt(linea1.trim());
                    int val2 = Integer.parseInt(linea2.trim());

                    if (val1 <= val2) {
                        escritor.println(val1);
                        linea1 = lector1.readLine();
                        elementosEscritos1++;
                    } else {
                        escritor.println(val2);
                        linea2 = lector2.readLine();
                        elementosEscritos2++;
                    }
                }
                // CASO 2: El bloque de F2 ya se acabó, vaciamos lo que sobró del bloque de F1
                else if (elementosEscritos1 < L) {
                    escritor.println(linea1.trim());
                    linea1 = lector1.readLine();
                    elementosEscritos1++;
                }
                // CASO 3: El bloque de F1 ya se acabó, vaciamos lo que sobró del bloque de F2
                else if (elementosEscritos2 < L) {
                    escritor.println(linea2.trim());
                    linea2 = lector2.readLine(); // Corregido: antes decía linea1
                    elementosEscritos2++;
                }
                // CASO 4: Ambos bloques llegaron a L, reiniciamos los contadores para el siguiente par de bloques
                else {
                    elementosEscritos1 = 0;
                    elementosEscritos2 = 0;
                }
            }

            // Si F2 se acabó primero, vaciamos todo lo que quede en F1
            while (linea1 != null) {
                escritor.println(linea1.trim());
                linea1 = lector1.readLine();
            }

            // Si F1 se acabó primero, vaciamos todo lo que quede en F2
            while (linea2 != null) {
                escritor.println(linea2.trim());
                linea2 = lector2.readLine();
            }

            System.out.println("Bloques intercalados con éxito!");

        } catch (IOException e) {
            System.out.println("Error en E/S de archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Verifica si tus archivos se llaman "f1.txt" o "f1.txt.txt"
        intercalarBloques("f1.txt", "f2.txt", "f3.txt", 1);
    }
}
