package logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Alexey Ushakov
 */
public class CrazyLogger {
    private StringBuilder logText = new StringBuilder(100);
    private String[] searchResult = new String[10];
    private int searchResultCount = 0;

    public void addToLog(String message) {
        StringBuilder logMessage = new StringBuilder(message);
        convertToLogMessage(logMessage);
        logText.append(logMessage);
    }

    private void resizeFindResult() {
        String[] newResult = new String[searchResult.length * 2];
        System.arraycopy(searchResult, 0, newResult, 0, searchResult.length);
        searchResult = newResult;
    }

    private void addToSearchResult(String message) {
        if (searchResult.length == searchResultCount) {
            resizeFindResult();
        }
        searchResult[searchResultCount] = message;
        searchResultCount++;
    }

    public String[] search(String message) {
        int startIndex = 0;
        searchResult = new String[10];
        searchResultCount = 0;

        while (startIndex < logText.length()) {
            int indexOfMessage = logText.indexOf(message, startIndex);

            if (indexOfMessage != -1) {

                int startIndexOfMessage = 0;
                int endIndexOfMessage = 0;

                for (int i = indexOfMessage; i >= 0; i--) {
                    if (logText.charAt(i) == '\n') {
                        startIndexOfMessage = i;
                        break;
                    }
                }

                for (int i = indexOfMessage; i < logText.length(); i++) {
                    if (logText.charAt(i) == '\n') {
                        endIndexOfMessage = i;
                        break;
                    }
                }

                if (startIndexOfMessage != 0) {
                    startIndexOfMessage++;
                }

                addToSearchResult(logText.substring(startIndexOfMessage, endIndexOfMessage));
                startIndex = ++endIndexOfMessage;
            } else {
                break;
            }
        }
        return Arrays.copyOfRange(searchResult, 0, searchResultCount);
    }

    public void search(String message, PrintStream output) {
        String[] results = search(message);
        for (String result : results) {
            output.println(result);
        }
    }

    public void printLog(PrintStream output) {
        output.print(logText);
    }

    private static void convertToLogMessage(StringBuilder message) {
        message.insert(0, String.format("%1$td-%1$tm-%1$tY : %1$tH-%1$tM - ", new Date()));
        message.append(";\n");
    }

    private static String getReportName() {
        return String.format("%1$tY:%1$tm:%1$td reportLog %1$tH:%1$tM:%1$tM.log", new Date());
    }

    public void saveToFile() {
        saveToFile(getReportName());
    }

    public void saveToFile(String reportName) {
        try (Writer stream = new FileWriter(reportName)) {
            stream.write(logText.toString());
        } catch (IOException e) {
            StringBuilder errorMessage = new StringBuilder("Can`t save log to file ");
            errorMessage.append(reportName);
            convertToLogMessage(errorMessage);
            addToLog(errorMessage.toString());
        }
    }

}
