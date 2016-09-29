/**
 * Created by Andrew on 9/29/16.
 */
public class Sort {
    public static <T extends Comparable<T>> void quick(T[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int middle = low + (high - low) / 2;
        T pivot = array[middle];

        int i = low;
        int j = high;

        while (i <= j) {
            while (array[i].compareTo(pivot) < 0) {
                i++;
            }

            while (array[j].compareTo(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }

    public static <T extends Comparable<T>> void insertion(T[] array) {
        for (int i = 0; i < array.length; i++) {
            T temp = array[i];
            int j = i - 1;
            while(j >= 0 && array[j].compareTo(temp) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }

    public static <T extends Comparable<T>> void bubble(T[] array) {
        boolean flag = true;
        T temp;

        while (flag) {
            flag = false;

            for (int j = 0; j < array.length - 1;  j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = true;
                }
            }
        }
    }
}
