package job_finder.entities.application;

import job_finder.entities.JobSeeker;
import job_finder.entities.job.Job;

public class InvalidJobApplication extends JobApplication {
    public InvalidJobApplication(Job job, JobSeeker jobSeeker) {
        super(job, jobSeeker);
    }

    @Override
    public Boolean isInvalid() {
        return true;
    }
}
