package job_finder.entities.job;

import job_finder.criterion.IterableAddable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Jobs implements IterableAddable<Job> {
    List<Job> jobs = new ArrayList<Job>();

    public void add(Job job) {
        jobs.add(job);
    }

    @Override
    public Iterator<Job> iterator() {
        return jobs.iterator();
    }

    public Boolean isEmpty() {
        return jobs.isEmpty();
    }
}
