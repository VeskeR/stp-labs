import java.util.Arrays;

/**
 * Created by Andrew on 9/15/16.
 */
public class Main {
    public static void main(String[] args) {
        int arrsLength = 4;
        int[][] arrs = new int[arrsLength][];
        for (int i = 0; i < arrsLength; i++) {
            arrs[i] = new int[100 * (int)(Math.pow(10, i))];
            fillArray(arrs[i]);
        }

        String sortMethod = "Bubble";
        for (int i = 0; i < arrs.length; i++) {
            int[] copy = copyArray(arrs[i]);
            int arrLength = arrs[i].length;
            long beforeTest = System.currentTimeMillis();
            bubbleSort(copy);
            if (arrLength == 100) {
                System.out.println(Arrays.toString(copy));
            }
            long afterTest = System.currentTimeMillis();
            System.out.println(sortMethod + " sort method sorted " + arrLength + " elementh. Time elapsed in ms: " + (afterTest - beforeTest));
        }

        sortMethod = "Insertion";
        for (int i = 0; i < arrs.length; i++) {
            int[] copy = copyArray(arrs[i]);
            int arrLength = arrs[i].length;
            long beforeTest = System.currentTimeMillis();
            copy = insertionSort(copy);
            if (arrLength == 100) {
                System.out.println(Arrays.toString(copy));
            }
            long afterTest = System.currentTimeMillis();
            System.out.println(sortMethod + " sort method sorted " + arrLength + " elementh. Time elapsed in ms: " + (afterTest - beforeTest));
        }

        sortMethod = "Quick";
        for (int i = 0; i < arrs.length; i++) {
            int[] copy = arrs[i];
            int arrLength = arrs[i].length;
            long beforeTest = System.currentTimeMillis();
            quickSort(copy, 0, arrLength - 1);
            if (arrLength == 100) {
                System.out.println(Arrays.toString(copy));
            }
            long afterTest = System.currentTimeMillis();
            System.out.println(sortMethod + " sort method sorted " + arrLength + " elementh. Time elapsed in ms: " + (afterTest - beforeTest));
        }
    }

    public static void fillArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = randomWithRange(0, 1000000);
        }
    }

    public static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public static int[] copyArray(int[] src) {
        int[] dest = new int[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }

    public static void bubbleSort(int[] array) {
        int j;
        boolean flag = true;
        int temp;

        while ( flag )
        {
            flag= false;
            for( j=0;  j < array.length -1;  j++ )
            {
                if ( array[ j ] > array[j+1] )
                {
                    temp = array[ j ];
                    array[ j ] = array[ j+1 ];
                    array[ j+1 ] = temp;
                    flag = true;
                }
            }
        }
    }

    public static int[] insertionSort(int[] array) {
        int[] copy = copyArray(array);

        for (int i = 0; i < copy.length; i++) {
            int temp = copy[i];
            int j =i-1;
            while(j >= 0 && copy[j] > temp) {
                copy[j + 1] = copy[j];
                j--;
            }
            copy[j + 1] = temp;
        }

        return copy;
    }

    public static void quickSort(int[] array, int low, int high) {
        if (array == null || array.length == 0)
            return;
        if (low >= high)
            return;
        int middle = low + (high - low) / 2;
        int pivot = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }

            while (array[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
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
}
