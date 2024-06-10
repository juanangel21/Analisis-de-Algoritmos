public class BusquedaSecuencial {
    public int buscar(int[] vector, int objetivo) {
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == objetivo) {
                return i;
            }
        }
        return -1;
    }
}