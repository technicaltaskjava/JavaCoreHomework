package com.epam.javase04.task03_htmlParser.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HtmlWriter {
    public void writeToFile(StringBuilder text, String outputFile) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(outputFile));
            writer.print(text);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
