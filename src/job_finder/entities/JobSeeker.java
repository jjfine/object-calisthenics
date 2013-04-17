package job_finder.entities;

import job_finder.value_objects.Name;
import job_finder.value_objects.Resume;

import java.io.IOException;
import java.io.Writer;

public class JobSeeker {
    private Name name = new Name("");
    private Resume resume;

    public JobSeeker() {}

    public JobSeeker(Name name) {
        this.name = name;
    }

    public Boolean hasResume() {
        return resume != null;
    }

    public void appendNameToWriter(Writer writer) throws IOException {
        name.appendTo(writer);
    }
}