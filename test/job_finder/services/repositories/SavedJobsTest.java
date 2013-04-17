package job_finder.services.repositories;

import job_finder.entities.JobSeeker;
import job_finder.entities.job.Job;
import job_finder.entities.job.ats.AtsJob;
import job_finder.value_objects.JobTitle;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SavedJobsTest {
    @Test
    public void jobSeekerCanSeeAListOfSavedJobs() throws IOException {
        JobSeeker jobSeeker = new JobSeeker();
        Job job = new AtsJob(new JobTitle("Candy Taster"));
        SavedJobs savedJobs = new SavedJobs();
        savedJobs.add(jobSeeker, job);

        StringWriter writer = new StringWriter();

        savedJobs.writeJobsSavedBy(jobSeeker, writer);

        assertThat(writer.toString(), is("- Candy Taster\n"));
    }
}
