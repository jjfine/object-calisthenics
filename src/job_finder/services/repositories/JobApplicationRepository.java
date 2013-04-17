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

import java.io.IOException;
import java.io.Writer;

public class JobApplicationRepository {
    JobApplications applications = new JobApplications();

    public void saveNewApplicationFor(JobApplication jobApplication) {
        if (jobApplication == null) return;
        applications.add(jobApplication);
    }

    public JobApplications findByJobApplicationCriteria(Criteria<JobApplication> criteria) {
        JobApplications jobApplications = new JobApplications();
        for (JobApplication application : applications) if (criteria.isSatisfiedBy(application)) jobApplications.add(application);
        return jobApplications;
    }

    JobApplications findByJobSeeker(JobSeeker jobSeeker) {
        Criteria<JobApplication> criteria = new Criteria<JobApplication>(new JobSeekerCriterion(jobSeeker));
        return findByJobApplicationCriteria(criteria);
    }

    public void showJobsByJobSeeker(JobSeeker jobSeeker, Writer writer) throws IOException {
        JobApplications jobApplications = findByJobSeeker(jobSeeker);
        jobApplications.appendWriterWithListOfJobTitles(writer);
    }

    public JobApplications findByJob(Job job) {
        Criteria<JobApplication> criteria = new Criteria<JobApplication>(new JobCriterion(job));
        return findByJobApplicationCriteria(criteria);
    }

    public JobApplications findByDateAndRecruiter(LocalDate date, Recruiter recruiter) {
        Criteria<JobApplication> criteria = new Criteria<JobApplication>(new ApplicationDateCriterion(date));
        criteria.add(new RecruiterJobApplicationCriterion(recruiter));
        return findByJobApplicationCriteria(criteria);
    }
}