package job_finder.entities.application.criteria;

import job_finder.criterion.Criterion;
import job_finder.entities.application.JobApplication;
import org.joda.time.LocalDate;

public class ApplicationDateCriterion implements Criterion<JobApplication> {
    private LocalDate date;

    public ApplicationDateCriterion(LocalDate date) {
        this.date = date;
    }

    @Override
    public Boolean isSatisfiedBy(JobApplication application) {
        return application.appliedOn(date);
    }
}
