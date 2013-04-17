package job_finder.workflows;

import job_finder.entities.Recruiter;
import job_finder.entities.application.JobApplications;
import job_finder.entities.application.JobApplicationsAppender;
import job_finder.entities.job.Job;
import job_finder.services.repositories.JobApplicationRepository;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class RecruiterJobApplicationWorkflow {
    private JobApplicationRepository jobApplicationRepository;
    private Recruiter recruiter;

    public RecruiterJobApplicationWorkflow(JobApplicationRepository jobApplicationRepository, Recruiter recruiter) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.recruiter = recruiter;
    }

    public void showJobSeekersWhoAppliedTo(Job job, Writer writer) throws IOException {
        if (job.wasNotPostedBy(recruiter)) writer.write("Sorry, you cannot view applications to jobs you didn't post.");
        JobApplications jobApplications = jobApplicationRepository.findByJob(job);
        new JobApplicationsAppender().write(jobApplications, writer);
    }

    public void showApplicantsFromDate(LocalDate date, StringWriter writer) throws IOException {
        JobApplications jobApplications = jobApplicationRepository.findByDateAndRecruiter(date, recruiter);
        new JobApplicationsAppender().write(jobApplications, writer);
    }
}
