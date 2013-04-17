package job_finder.entities.job;

import job_finder.entities.JobSeeker;
import job_finder.entities.Recruiter;
import job_finder.value_objects.JobTitle;

public class AtsJob extends Job {
    public AtsJob() { super(); }

    public AtsJob(JobTitle title) {
        super(title);
    }

    public AtsJob(JobTitle jobTitle, Recruiter recruiter) {
        super(jobTitle, recruiter);
    }


    @Override
    public Boolean canBeAppliedToBy(JobSeeker jobSeeker) {
        return true;
    }
}
