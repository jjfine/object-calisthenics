package job_finder.entities.job.jreq;

import job_finder.entities.JobSeeker;
import job_finder.entities.job.Job;

public class JReqJob extends Job {
    @Override
    public Boolean canBeAppliedToBy(JobSeeker jobSeeker) {
        return jobSeeker.hasResume();
    }
}
