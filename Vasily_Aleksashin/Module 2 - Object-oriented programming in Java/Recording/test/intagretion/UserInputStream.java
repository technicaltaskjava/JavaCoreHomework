package intagretion;

import java.io.IOException;
import java.io.InputStream;

public class UserInputStream extends InputStream {
    private String line;
    private boolean endLine;

    @Override
    public int read() throws IOException {
        if (line.length() == 0) {
            return -1;
        }
        if (endLine) {
            endLine = false;
            return -1;
        }

        char ch = line.charAt(0);
        line = line.substring(1);

        if (ch == '\n') {
            endLine = true;
        }
        return (int) ch;
    }

    public void add(final String line) {
        if (this.line == null) {
            this.line = line;
        } else {
            this.line += "\n" + line;
        }
    }

    @Override
    public synchronized void reset() throws IOException {
        line = null;
        endLine = false;
    }
}
