package job_finder.workflows;

import job_finder.entities.Recruiter;
import job_finder.entities.application.JobApplications;
import job_finder.entities.application.JobApplicationsAppender;
import job_finder.entities.job.Job;
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

    public void showNumberOfApplicationsPostedFor(Job job, Writer writer) throws IOException {
        JobApplications jobApplications = jobApplicationRepository.findByJob(job);
        writer.write("The job titled '");
        job.appendTitleToWriter(writer);
        Integer size = jobApplications.size();
        if (size == 0) writer.write("' has not been applied to by anyone.");
        if (size == 1) writer.write("' has been applied to by 1 person.");
        if (size > 1) writer.write("' has been applied to by " + size + " people.");
    }

    public void showNumberOfApplicationsByRecruiter(Recruiter recruiter, Writer writer) throws IOException {
        JobApplications jobApplications = jobApplicationRepository.findByRecruiter(recruiter);
        writer.write("The recruiter named '");
        recruiter.appendNameToWriter(writer);
        Integer size = jobApplications.size();
        if (size == 0) writer.write("' has no applications.");
        if (size == 1) writer.write("' has 1 application.");
        if (size > 1) writer.write("' has " + size + " applications.");
    }
}
