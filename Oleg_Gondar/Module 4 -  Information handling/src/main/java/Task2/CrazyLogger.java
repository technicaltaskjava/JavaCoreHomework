package Task2;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by O.Gondar on 22.03.2016.
 */
public class CrazyLogger {

    StringBuilder logJournal;

    public CrazyLogger() {
        this.logJournal = new StringBuilder();
    }


    public void addLog(String message) {

        logJournal.append(LogsDateFormat.dateForLog() + " - " + message + "\n");

    }

    public String getLog(String text) {

        String result = "";

        String[] logMessages = logJournal.toString().split("\n");
        for (String s :
                logMessages) {
            if (s.matches(text)) {
                result += s + "\n";
            }
        }
        return result;

    }

    public void logToSTDOut() {

        System.out.println(logJournal);
    }

    public void logToFileWriter(String path) {

        try {

            FileWriter outputStream = new FileWriter(path);
            outputStream.write(logJournal.toString());
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
