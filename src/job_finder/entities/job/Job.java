package job_finder.entities.job;

import job_finder.entities.JobSeeker;
import job_finder.entities.Recruiter;
import job_finder.value_objects.JobTitle;

import java.io.IOException;
import java.io.Writer;

public abstract class Job {
    private JobTitle title;
    private Recruiter postedBy;

    public Job(JobTitle title) {
        this.title = title;
    }

    public Job() {  }

    public Job(JobTitle jobTitle, Recruiter postedBy) {
        title = jobTitle;
        this.postedBy = postedBy;
    }

    public abstract Boolean canBeAppliedToBy(JobSeeker jobSeeker);

    public void appendTitleToWriter(Writer writer) throws IOException {
        title.appendTo(writer);
    }

    public boolean wasNotPostedBy(Recruiter recruiter) {
        return !wasPostedBy(recruiter);
    }

    public Boolean wasPostedBy(Recruiter recruiter) {
        return recruiter.equals(postedBy);
    }
}
