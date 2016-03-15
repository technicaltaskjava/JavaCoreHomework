package intagretion;

import java.io.IOException;
import java.io.OutputStream;

public class UserOutputStream extends OutputStream {
    private String line = "";

    @Override
    public void write(int b) throws IOException {
        byte[] bytes = new byte[] { (byte)(b & 0xFF00 >> 8)};
        line += new String(bytes,"utf-8");
    }

    public String getLine() {
        return line;
    }
}
