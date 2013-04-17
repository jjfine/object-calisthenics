package job_finder.workflows;

import job_finder.entities.Recruiter;
import job_finder.entities.job.AtsJob;
import job_finder.entities.job.Jobs;
import job_finder.entities.job.JobsAppender;
import job_finder.services.repositories.JobRepository;
import job_finder.value_objects.JobTitle;

import java.io.IOException;
import java.io.StringWriter;

public class RecruiterJobPostingWorkflow {
    private Recruiter recruiter;
    private JobRepository jobRepository;

    public RecruiterJobPostingWorkflow(Recruiter recruiter, JobRepository jobRepository) {
        this.recruiter = recruiter;
        this.jobRepository = jobRepository;
    }

    public void postAtsJob(JobTitle title) {
        AtsJob job = new AtsJob(title, recruiter);
        jobRepository.post(job);
    }

    public void showMyJobs(StringWriter writer) throws IOException {
        Jobs jobsByRecruiter = jobRepository.findJobsByRecruiter(recruiter);
        new JobsAppender().appendJobListToWriter(jobsByRecruiter, writer);
    }
}
