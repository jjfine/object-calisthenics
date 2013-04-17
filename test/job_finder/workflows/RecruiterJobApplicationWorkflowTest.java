package job_finder.workflows;

import job_finder.entities.JobSeeker;
import job_finder.entities.Recruiter;
import job_finder.entities.job.AtsJob;
import job_finder.entities.job.Job;
import job_finder.services.JobApplicationBuilder;
import job_finder.services.repositories.JobApplicationRepository;
import job_finder.value_objects.JobTitle;
import job_finder.value_objects.Name;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class RecruiterJobApplicationWorkflowTest {
    private Job jobWithTwoApplicants;
    private JobSeeker jobSeekerWithTwoApplications;
    private JobApplicationBuilder jobApplicationBuilder;
    private JobApplicationRepository jobApplicationRepository;
    private RecruiterJobApplicationWorkflow underTest;
    private Recruiter recruiter;
    private Job jobPostedBySomeoneElse;

    @Before
    public void setUp() {
        jobApplicationRepository = new JobApplicationRepository();
        recruiter = new Recruiter();

        underTest = new RecruiterJobApplicationWorkflow(jobApplicationRepository, recruiter);
        jobWithTwoApplicants = new AtsJob(new JobTitle("Saucier"), recruiter);
        jobSeekerWithTwoApplications = new JobSeeker(new Name("Bobby"));
        jobApplicationBuilder = new JobApplicationBuilder();

        jobApplicationRepository.saveNewApplicationFor(jobApplicationBuilder.buildJobApplication(jobWithTwoApplicants, jobSeekerWithTwoApplications));
        jobApplicationRepository.saveNewApplicationFor(jobApplicationBuilder.buildJobApplication(new AtsJob(new JobTitle("Chef Garde Manager"), recruiter), jobSeekerWithTwoApplications));
        jobApplicationRepository.saveNewApplicationFor(jobApplicationBuilder.buildJobApplication(jobWithTwoApplicants, new JobSeeker(new Name("Ina"))));

        jobPostedBySomeoneElse = new AtsJob(new JobTitle("Dishwasher"), new Recruiter());
        jobApplicationRepository.saveNewApplicationFor(jobApplicationBuilder.buildJobApplication(jobPostedBySomeoneElse, new JobSeeker()));

    }

    @Test
    public void canViewAListOfApplicationsToAJobPostedByThisRecruiter() throws IOException {
        StringWriter writer = new StringWriter();
        underTest.showJobSeekersWhoAppliedTo(jobWithTwoApplicants, writer);
        String dateString = new LocalDate().toString();
        assertThat(writer.toString(), containsString("- Ina applied to Saucier on " + dateString));
        assertThat(writer.toString(), containsString("- Bobby applied to Saucier on " + dateString));
    }

    @Test
    public void cannotViewAListOfApplicationsToAJobPostedByAnotherRecruiter() throws IOException {
        StringWriter writer = new StringWriter();
        underTest.showJobSeekersWhoAppliedTo(jobPostedBySomeoneElse, writer);
        assertThat(writer.toString(), containsString("Sorry, you cannot view applications to jobs you didn't post."));
    }

    @Test
    public void canViewAListOfThisRecruitersJobsAppliedToOnACertainDate() throws IOException {
        LocalDate today = new LocalDate();
        StringWriter writer = new StringWriter();
        underTest.showApplicantsFromDate(today, writer);

        String dateString = new LocalDate().toString();
        assertThat(writer.toString(), containsString("- Ina applied to Saucier on " + dateString));
        assertThat(writer.toString(), containsString("- Bobby applied to Saucier on " + dateString));
        assertThat(writer.toString(), containsString("- Bobby applied to Chef Garde Manager on " + dateString));
        assertThat(writer.toString(), not(containsString("Dishwasher")));
    }
}
