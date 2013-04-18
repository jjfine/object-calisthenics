package job_finder.services.repositories;

import job_finder.criterion.Criteria;
import job_finder.entities.Recruiter;
import job_finder.entities.job.Job;
import job_finder.entities.job.Jobs;
import job_finder.entities.job.criteria.RecruiterJobCriterion;

public class JobRepository {
    Jobs jobs = new Jobs();

    public Jobs findJobsByRecruiter(Recruiter recruiter) {
        return new Criteria<Job>().add(new RecruiterJobCriterion(recruiter)).list(Jobs.class, jobs);
    }

    public void post(Job job) {
        jobs.add(job);
    }
}
