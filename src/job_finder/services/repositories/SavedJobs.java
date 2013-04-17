package job_finder.services.repositories;

import job_finder.entities.JobSeeker;
import job_finder.entities.job.Job;
import job_finder.entities.job.Jobs;

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
        Jobs jobs = jobsByJobSeeker.get(jobSeeker);
        if (jobs == null) return new Jobs();
        return jobs;
    }
}
