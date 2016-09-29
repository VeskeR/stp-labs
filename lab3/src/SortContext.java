/**
 * Created by Andrew on 9/29/16.
 */
public class SortContext<T extends Comparable<T>> {
    private SortStrategy<T> strategy;

    public SortContext(SortStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void execute(T[] array) {
        strategy.sort(array);
    }
}
