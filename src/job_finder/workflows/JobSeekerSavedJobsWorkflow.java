package job_finder.workflows;

import job_finder.entities.JobSeeker;
import job_finder.entities.job.Job;
import job_finder.entities.job.Jobs;
import job_finder.entities.job.JobsAppender;
import job_finder.services.repositories.SavedJobs;

import java.io.IOException;
import java.io.StringWriter;

public class JobSeekerSavedJobsWorkflow {
    private final SavedJobs savedJobs;
    private final JobSeeker jobSeeker;

    public JobSeekerSavedJobsWorkflow(SavedJobs savedJobs, JobSeeker jobSeeker) {
        this.savedJobs = savedJobs;
        this.jobSeeker = jobSeeker;
    }

    public void saveJob(Job job) {
        savedJobs.add(jobSeeker, job);
    }

    public void showSavedJobs(StringWriter writer) throws IOException {
        Jobs jobs = savedJobs.findByJobSeeker(jobSeeker);
        new JobsAppender().appendJobListToWriter(jobs, writer);
    }
}
