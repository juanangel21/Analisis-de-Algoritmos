import java.util.Arrays;
import java.util.Random;

public class Ordenacion {
    public static void main(String[] args) {
        int N = 10000000;  // Tamaño del vector
        int[] vector = generarVector(N);

        // Clonar el vector para cada algoritmo
        int[] vectorInsercion = vector.clone();
        int[] vectorMergeSort = vector.clone();
        int[] vectorQuickSort = vector.clone();

        // Medir el tiempo de ejecución de cada algoritmo
        long tiempoInicio, tiempoFin;

        tiempoInicio = System.currentTimeMillis();
        insertionSort(vectorInsercion);
        tiempoFin = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución del algoritmo de inserción: " + (tiempoFin - tiempoInicio) + " ms");

        tiempoInicio = System.currentTimeMillis();
        mergeSort(vectorMergeSort);
        tiempoFin = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución del algoritmo Merge Sort: " + (tiempoFin - tiempoInicio) + " ms");

        tiempoInicio = System.currentTimeMillis();
        quickSort(vectorQuickSort, 0, vectorQuickSort.length - 1);
        tiempoFin = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución del algoritmo Quick Sort: " + (tiempoFin - tiempoInicio) + " ms");
    }

    // Método para generar un vector de tamaño N con valores aleatorios entre 0 y N
    public static int[] generarVector(int N) {
        Random random = new Random();
        int[] vector = new int[N];
        for (int i = 0; i < N; i++) {
            vector[i] = random.nextInt(N + 1);
        }
        return vector;
    }

    // Implementación del algoritmo de ordenación por inserción
    public static void insertionSort(int[] vector) {
        int n = vector.length;
        for (int i = 1; i < n; ++i) {
            int key = vector[i];
            int j = i - 1;

            while (j >= 0 && vector[j] > key) {
                vector[j + 1] = vector[j];
                j = j - 1;
            }
            vector[j + 1] = key;
        }
    }

    // Implementación del algoritmo de ordenación Merge Sort
    public static void mergeSort(int[] vector) {
        if (vector.length < 2)
            return;
        int mid = vector.length / 2;
        int[] left = Arrays.copyOfRange(vector, 0, mid);
        int[] right = Arrays.copyOfRange(vector, mid, vector.length);
        mergeSort(left);
        mergeSort(right);
        merge(vector, left, right);
    }

    public static void merge(int[] result, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
    }

    // Implementación del algoritmo de ordenación Quick Sort
    public static void quickSort(int[] vector, int low, int high) {
        if (low < high) {
            int pi = partition(vector, low, high);
            quickSort(vector, low, pi - 1);
            quickSort(vector, pi + 1, high);
        }
    }

    public static int partition(int[] vector, int low, int high) {
        int pivot = vector[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (vector[j] <= pivot) {
                i++;
                int temp = vector[i];
                vector[i] = vector[j];
                vector[j] = temp;
            }
        }
        int temp = vector[i + 1];
        vector[i + 1] = vector[high];
        vector[high] = temp;

        return i + 1;
    }
}
