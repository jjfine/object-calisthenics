package job_finder.criterion;

public interface Criterion<T> {
    Boolean isSatisfiedBy(T t);
}
