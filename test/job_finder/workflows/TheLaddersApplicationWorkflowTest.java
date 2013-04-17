package job_finder.workflows;

import job_finder.entities.JobSeeker;
import job_finder.entities.Recruiter;
import job_finder.entities.job.Job;
import job_finder.entities.job.ats.AtsJob;
import job_finder.services.JobApplicationBuilder;
import job_finder.services.repositories.JobApplicationRepository;
import job_finder.value_objects.JobTitle;
import job_finder.value_objects.Name;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class TheLaddersApplicationWorkflowTest {

    private TheLaddersApplicationWorkflow underTest;
    private Job jobWithTwoApplicants;
    private JobSeeker jobSeekerWithTwoApplications;
    private JobApplicationBuilder jobApplicationBuilder;
    private Recruiter recruiter = new Recruiter();
    private JobApplicationRepository jobApplicationRepository;
    private Job jobPostedBySomeoneElse;

    @Before
    public void setup() {
        jobApplicationRepository = new JobApplicationRepository();
        underTest = new TheLaddersApplicationWorkflow(jobApplicationRepository);

        jobWithTwoApplicants = new AtsJob(new JobTitle("Saucier"), recruiter);
        jobSeekerWithTwoApplications = new JobSeeker(new Name("Bobby"));
        jobApplicationBuilder = new JobApplicationBuilder();

        jobApplicationRepository.saveNewApplicationFor(jobApplicationBuilder.buildJobApplication(jobWithTwoApplicants, jobSeekerWithTwoApplications));
        jobApplicationRepository.saveNewApplicationFor(jobApplicationBuilder.buildJobApplication(new AtsJob(new JobTitle("Chef Garde Manager"), recruiter), jobSeekerWithTwoApplications));
        jobApplicationRepository.saveNewApplicationFor(jobApplicationBuilder.buildJobApplication(jobWithTwoApplicants, new JobSeeker(new Name("Ina"))));

        jobPostedBySomeoneElse = new AtsJob(new JobTitle("Dishwasher"), new Recruiter());
        jobApplicationRepository.saveNewApplicationFor(jobApplicationBuilder.buildJobApplication(jobPostedBySomeoneElse, new JobSeeker(new Name("Mario"))));

    }

    @Test
    public void theLaddersCanViewAllApplicationsOnASpecifiedDate() throws IOException {
        LocalDate today = new LocalDate();
        StringWriter writer = new StringWriter();
        underTest.showApplicationsPostedOn(today, writer);

        String dateString = today.toString();
        assertThat(writer.toString(), containsString("- Ina applied to Saucier on " + dateString));
        assertThat(writer.toString(), containsString("- Bobby applied to Saucier on " + dateString));
        assertThat(writer.toString(), containsString("- Mario applied to Dishwasher on " + dateString));
        assertThat(writer.toString(), containsString("- Bobby applied to Chef Garde Manager on " + dateString));
    }
}
