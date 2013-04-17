package job_finder.services.repositories;

import job_finder.entities.JobSeeker;
import job_finder.entities.Recruiter;
import job_finder.entities.application.JobApplication;
import job_finder.entities.application.JobApplications;
import job_finder.entities.job.Job;
import job_finder.entities.job.ats.AtsJob;
import job_finder.entities.job.jreq.JReqJob;
import job_finder.services.JobApplicationBuilder;
import job_finder.value_objects.JobTitle;
import job_finder.value_objects.Name;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.IsCollectionContaining.hasItem;

public class JobApplicationRepositoryTest {

    private JobApplicationRepository underTest;
    private Job jobWithTwoApplicants;
    private JobSeeker jobSeekerWithTwoApplications;
    private JobApplicationBuilder jobApplicationBuilder;
    private Job jobPostedBySomeoneElse;
    private Recruiter recruiter;

    @Before
    public void setUp() {
        underTest = new JobApplicationRepository();
        recruiter = new Recruiter();
        jobWithTwoApplicants = new AtsJob(new JobTitle("Saucier"), recruiter);
        jobSeekerWithTwoApplications = new JobSeeker(new Name("Bobby"));
        jobApplicationBuilder = new JobApplicationBuilder();

        underTest.saveNewApplicationFor(jobApplicationBuilder.buildJobApplication(jobWithTwoApplicants, jobSeekerWithTwoApplications));
        underTest.saveNewApplicationFor(jobApplicationBuilder.buildJobApplication(new AtsJob(new JobTitle("Chef Garde Manager"), recruiter), jobSeekerWithTwoApplications));
        underTest.saveNewApplicationFor(jobApplicationBuilder.buildJobApplication(jobWithTwoApplicants, new JobSeeker(new Name("Ina"))));

        jobPostedBySomeoneElse = new AtsJob(new JobTitle("Dishwasher"), new Recruiter());
        underTest.saveNewApplicationFor(jobApplicationBuilder.buildJobApplication(jobPostedBySomeoneElse, new JobSeeker()));
    }

    @Test
    public void canFindJobsApplicationsGivenAJobSeeker() throws IOException {
        JobApplications actual = underTest.findByJobSeeker(jobSeekerWithTwoApplications);
        JobApplication expected = new JobApplication(jobWithTwoApplicants, jobSeekerWithTwoApplications);

        assertThat(actual.size(), is(2));
        assertThat(actual, hasItem(expected));
    }

    @Test
    public void canFindJobsApplicationsGivenAJob() {
        JobApplication expected = new JobApplication(jobWithTwoApplicants, jobSeekerWithTwoApplications);

        JobApplications actual = underTest.findByJob(jobWithTwoApplicants);

        assertThat(actual.size(), is(2));
        assertThat(actual, hasItem(expected));
    }

    @Test
    public void canFindJobsAppliedToOnACertainDatePostedByAGivenRecruiter() {
        LocalDate today = new LocalDate();
        JobApplications actual = underTest.findByDateAndRecruiter(today, recruiter);

        assertThat(actual.size(), is(3));
    }

    @Test
    public void jobSeekerCannotApplyToJReqJobWithoutAResume() {
        JobSeeker jobSeekerWithoutResume = new JobSeekerWithoutResume();
        JobApplication jobApplication = jobApplicationBuilder.buildJobApplication(new JReqJob(), jobSeekerWithoutResume);
        underTest.saveNewApplicationFor(jobApplication);

        JobApplications actual = underTest.findByJobSeeker(jobSeekerWithoutResume);

        assertThat(actual.size(), is(0));
    }

    private class JobSeekerWithoutResume extends JobSeeker {
        @Override
        public Boolean hasResume() {
            return false;
        }
    }
}
