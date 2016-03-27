package com.epam.t01;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TxtHandle {
    private static final String SPACE_TRIMMER_REGEX = "[\\s]+";
    private static final String BOM_TRIMMER_REGEX = "\uFEFF";
    private static final String SENTENCE_SPLIT_REGEX = "[А-Я]{1}[^!?\\.]*[!?\\.]+";
    private static final String REPLACE_FIRST_LAST_REGEX = "([^\\s]+)([\\s].*\\s)([^\\s!?\\.]+)([!?\\.])";
    private static final String REPLACE_FIRST_LAST_CONDITION = "$3$2$1$4";
    private static final String WORD_SPLIT_REGEX = "[^!?\\.\\-,:;\")(\\s]+";
    private static final String VOWELS_ARRAY = "аеиоуыэюяАЕИОУЭЮЯ";

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

        newData = newData.replaceAll(SPACE_TRIMMER_REGEX," ");
        newData = newData.replaceAll(BOM_TRIMMER_REGEX,"");
        newData = newData.trim();
        return newData;
    }

    public static String replaceFirstAndLastWord(String fileName) throws IOException {
        String newData ="";
        String currentText = readTextFromFile(fileName);

        Pattern sentencePattern = Pattern.compile(SENTENCE_SPLIT_REGEX);
        Matcher sentenceMatcher = sentencePattern.matcher(currentText);
        while (sentenceMatcher.find()) {
            String matchesData = sentenceMatcher.group(0);
            String newMatchesData = matchesData.replaceFirst(REPLACE_FIRST_LAST_REGEX, REPLACE_FIRST_LAST_CONDITION);
            newData = newData + newMatchesData + System.lineSeparator();
        }
        return newData;
    }

    public static void outResultData(String resultData, String outputFile) throws IOException {
        if (WorkWithFile.currentOutputToFile) {
            File currentFile = new File(outputFile);
            if (!currentFile.exists()) {
                currentFile.createNewFile();
            }

            BufferedWriter currentWritter = new BufferedWriter(new FileWriter(currentFile));
            currentWritter.write(resultData);
            currentWritter.close();
        }
        else {
            System.out.println(resultData);
        }
    }

    public static boolean checkNewWord(String matchedWord, VowelProportionSet proportionItems) {
        VowelProportion[] checkArray = proportionItems.getProportionSet();
        for (VowelProportion currentWord : checkArray) {
            if (matchedWord.equals(currentWord.getWord())) {
                return false;
            }
        }
        return true;
    }

    public static VowelProportionSet sortWordsByProportion(String fileName) throws IOException {
        VowelProportionSet proportionItems = new VowelProportionSet();
        String currentText = readTextFromFile(fileName);

        Pattern wordPattern = Pattern.compile(WORD_SPLIT_REGEX);
        Matcher wordMatcher = wordPattern.matcher(currentText);
        while (wordMatcher.find()) {
            if (checkNewWord(wordMatcher.group(0), proportionItems)) {
                char[] matchesData = wordMatcher.group(0).toCharArray();
                int vowelCounter = 0;
                for (char currentChar : matchesData) {
                    if (VOWELS_ARRAY.indexOf(currentChar) != -1) {
                        vowelCounter++;
                    }
                }
                float addProportion = ((float) vowelCounter / (float) matchesData.length);
                proportionItems.addProportionItem(wordMatcher.group(0), addProportion);
            }
        }
        Arrays.sort(proportionItems.getProportionSet());
        return proportionItems;
    }

    public static void outArrayResultData(VowelProportionSet proportionItems, String outputFile) throws IOException {
        if (WorkWithFile.currentOutputToFile) {
            File currentFile = new File(outputFile);
            if (!currentFile.exists()) {
                currentFile.createNewFile();
            }

            BufferedWriter currentWritter = new BufferedWriter(new FileWriter(currentFile));
            for (VowelProportion currentRecord : proportionItems.getProportionSet()) {
                String outString = String.format("%-20s%6.5f", currentRecord.getWord(), currentRecord.getProportion());
                currentWritter.write(outString + System.lineSeparator());
            }
            currentWritter.close();
        }
        else {
            for (VowelProportion currentRecord : proportionItems.getProportionSet()) {
                System.out.printf("%-20s", currentRecord.getWord());
                System.out.printf("%6.5f\n", currentRecord.getProportion());
            }
        }
    }

    public static String deleteFirstConsonantWords(String fileName, Integer wordLength) throws IOException {
        String currentText = readTextFromFile(fileName);
        String newData = currentText;

        Pattern wordPattern = Pattern.compile(WORD_SPLIT_REGEX);
        Matcher wordMatcher = wordPattern.matcher(currentText);
        while (wordMatcher.find()) {
                char[] matchesData = wordMatcher.group(0).toCharArray();
                if ((VOWELS_ARRAY.indexOf(matchesData[0]) == -1) && (matchesData.length == wordLength)) {
                    newData = newData.replaceAll(wordMatcher.group(0),"");
                }
        }
        newData = newData.replaceAll(SPACE_TRIMMER_REGEX," ");
        return newData;
    }

    public static String deleteFirstRepeatedLetters(String fileName) throws IOException {
        String currentText = readTextFromFile(fileName);
        String newData = "";

        Pattern wordPattern = Pattern.compile(WORD_SPLIT_REGEX);
        Matcher wordMatcher = wordPattern.matcher(currentText);
        while (wordMatcher.find()) {
            char[] matchesData = wordMatcher.group(0).toLowerCase().toCharArray();
            String currentWord = wordMatcher.group(0).toLowerCase();
            newData = newData + String.valueOf(matchesData[0]) +
                    currentWord.replaceAll(String.valueOf(matchesData[0]),"") + System.lineSeparator();
        }
        return newData;
    }
}
