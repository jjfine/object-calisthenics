package job_finder.entities;

import job_finder.value_objects.Name;

import java.io.IOException;
import java.io.Writer;

public class Recruiter {
    private Name name = new Name("No Name");

    public Recruiter() {  }

    public Recruiter(Name name) { this.name = name; }

    public void appendNameToWriter(Writer writer) throws IOException {
        name.appendTo(writer);
    }
}
