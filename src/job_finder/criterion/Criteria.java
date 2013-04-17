package job_finder.criterion;

import java.util.ArrayList;
import java.util.List;

public class Criteria<T> {
    List<Criterion<T>> criteria = new ArrayList<Criterion<T>>();

    public Criteria(Criterion<T> criterion) {
        criteria.add(criterion);
    }

    public boolean isSatisfiedBy(T t) {
        for (Criterion<T> criterion : criteria) if (!criterion.isSatisfiedBy(t)) return false;
        return true;
    }

    public void add(Criterion<T> criterion) {
        criteria.add(criterion);
    }
}