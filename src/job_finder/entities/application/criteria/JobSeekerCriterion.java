package job_finder.entities.application.criteria;

import job_finder.criterion.Criterion;
import job_finder.entities.JobSeeker;
import job_finder.entities.application.JobApplication;

public class JobSeekerCriterion implements Criterion<JobApplication> {
    private JobSeeker jobSeeker;

    public JobSeekerCriterion(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    @Override
    public Boolean isSatisfiedBy(JobApplication application) {
        return application.hasJobSeeker(jobSeeker);
    }
}
