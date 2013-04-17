package job_finder.workflows;

import job_finder.entities.Recruiter;
import job_finder.entities.job.AtsJob;
import job_finder.services.repositories.JobRepository;
import job_finder.value_objects.JobTitle;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class RecruiterJobPostingWorkflowTest {
    RecruiterJobPostingWorkflow underTest;
    private JobRepository jobRepository;

    @Before
    public void setUp() {
        jobRepository = new JobRepository();
        underTest = new RecruiterJobPostingWorkflow(new Recruiter(), jobRepository);
    }

    @Test
    public void recruiterCanViewAListOfJobsTheyPosted() throws IOException {
        StringWriter writer = new StringWriter();
        underTest.postAtsJob(new JobTitle("Waiter"));
        underTest.showMyJobs(writer);
        assertThat(writer.toString(), containsString("Waiter"));
    }

    @Test
    public void recruiterCannotViewAListOfJobsOtherRecruitersHavePosted() throws IOException {
        StringWriter writer = new StringWriter();
        jobRepository.post(new AtsJob(new JobTitle("BAD"), new Recruiter()));
        underTest.showMyJobs(writer);
        assertThat(writer.toString(), not(containsString("BAD")));
    }
}
