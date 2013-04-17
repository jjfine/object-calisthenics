package job_finder.workflows;

import job_finder.entities.JobSeeker;
import job_finder.entities.job.ats.AtsJob;
import job_finder.services.repositories.JobApplicationRepository;
import job_finder.value_objects.JobTitle;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class JobSeekerApplicationWorkflowTest {

    @Test
    public void jobSeekerCanSeeAListOfJobsAppliedTo() throws IOException {
        JobSeekerApplicationWorkflow underTest = new JobSeekerApplicationWorkflow(new JobSeeker(),new JobApplicationRepository());
        underTest.applyTo(new AtsJob(new JobTitle("Programmer")));
        underTest.applyTo(new AtsJob(new JobTitle("Tester")));
        StringWriter writer = new StringWriter();
        underTest.showJobsAppliedTo(writer);

        assertThat(writer.toString(), containsString("Programmer"));
        assertThat(writer.toString(), containsString("Tester"));
    }

    @Test
    public void jobSeekerSeesErrorMessageWhenListOfAppliedToJobsIsEmpty() throws IOException {
        JobSeekerApplicationWorkflow underTest = new JobSeekerApplicationWorkflow(new JobSeeker(),new JobApplicationRepository());
        StringWriter writer = new StringWriter();
        underTest.showJobsAppliedTo(writer);

        assertThat(writer.toString(), containsString("No job applications here."));
    }
}