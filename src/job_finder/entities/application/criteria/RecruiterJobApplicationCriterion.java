package job_finder.entities.application.criteria;

import job_finder.criterion.Criterion;
import job_finder.entities.Recruiter;
import job_finder.entities.application.JobApplication;

public class RecruiterJobApplicationCriterion implements Criterion<JobApplication> {
    private Recruiter recruiter;

    public RecruiterJobApplicationCriterion(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    @Override
    public Boolean isSatisfiedBy(JobApplication application) {
        return application.hasJobPostedBy(recruiter);
    }
}
