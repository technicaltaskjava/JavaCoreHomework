package com.epam.javase04.task01_bookHandler.writer;

import com.epam.javase04.task01_bookHandler.wordsHandler.Word;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class dataWriter {
    PrintWriter printWriter = null;

    public void writeToFile(StringBuilder text, String outputFile) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(outputFile));
            printWriter.println(text);
            System.out.println("All data has written into the file \"" + outputFile + "\".\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (printWriter != null){
                    printWriter.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void writeToFile(String[] string, String outputFile) {
        try {
            printWriter = new PrintWriter(new FileWriter(outputFile));
            for(String s : string) {
                printWriter.println(s);
            }
            System.out.print("All data has written into the file \"" + outputFile + "\". ");
            System.out.println("Please, check this file.\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (printWriter != null){
                    printWriter.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public void writeToFile(Word[] word, String outputFile) {
        try {
            printWriter = new PrintWriter(new FileWriter(outputFile));
            for(Word s : word) {
                printWriter.println(s.getWord() + " " + s.getVowelsPercentage());
            }
            System.out.print("All data has written into the file \"" + outputFile + "\". ");
            System.out.println("Please, check this file.\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (printWriter != null){
                    printWriter.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
