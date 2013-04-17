package job_finder.entities.job;

import job_finder.entities.JobSeeker;

public class JReqJob extends Job {
    @Override
    public Boolean canBeAppliedToBy(JobSeeker jobSeeker) {
        return jobSeeker.hasResume();
    }
}
