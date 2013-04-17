package job_finder.entities.job.criteria;

import job_finder.criterion.Criterion;
import job_finder.entities.Recruiter;
import job_finder.entities.job.Job;

public class RecruiterJobCriterion implements Criterion<Job> {
    private Recruiter recruiter;

    public RecruiterJobCriterion(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    @Override
    public Boolean isSatisfiedBy(Job job) {
        return job.wasPostedBy(recruiter);
    }
}
