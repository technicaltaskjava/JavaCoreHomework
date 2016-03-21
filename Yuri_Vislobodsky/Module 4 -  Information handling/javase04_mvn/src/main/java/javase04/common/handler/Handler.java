package javase04.common.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Base parent class to handle with StringBuilder stream input and output
 * Created by Yury Vislobodsky on 18.03.2016.
 */
public class Handler {
    protected StringBuilder content;

    public Handler() {
        content = new StringBuilder();
    }

    public void clear() {
        content.delete(0, content.length());
    }

    public void outputToStream(OutputStream out) throws IOException {
        out.write(content.toString().getBytes());
    }

    public void inputFromStream(InputStream in) throws IOException {
        int bytesAvailable = in.available();
        byte[] bytes = new byte[bytesAvailable];
        if (in.read(bytes, 0, bytesAvailable) != -1) {
            content.append((new String(bytes)));
        }
    }
}
