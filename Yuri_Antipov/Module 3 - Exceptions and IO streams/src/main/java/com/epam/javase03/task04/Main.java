package com.epam.javase03.task04;

import com.epam.javase03.task04.readers.CodeWordsTextReader;
import com.epam.javase03.task04.readers.KeyWordsTextReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        KeyWordsTextReader keyWordsList = new KeyWordsTextReader();
        String[] listOfKeywords = keyWordsList.createKeyWordList();
        int count = 1;
        System.out.println("List of keywords:");
        for (String word : listOfKeywords) {
            System.out.println(count + ") " + word);
            count++;
        }
        CodeWordsTextReader codeWordsList = new CodeWordsTextReader();
        String[] listOfCodeWords = codeWordsList.createCodeWordsList();
        System.out.println("Words list from code file:");
        for (String word : listOfCodeWords) {
            System.out.println(word);
        }

        File foundKeyWordsFile = new File("FoundKeyWords.txt");
        int counter = 0;
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(foundKeyWordsFile));
            for (String keyWord : listOfKeywords) {
                for (String codeWord : listOfCodeWords) {
                    if (keyWord.equals(codeWord)) {
                        counter++;
                        writer.println(counter + ") " + keyWord);
                        System.out.println(counter + ") " + keyWord);
                    }
                }
            }
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

