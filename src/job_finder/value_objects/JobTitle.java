package job_finder.value_objects;

import java.io.IOException;
import java.io.Writer;

public class JobTitle {
    private String jobTitle;

    public JobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void appendTo(Writer writer) throws IOException {
        writer.write(jobTitle);
    }
}
