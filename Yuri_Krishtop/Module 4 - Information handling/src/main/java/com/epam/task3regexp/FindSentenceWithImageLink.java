package com.epam.task3regexp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yuriy Krishtop on 20.03.2016.
 */
public class FindSentenceWithImageLink{

    private String fileName;

    public FindSentenceWithImageLink(String fileName) {
        this.fileName = fileName;
    }

    public String getText() {
        String text = "";
        try (FileInputStream fileWords = new FileInputStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(fileWords, "cp1251");) {
            BufferedReader inFile = new BufferedReader(inputStreamReader);
            String currentLine;
            while ((currentLine = inFile.readLine()) != null) {
                text += currentLine;
            }
            String newText = text.replaceFirst("^<.+both;\">","").replaceAll("<.{1,6}>|&nbsp;|<img.{10,200}>", " ");
            String repFig = newText.replaceAll("[Рр]ис\\. ", "figure");
            return repFig;
        } catch (IOException e) {
            System.out.println(e);
            return "File"+ fileName +"did not read";
        }
    }

    public String[] getSentences(String text) {
        String[] trimSentences = null;
        if ((text != null) && text != "") {
            Pattern pattern = Pattern.compile("\\. |\\? |\\! ");
            String[] sentences = pattern.split(text);
            trimSentences = new String[sentences.length];
            for (int i = 0; i < sentences.length; i++) {
                trimSentences[i] = sentences[i].trim().replaceAll("figure", "Рис. ");
            }
            return trimSentences;
        } else {
            System.out.print("There are no text for sentences extracting");
            return trimSentences;
        }
    }

    public void getSentencesWithImageLink(String[] sentences) {
        String[] sentencesWithLink = null;
        if (sentences != null) {
            Pattern pattern = Pattern.compile(".{0,}[Рр]ис[\\.у][ н][\\dк].+");
            for (String s : sentences) {
                Matcher matcher = pattern.matcher(s);
                if (matcher.matches()) {
                    System.out.println(s);
                }
            }
        } else {
            System.out.print("There are no text for sentences extracting");
        }
    }

}
