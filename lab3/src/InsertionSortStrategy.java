/**
 * Created by Andrew on 9/29/16.
 */
public class InsertionSortStrategy<T extends Comparable<T>> implements SortStrategy<T> {
    @Override
    public void sort(T[] array) {
        Sort.insertion(array);
    }
}
