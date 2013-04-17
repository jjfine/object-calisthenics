package job_finder.entities.job;

import java.io.IOException;
import java.io.Writer;

public class JobsAppender {
    public void appendJobListToWriter(Jobs jobs, Writer writer) throws IOException {
        if (jobs.isEmpty()) appendErrorTo(writer);
        for (Job job : jobs)  appendJobListItem(writer, job);
    }

    private void appendErrorTo(Writer writer) throws IOException {
        writer.write("You have not saved any jobs.");
    }

    private void appendJobListItem(Writer writer, Job job) throws IOException {
        writer.write("- ");
        job.appendTitleToWriter(writer);
        writer.write("\n");
    }
}
