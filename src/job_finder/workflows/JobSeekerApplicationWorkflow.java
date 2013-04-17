package job_finder.workflows;

import job_finder.entities.JobSeeker;
import job_finder.entities.application.JobApplication;
import job_finder.entities.application.JobApplications;
import job_finder.entities.job.Job;
import job_finder.services.JobApplicationBuilder;
import job_finder.services.repositories.JobApplicationRepository;

import java.io.IOException;
import java.io.Writer;

public class JobSeekerApplicationWorkflow {
    private final JobSeeker jobSeeker;
    private final JobApplicationRepository jobApplicationRepository;

    public JobSeekerApplicationWorkflow(JobSeeker jobSeeker, JobApplicationRepository jobApplicationRepository) {
        this.jobSeeker = jobSeeker;
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public void applyTo(Job job) {
        JobApplication jobApplication = new JobApplicationBuilder().buildJobApplication(job, jobSeeker);
        jobApplicationRepository.saveNewApplicationFor(jobApplication);
    }

    public void showJobsAppliedTo(Writer writer) throws IOException {
        JobApplications jobApplications = jobApplicationRepository.findByJobSeeker(jobSeeker);
        jobApplications.appendWriterWithListOfJobTitles(writer);
    }
}
