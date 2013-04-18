package job_finder.criterion;

public interface IterableAddable<T> extends Iterable<T> {
    void add(T t);
}
