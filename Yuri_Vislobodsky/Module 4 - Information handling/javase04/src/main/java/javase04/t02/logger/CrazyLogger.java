package javase04.t02.logger;

import javase04.common.handler.FileHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CrazyLogger class
 * Created by Yury Vislobodsky on 18.03.2016.
 */
public class CrazyLogger extends FileHandler {
    private final static String recordPattern = "[\\d]{2}-[\\d]{2}-[\\d]{4} : [\\d]{2}-[\\d]{2} - [^;]+;";

    public void searchResultToStream(String regex, OutputStream out) throws IOException {
        Matcher matcher = Pattern.compile(recordPattern).matcher(content);
        while (matcher.find()) {
            Matcher matcherPhrase = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(matcher.group(0));
            while (matcherPhrase.find()) {
                String line = "Found \"" + matcherPhrase.group(0) + "\" at position " + matcherPhrase.start(0) +
                        " in record : " + matcher.group(0) + "\r\n";
                out.write(line.getBytes());
            }
        }
    }

    public void linesToStream(OutputStream out) throws IOException {
        Pattern pattern = Pattern.compile(recordPattern);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String line = matcher.group(0) + "\r\n";
            out.write(line.getBytes());
        }
    }

    public void addRecord(String record) {
        if (record != null) {
            content.append(String.format("%td-%<tm-%<tY : %<tH-%<tM - %s;", new Date(), record));
        }
    }
}
