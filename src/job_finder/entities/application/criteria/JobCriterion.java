package job_finder.entities.application.criteria;

import job_finder.criterion.Criterion;
import job_finder.entities.application.JobApplication;
import job_finder.entities.job.Job;

public class JobCriterion implements Criterion<JobApplication> {
    private Job job;

    public JobCriterion(Job job) {
        this.job = job;
    }

    @Override
    public Boolean isSatisfiedBy(JobApplication application) {
        return application.hasJob(job);
    }
}
