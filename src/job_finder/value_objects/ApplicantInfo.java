package job_finder.value_objects;

import job_finder.entities.JobSeeker;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.io.Writer;

public class ApplicantInfo {
    private final LocalDate dateApplied;
    private final JobSeeker jobSeeker;

    public ApplicantInfo(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
        this.dateApplied = new LocalDate();
    }

    public Boolean hasJobSeeker(JobSeeker jobSeeker) {
        return this.jobSeeker.equals(jobSeeker);
    }

    public Boolean hasDate(LocalDate date) {
        return dateApplied.equals(date);
    }

    public void appendJobSeekerNameToWriter(Writer writer) throws IOException {
        jobSeeker.appendNameToWriter(writer);
    }

    public void appendApplicationDateToWriter(Writer writer) throws IOException {
        writer.append(dateApplied.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicantInfo that = (ApplicantInfo) o;

        if (!dateApplied.equals(that.dateApplied)) return false;
        if (!jobSeeker.equals(that.jobSeeker)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dateApplied.hashCode();
        result = 31 * result + jobSeeker.hashCode();
        return result;
    }
}