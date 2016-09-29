import java.util.Arrays;

/**
 * Created by Andrew on 9/15/16.
 */
public class Main {
    public static void main(String[] args) {
        SortContext<Integer> sortContext = new SortContext<Integer>(new BubbleSortStrategy<Integer>());
        Integer[][] arrays = initArrays(4);

        testSort(arrays, "Bubble", sortContext);

        sortContext.setStrategy(new InsertionSortStrategy<Integer>());
        testSort(arrays, "Insertion", sortContext);

        sortContext.setStrategy(new QuickSortStrategy<Integer>());
        testSort(arrays, "Quick", sortContext);
    }

    public static Integer[][] initArrays(int arraysLength) {
        Integer[][] arrays = new Integer[arraysLength][];
        for (int i = 0; i < arraysLength; i++) {
            arrays[i] = new Integer[100 * (int)(Math.pow(10, i))];
            fillArray(arrays[i]);
        }

        return arrays;
    }

    public static void fillArray(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = randomWithRange(0, 1000000);
        }
    }

    public static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public static Integer[] copyArray(Integer[] src) {
        Integer[] dest = new Integer[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }

    public static void testSort(Integer[][] arrays, String sortMethod, SortContext<Integer> sortContext) {
        for (int i = 0; i < arrays.length; i++) {
            Integer[] copy = copyArray(arrays[i]);
            int arrLength = arrays[i].length;
            long beforeTest = System.currentTimeMillis();
            sortContext.execute(copy);
            if (arrLength == 100) {
                System.out.println(Arrays.toString(copy));
            }
            long afterTest = System.currentTimeMillis();
            System.out.println(sortMethod + " sort method sorted " + arrLength + " elements. Time elapsed in ms: " + (afterTest - beforeTest));
        }
    }
}
