package secondTask;


import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CrazyLogger {


    private StringBuilder logs = new StringBuilder();
    private OutputStream out = System.out;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
    private Calendar calendar = Calendar.getInstance();


    public String message(String message) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dateFormat.format(calendar.getTime())).append(" - ").append(message);
        return stringBuilder.toString();
    }


    public void log(String message) {
        LogRecord loger = new LogRecord(Level.FINE, message);
        logs.append(loger);
        try {
            out.write(loger.toString().getBytes());
        } catch (IOException e) {
            System.out.println("Cant write into file");
        }

//        try {
//            FileHandler fh = new FileHandler("log_tmp.txt");
//            loger.addHandler(fh);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public String search(String text) {
        StringBuilder searchResult = new StringBuilder();
        Pattern pattern = Pattern.compile("[0-9]{2}-[0-9]{2}-[0-9]{4} : [0-9]{2}-[0-9]{2} — .*" + text + ".*\\n");
        Matcher matcher = pattern.matcher(logs);
        int match = 0;
        while (matcher.find()) {
            searchResult.append(matcher.group());
            match++;
        }
        if (!searchResult.toString().isEmpty()) {
            System.out.println(match + " result(s) for \"" + text + "\" was found in the Log:\n" + searchResult);
        } else {
            System.out.println("No results for \"" + text + "\" was found in the Log");
        }
        return searchResult.toString();
    }
}