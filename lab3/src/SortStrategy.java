/**
 * Created by Andrew on 9/29/16.
 */
public interface SortStrategy<T extends Comparable<T>> {
    public void sort(T[] array);
}
