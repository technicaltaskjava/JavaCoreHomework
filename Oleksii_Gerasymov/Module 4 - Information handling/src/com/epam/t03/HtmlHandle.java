package com.epam.t03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlHandle {
    private static final String BAD_HTML_TRIMMER_REGEX = ".+<body>";
    private static final String SPACE_TRIMMER_REGEX = "[\\s]+";
    private static final String BOM_TRIMMER_REGEX = "\uFEFF";
    private static final String IMAGE_SPLIT_REGEX =
     "[А-Я]{1}[^!?\\.]*(((\\(.ис\\.)|(рисун))[^!?\\.]*)+[^(\\(.ис\\.)].[!?\\.]+";

    private static String readTextFromFile(String newFile) throws IOException {
        File currentFile = new File(newFile);
        String newData="";
        if(!currentFile.exists()) {
            return newData;
        }

        BufferedReader currentFileReader = new BufferedReader(new FileReader(currentFile));
        String currentLine = currentFileReader.readLine();
        while (currentLine != null) {
            newData = newData + currentLine + " ";
            currentLine = currentFileReader.readLine();
        }
        currentFileReader.close();

        newData = newData.replaceAll(BAD_HTML_TRIMMER_REGEX," ");
        newData = newData.replaceAll(SPACE_TRIMMER_REGEX," ");
        newData = newData.replaceAll(BOM_TRIMMER_REGEX,"");
        newData = newData.trim();
        return newData;
    }

    public static String searchPictureLinks(String fileName) throws IOException {
        String newData ="";
        String currentText = readTextFromFile(fileName);

        Pattern sentencePattern = Pattern.compile(IMAGE_SPLIT_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher sentenceMatcher = sentencePattern.matcher(currentText);
        int countOfSentences = 0;
        while (sentenceMatcher.find()) {
            countOfSentences++;
            newData = newData + countOfSentences + ". " + sentenceMatcher.group(0) + System.lineSeparator();
        }
        return newData;
    }

    public static void outResultData(String resultData) throws IOException {
            System.out.println(resultData);
    }
}
