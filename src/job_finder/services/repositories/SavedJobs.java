package job_finder.services.repositories;

import job_finder.entities.JobSeeker;
import job_finder.entities.job.Job;
import job_finder.entities.job.Jobs;
import job_finder.entities.job.JobsAppender;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class SavedJobs {
    Map<JobSeeker, Jobs> jobsByJobSeeker = new HashMap<JobSeeker, Jobs>();

    public void add(JobSeeker jobSeeker, Job job) {
        Jobs jobs = jobsByJobSeeker.get(jobSeeker);
        if (jobs == null) jobs = new Jobs();
        jobs.add(job);
        jobsByJobSeeker.put(jobSeeker, jobs);
    }

    public Jobs findByJobSeeker(JobSeeker jobSeeker) {
        return jobsByJobSeeker.get(jobSeeker);
    }

    public void writeJobsSavedBy(JobSeeker jobSeeker, Writer writer) throws IOException {
        Jobs jobs = findByJobSeeker(jobSeeker);
        new JobsAppender().appendJobListToWriter(jobs, writer);
    }
}
