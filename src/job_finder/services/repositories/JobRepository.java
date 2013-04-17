package job_finder.services.repositories;

import job_finder.criterion.Criteria;
import job_finder.entities.Recruiter;
import job_finder.entities.job.Job;
import job_finder.entities.job.Jobs;
import job_finder.entities.job.criteria.RecruiterJobCriterion;

public class JobRepository {
    Jobs jobs = new Jobs();

    public Jobs findByCriteria(Criteria<Job> criteria) {
        Jobs jobsSatisfyingCriteria = new Jobs();
        for (Job job : jobs) if (criteria.isSatisfiedBy(job)) jobsSatisfyingCriteria.add(job);
        return jobsSatisfyingCriteria;
    }

    public Jobs findJobsByRecruiter(Recruiter recruiter) {
        return findByCriteria(new Criteria<Job>(new RecruiterJobCriterion(recruiter)));
    }

    public void post(Job job) {
        jobs.add(job);
    }
}
