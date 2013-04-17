package job_finder.entities.job;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Jobs implements Iterable<Job> {
    List<Job> jobs = new ArrayList<Job>();

    public void add(Job job) {
        jobs.add(job);
    }

    @Override
    public Iterator<Job> iterator() {
        return jobs.iterator();
    }
}
