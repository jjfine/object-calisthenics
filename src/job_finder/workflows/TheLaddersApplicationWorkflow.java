package job_finder.workflows;

import job_finder.entities.application.JobApplications;
import job_finder.entities.application.JobApplicationsAppender;
import job_finder.services.repositories.JobApplicationRepository;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.io.Writer;

public class TheLaddersApplicationWorkflow {
    private JobApplicationRepository jobApplicationRepository;

    public TheLaddersApplicationWorkflow(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public void showApplicationsPostedOn(LocalDate date, Writer writer) throws IOException {
        JobApplications jobApplications = jobApplicationRepository.findAllJobsPostedOn(date);
        new JobApplicationsAppender().write(jobApplications, writer);
    }
}
