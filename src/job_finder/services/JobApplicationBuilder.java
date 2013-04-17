package job_finder.services;

import job_finder.entities.JobSeeker;
import job_finder.entities.application.InvalidJobApplication;
import job_finder.entities.application.JobApplication;
import job_finder.entities.job.Job;

public class JobApplicationBuilder {
    public JobApplicationBuilder() {
    }

    public JobApplication buildJobApplication(Job job, JobSeeker jobSeeker) {
        if (job.canBeAppliedToBy(jobSeeker)) return new JobApplication(job, jobSeeker);
        return new InvalidJobApplication(job, jobSeeker);
    }
}