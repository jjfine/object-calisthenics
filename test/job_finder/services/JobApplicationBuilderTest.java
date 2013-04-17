package job_finder.services;

import job_finder.entities.JobSeeker;
import job_finder.entities.application.JobApplication;
import job_finder.entities.job.Job;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JobApplicationBuilderTest {

    @Test
    public void createAnApplicationWhenJobSeekerIsValidForTheGivenJob() {
        Job jobValidForAnyJobSeeker = new JobValidForAnyJobSeeker();
        JobSeeker jobSeeker = new JobSeeker();
        JobApplicationBuilder underTest = new JobApplicationBuilder();

        JobApplication actual = underTest.buildJobApplication(jobValidForAnyJobSeeker, jobSeeker);
        JobApplication expected = new JobApplication(jobValidForAnyJobSeeker, jobSeeker);

        assertThat(actual, is(expected));
    }

    @Test
    public void returnNullWhenJobSeekerIsNotValidForTheGivenJob() {
        Job jobNotValidForAnyJobSeeker = new JobNotValidForAnyJobSeeker();
        JobSeeker jobSeeker = new JobSeeker();
        JobApplicationBuilder underTest = new JobApplicationBuilder();

        JobApplication actual = underTest.buildJobApplication(jobNotValidForAnyJobSeeker, jobSeeker);

        assertThat(actual.isInvalid(), is(true));
    }

    private class JobValidForAnyJobSeeker extends Job {
        @Override
        public Boolean canBeAppliedToBy(JobSeeker jobSeeker) {
            return true;
        }
    }

    private class JobNotValidForAnyJobSeeker extends Job {
        @Override
        public Boolean canBeAppliedToBy(JobSeeker jobSeeker) {
            return false;
        }
    }
}
