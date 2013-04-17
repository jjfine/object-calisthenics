package job_finder.workflows;

import job_finder.entities.JobSeeker;
import job_finder.entities.job.ats.AtsJob;
import job_finder.services.repositories.SavedJobs;
import job_finder.value_objects.JobTitle;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class JobSeekerSavedJobsTest {
    @Test
    public void jobSeekerCanSeeAListOfJobsTheySaved() throws IOException {
        JobSeekerSavedJobsWorkflow underTest = new JobSeekerSavedJobsWorkflow(new SavedJobs(), new JobSeeker());
        underTest.saveJob(new AtsJob(new JobTitle("Astronaut")));
        StringWriter writer = new StringWriter();
        underTest.showSavedJobs(writer);

        assertThat(writer.toString(), containsString("Astronaut"));
    }

    @Test
    public void jobSeekerSeesErrorMessageIfTheySavedNoJobs() throws IOException {
        JobSeekerSavedJobsWorkflow underTest = new JobSeekerSavedJobsWorkflow(new SavedJobs(), new JobSeeker());
        StringWriter writer = new StringWriter();
        underTest.showSavedJobs(writer);

        assertThat(writer.toString(), containsString("You have not saved any jobs."));
    }
}
