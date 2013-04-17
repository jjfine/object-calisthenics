package job_finder.entities.application;

import job_finder.entities.JobSeeker;
import job_finder.entities.Recruiter;
import job_finder.entities.job.Job;
import job_finder.value_objects.ApplicantInfo;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.io.Writer;

public class JobApplication {
    private final Job job;
    private final ApplicantInfo applicantInfo;

    public JobApplication(Job job, JobSeeker jobSeeker) {
        this.job = job;
        this.applicantInfo = new ApplicantInfo(jobSeeker);
    }

    public Boolean hasJobSeeker(JobSeeker jobSeeker) {
        return applicantInfo.hasJobSeeker(jobSeeker);
    }

    public Boolean hasJob(Job aJob) {
        return job.equals(aJob);
    }

    public Boolean hasJobPostedBy(Recruiter recruiter) {
        return job.wasPostedBy(recruiter);
    }

    public Boolean appliedOn(LocalDate date) {
        return applicantInfo.hasDate(date);
    }

    public void appendJobSeekerNameToWriter(Writer writer) throws IOException {
        applicantInfo.appendJobSeekerNameToWriter(writer);
    }

    public void appendJobTitleToWriter(Writer writer) throws IOException {
        job.appendTitleToWriter(writer);
    }

    public void appendApplicationDateToWriter(Writer writer) throws IOException {
        applicantInfo.appendApplicationDateToWriter(writer);
    }

    public Boolean isInvalid() { return false; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobApplication that = (JobApplication) o;

        if (!applicantInfo.equals(that.applicantInfo)) return false;
        if (!job.equals(that.job)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = job.hashCode();
        result = 31 * result + applicantInfo.hashCode();
        return result;
    }
}