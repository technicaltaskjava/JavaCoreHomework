package javase04.t03.parser;

import javase04.common.handler.FileHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HTML Parser class
 * Created by Yury Vislobodsky on 18.03.2016.
 */
public class HtmlParser extends FileHandler {
    private final static String sentencePattern =
            "[А-Я][^\\.!\\?]+(([Рр]ис.[ ]?[\\d])|(рисунк))[^\\.!\\?]+([\\.!\\?]|</div>|</p>)";

    public void resultToStream(OutputStream out) throws IOException {
        Pattern pattern = Pattern.compile(sentencePattern);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String line = matcher.group(0) + "\r\n";
            out.write(line.getBytes());
        }
    }
}
