package job_finder.entities.application;

import java.io.IOException;
import java.io.Writer;

public class JobApplicationsAppender {
    public void write(JobApplications jobApplications, Writer writer) throws IOException {
        if (jobApplications.isEmpty()) writer.write("No job applications here.\n");
        for (JobApplication application : jobApplications) appendJobSeekerNameList(writer, application);
    }

    private void appendJobSeekerNameList(Writer writer, JobApplication application) throws IOException {
        writer.write("- ");
        application.appendJobSeekerNameToWriter(writer);
        writer.append(" applied to ");
        application.appendJobTitleToWriter(writer);
        writer.append(" on ");
        application.appendApplicationDateToWriter(writer);
        writer.write("\n");
    }
}
