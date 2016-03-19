package com.epam.task3_4;

import com.epam.task3_4.utils.IOUtil;

import java.io.IOException;

/**
 * Created by Olga Kramska on 12-Mar-16.
 */
public class MainTask3_4 {
    public static void main(String[] args) throws IOException {
        String fileToCheck = IOUtil.readFromFile("Factorial.java");
        String[] keyWords = IOUtil.readFromFile("KeyWords.txt").split(";");

        Result[] results = KeyWordsFinder.find(fileToCheck, keyWords);
        IOUtil.writeToFile("D:/out.txt", results);

        String fileToCheck1 = IOUtil.readFromFileByByte("Salad.java");
        String[] keyWords1 = IOUtil.readFromFileByByte("KeyWords.txt").split(";");

        Result[] results1 = KeyWordsFinder.find(fileToCheck1, keyWords1);
        IOUtil.writeToFileByByte("D:/out1.txt", results1);
    }
}

