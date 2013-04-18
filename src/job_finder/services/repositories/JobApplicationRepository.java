package job_finder.services.repositories;

import job_finder.criterion.Criteria;
import job_finder.entities.JobSeeker;
import job_finder.entities.Recruiter;
import job_finder.entities.application.JobApplication;
import job_finder.entities.application.JobApplications;
import job_finder.entities.application.criteria.ApplicationDateCriterion;
import job_finder.entities.application.criteria.JobCriterion;
import job_finder.entities.application.criteria.JobSeekerCriterion;
import job_finder.entities.application.criteria.RecruiterJobApplicationCriterion;
import job_finder.entities.job.Job;
import org.joda.time.LocalDate;

public class JobApplicationRepository {
    JobApplications applications = new JobApplications();

    public void saveNewApplicationFor(JobApplication jobApplication) {
        if (!jobApplication.isInvalid()) applications.add(jobApplication);
    }

    public JobApplications findByJobSeeker(JobSeeker jobSeeker) {
        Criteria<JobApplication> criteria = new Criteria<JobApplication>().add(new JobSeekerCriterion(jobSeeker));
        return criteria.list(JobApplications.class, applications);
    }

    public JobApplications findByJob(Job job) {
        Criteria<JobApplication> criteria = new Criteria<JobApplication>().add(new JobCriterion(job));
        return criteria.list(JobApplications.class, applications);
    }

    public JobApplications findByDateAndRecruiter(LocalDate date, Recruiter recruiter) {
        Criteria<JobApplication> criteria = new Criteria<JobApplication>().add(new ApplicationDateCriterion(date)).add(new RecruiterJobApplicationCriterion(recruiter));
        return criteria.list(JobApplications.class, applications);
    }

    public JobApplications findByJobAndDate(Job job, LocalDate date) {
        Criteria<JobApplication> criteria = new Criteria<JobApplication>().add(new ApplicationDateCriterion(date)).add(new JobCriterion(job));
        return criteria.list(JobApplications.class, applications);
    }

    public JobApplications findAllJobsPostedOn(LocalDate date) {
        Criteria<JobApplication> criteria = new Criteria<JobApplication>().add(new ApplicationDateCriterion(date));
        return criteria.list(JobApplications.class, applications);
    }

    public JobApplications findByRecruiter(Recruiter recruiter) {
        Criteria<JobApplication> criteria = new Criteria<JobApplication>().add(new RecruiterJobApplicationCriterion(recruiter));
        return criteria.list(JobApplications.class, applications);
    }
}