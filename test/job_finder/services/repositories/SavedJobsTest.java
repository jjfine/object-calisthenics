package job_finder.services.repositories;

import job_finder.entities.JobSeeker;
import job_finder.entities.job.Job;
import job_finder.entities.job.Jobs;
import job_finder.entities.job.ats.AtsJob;
import job_finder.value_objects.JobTitle;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.IsCollectionContaining.hasItem;

public class SavedJobsTest {

    private JobSeeker jobSeeker;
    private SavedJobs savedJobs;

    @Before
    public void setUp() {
        jobSeeker = new JobSeeker();
        savedJobs = new SavedJobs();
    }

    @Test
    public void returnsAListOfSavedJobs() {
        Job job = new AtsJob(new JobTitle("Candy Taster"));
        savedJobs.add(jobSeeker, job);

        Jobs jobs = savedJobs.findByJobSeeker(jobSeeker);

        assertThat(jobs, hasItem(job));
    }

    @Test
    public void returnsEmptyListIfNoSavedJobs() {
        Jobs actual = savedJobs.findByJobSeeker(jobSeeker);
        assertThat(actual.isEmpty(), is(true));
    }
}
