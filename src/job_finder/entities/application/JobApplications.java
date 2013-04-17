package job_finder.entities.application;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JobApplications implements Iterable<JobApplication> {
    List<JobApplication> applications = new ArrayList<JobApplication>();

    public void add(JobApplication jobApplication) {
        applications.add(jobApplication);
    }

    @Override
    public Iterator<JobApplication> iterator() {
        return applications.iterator();
    }

    public Integer size() {
        return applications.size();
    }

    public void appendWriterWithListOfJobTitles(Writer writer) throws IOException {
        if (applications.isEmpty()) writer.write("No job applications here.\n");
        for (JobApplication application : applications) appendJobTitleListItem(writer, application);
    }

    private void appendJobTitleListItem(Writer writer, JobApplication application) throws IOException {
        writer.write("- ");
        application.appendJobTitleToWriter(writer);
        writer.write("\n");
    }

    public Boolean isEmpty() {
        return applications.isEmpty();
    }
}
