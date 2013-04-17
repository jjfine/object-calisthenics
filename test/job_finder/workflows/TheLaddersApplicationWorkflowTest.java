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
    private Recruiter recruiterWithThreeApplications = new Recruiter(new Name("Three"));
    private JobApplicationRepository jobApplicationRepository;
    private Job jobWithOneApplicant;
    private Recruiter recruiterWithOneApplication;

    @Before
    public void setup() {
        jobApplicationRepository = new JobApplicationRepository();
        underTest = new TheLaddersApplicationWorkflow(jobApplicationRepository);

        jobWithTwoApplicants = new AtsJob(new JobTitle("Saucier"), recruiterWithThreeApplications);
        jobSeekerWithTwoApplications = new JobSeeker(new Name("Bobby"));
        jobApplicationBuilder = new JobApplicationBuilder();

        jobApplicationRepository.saveNewApplicationFor(jobApplicationBuilder.buildJobApplication(jobWithTwoApplicants, jobSeekerWithTwoApplications));
        jobApplicationRepository.saveNewApplicationFor(jobApplicationBuilder.buildJobApplication(new AtsJob(new JobTitle("Chef Garde Manager"), recruiterWithThreeApplications), jobSeekerWithTwoApplications));
        jobApplicationRepository.saveNewApplicationFor(jobApplicationBuilder.buildJobApplication(jobWithTwoApplicants, new JobSeeker(new Name("Ina"))));

        recruiterWithOneApplication = new Recruiter(new Name("One"));
        jobWithOneApplicant = new AtsJob(new JobTitle("Dishwasher"), recruiterWithOneApplication);
        jobApplicationRepository.saveNewApplicationFor(jobApplicationBuilder.buildJobApplication(jobWithOneApplicant, new JobSeeker(new Name("Mario"))));

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


    @Test
    public void theLaddersCanViewAggregateJobNumbersByJobAppliedToBy1Person() throws IOException {
        StringWriter writer = new StringWriter();
        underTest.showNumberOfApplicationsPostedFor(jobWithOneApplicant, writer);

        assertThat(writer.toString(), containsString("The job titled 'Dishwasher' has been applied to by 1 person."));
    }

    @Test
    public void theLaddersCanViewAggregateJobNumbersByJobAppliedToBy2People() throws IOException {
        StringWriter writer = new StringWriter();
        underTest.showNumberOfApplicationsPostedFor(jobWithTwoApplicants, writer);

        assertThat(writer.toString(), containsString("The job titled 'Saucier' has been applied to by 2 people."));
    }


    @Test
    public void theLaddersCanViewAggregateJobNumbersByJobAppliedToByNobody() throws IOException {
        StringWriter writer = new StringWriter();
        underTest.showNumberOfApplicationsPostedFor(new AtsJob(new JobTitle("Food Taster")), writer);

        assertThat(writer.toString(), containsString("The job titled 'Food Taster' has not been applied to by anyone."));
    }

    @Test
    public void theLaddersCanViewAggregateJobNumbersByRecruiterAppliedToBy1Person() throws IOException {
        StringWriter writer = new StringWriter();
        underTest.showNumberOfApplicationsByRecruiter(recruiterWithOneApplication, writer);

        assertThat(writer.toString(), containsString("The recruiter named 'One' has 1 application."));
    }

    @Test
    public void theLaddersCanViewAggregateJobNumbersByRecruiterAppliedToBy3People() throws IOException {
        StringWriter writer = new StringWriter();
        underTest.showNumberOfApplicationsByRecruiter(recruiterWithThreeApplications, writer);

        assertThat(writer.toString(), containsString("The recruiter named 'Three' has 3 applications."));
    }


    @Test
    public void theLaddersCanViewAggregateJobNumbersByRecruiterAppliedToByNobody() throws IOException {
        StringWriter writer = new StringWriter();
        underTest.showNumberOfApplicationsByRecruiter(new Recruiter(new Name("None")), writer);

        assertThat(writer.toString(), containsString("The recruiter named 'None' has no applications."));
    }
}
