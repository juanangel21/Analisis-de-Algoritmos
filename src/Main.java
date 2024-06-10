import java.util.Arrays;
import java.util.Random;

public class Main {
    // Método para generar un vector de tamaño N con valores aleatorios entre 0 y N
    public static int[] generarVector(int N) {
        Random random = new Random();
        int[] vector = new int[N];
        for (int i = 0; i < N; i++) {
            vector[i] = random.nextInt(N + 1);
        }
        return vector;
    }

    // Método para medir el tiempo total de las búsquedas y mostrar los resultados
    public static long medirTiempoTotalBusquedas(int[] vector, int N, Busqueda busqueda) {
        long tiempoTotal = 0;
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            int objetivo = random.nextInt(N + 1);
            long inicio = System.nanoTime();
            int resultado = busqueda.buscar(vector, objetivo);
            long fin = System.nanoTime();
            tiempoTotal += (fin - inicio);

            // Imprimir el resultado de la búsqueda
            if (resultado != -1) {
                System.out.printf("Valor %d encontrado en el índice %d%n", objetivo, resultado);
            } else {
                System.out.printf("Valor %d no encontrado%n", objetivo);
            }
        }
        return tiempoTotal;
    }

    // Método de búsqueda secuencial
    public static int busquedaSecuencial(int[] vector, int objetivo) {
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == objetivo) {
                return i;
            }
        }
        return -1;
    }

    // Método de búsqueda binaria
    public static int busquedaBinaria(int[] vector, int objetivo) {
        int izquierda = 0;
        int derecha = vector.length - 1;
        while (izquierda <= derecha) {
            int medio = (izquierda + derecha) / 2;
            if (vector[medio] == objetivo) {
                return medio;
            } else if (vector[medio] < objetivo) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return -1;
    }

    // Método principal
    public static void main(String[] args) {
        int N = 1000;  // Puedes cambiar este valor para probar con diferentes tamaños
        int[] vector = generarVector(N);

        // Imprimir el vector generado
        System.out.println("Vector generado: " + Arrays.toString(vector));

        // Medir tiempo de búsquedas secuenciales
        System.out.println("\nBúsquedas Secuenciales:");
        long tiempoTotalSecuencial = medirTiempoTotalBusquedas(vector, N, Main::busquedaSecuencial);

        // Ordenar el vector para búsquedas binarias
        int[] vectorOrdenado = Arrays.copyOf(vector, vector.length);
        Arrays.sort(vectorOrdenado);

        // Medir tiempo de búsquedas binarias
        System.out.println("\nBúsquedas Binarias:");
        long tiempoTotalBinario = medirTiempoTotalBusquedas(vectorOrdenado, N, Main::busquedaBinaria);

        System.out.printf("Tiempo total búsqueda secuencial: %.10f segundos%n", tiempoTotalSecuencial / 1e9);
        System.out.printf("Tiempo total búsqueda binaria: %.10f segundos%n", tiempoTotalBinario / 1e9);
    }

    // Interfaz funcional para las búsquedas
    @FunctionalInterface
    interface Busqueda {
        int buscar(int[] vector, int objetivo);
    }
}