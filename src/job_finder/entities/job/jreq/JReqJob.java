package job_finder.entities.job.jreq;

import job_finder.entities.JobSeeker;
import job_finder.entities.job.Job;
import job_finder.value_objects.JobTitle;

public class JReqJob extends Job {
    public JReqJob(JobTitle jobTitle) {
        super(jobTitle);
    }

    @Override
    public Boolean canBeAppliedToBy(JobSeeker jobSeeker) {
        return jobSeeker.hasResume();
    }
}
