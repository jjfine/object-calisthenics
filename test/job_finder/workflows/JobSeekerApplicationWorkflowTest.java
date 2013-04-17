package job_finder.workflows;

import job_finder.entities.JobSeeker;
import job_finder.entities.job.ats.AtsJob;
import job_finder.entities.job.jreq.JReqJob;
import job_finder.services.repositories.JobApplicationRepository;
import job_finder.value_objects.JobTitle;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JobSeekerApplicationWorkflowTest {
    StringWriter writer = new StringWriter();

    @Test
    public void jobSeekerCanSeeAListOfJobsAppliedTo() throws IOException {
        JobSeekerApplicationWorkflow underTest = new JobSeekerApplicationWorkflow(new JobSeeker(),new JobApplicationRepository());
        underTest.applyTo(new AtsJob(new JobTitle("Programmer")), writer);
        underTest.applyTo(new AtsJob(new JobTitle("Tester")), writer);
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

    @Test
    public void whenAJobSeekerAppliesToAJobTheyDoNotMeetTheRequirementsForTheySeeAnErrorMessage() throws IOException {
        JobSeekerApplicationWorkflow underTest = new JobSeekerApplicationWorkflow(new JobSeeker(),new JobApplicationRepository());

        underTest.applyTo(new JReqJob(new JobTitle("President")), writer);

        assertThat(writer.toString(), is("You cannot apply to be a President without a resume!"));
    }
}