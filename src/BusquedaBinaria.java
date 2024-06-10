public class BusquedaBinaria {
    public int buscar(int[] vector, int objetivo) {
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
}
