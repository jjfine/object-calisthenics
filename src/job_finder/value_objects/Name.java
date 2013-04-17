package job_finder.value_objects;

import java.io.IOException;
import java.io.Writer;

public class Name {
    private String name;

    public Name(String name) {
        this.name = name;
    }

    public void appendTo(Writer writer) throws IOException {
        writer.write(name);
    }
}
