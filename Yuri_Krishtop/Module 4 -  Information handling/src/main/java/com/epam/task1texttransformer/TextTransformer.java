package com.epam.task1texttransformer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yuriy Krishtop on 18.03.2016.
 */
public class TextTransformer {

    private String fileName;

    public TextTransformer(String fileName) {
        this.fileName = fileName;
    }

    public String getText() {
        String text = "";
        try (FileInputStream fileWords = new FileInputStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(fileWords, "utf8");) {
            BufferedReader inFile = new BufferedReader(inputStreamReader);
            String currentLine;
            while ((currentLine = inFile.readLine()) != null) {
                text += currentLine.replaceAll("[ ]+|[\t]+", " ") + "\n";
            }
            return text;
        } catch (IOException e) {
            System.out.println(e);
            return "File did not read";
        }

    }

    public String[] getSentences(String text) {
        String[] trimSentences = null;
        if ((text != null) && text != "") {
            Pattern pattern = Pattern.compile("\\.[\n]+|[\n]+|\\?[\n]+|(\\?)|(\\. )|(\\!)");
            String[] sentences = pattern.split(text);
            trimSentences = new String[sentences.length];
            for (int i = 0; i < sentences.length; i++) {
                trimSentences[i] = sentences[i].trim();
            }
            return trimSentences;
        } else {
            System.out.print("There are no text for sentences extracting");
            return trimSentences;
        }
    }

    public String replaceWordsInSentens(String[] sentences) {
        if (sentences != null) {
            String[] replacedSentences = new String[sentences.length];
            for (int i = 0; i < sentences.length; i++) {
                if (sentences[i].indexOf(' ') > 0) {
                    String firstWords = sentences[i].substring(0, sentences[i].indexOf(" "));
                    String lastWords = sentences[i].substring(sentences[i].lastIndexOf(" ") + 1, sentences[i].length());
                    String middlePart = sentences[i].substring(sentences[i].indexOf(" "), sentences[i].lastIndexOf(" ") + 1);
                    replacedSentences[i] = lastWords + middlePart + firstWords;
                } else {
                    replacedSentences[i] = sentences[i];
                }
            }
            String sents = "";
            for (String s : replacedSentences) {
                sents += s+"\n";
            }
            return sents.trim();
        } else {
            return "There are no sentences for replacing";
        }
    }

    public String[] getWords(String[] sentences) {
        String allWords = "";
        if (sentences != null) {
            for (String s : sentences) {
                allWords += s.replaceAll("[^a-zA-Zа-яА-Я]", " ") + " ";
            }
            String newAllWords = allWords.replaceAll("[ ]+", " ");
            String[] words = newAllWords.split(" ");
            return Arrays.copyOfRange(words, 1, words.length);
        } else {
            System.out.print("There are no sentences for getting words");
            return null;
        }
    }

    public String deleteWords(String text, int countLet) {
        if ((text != null) && text != "") {
            String trimText = text.replaceAll("[\\s]+", " ");
            String[] words = trimText.split(" ");
            String newStr = "";
            for (int i = 0; i < words.length; i++) {
                char firstLetter = words[i].charAt(0);
                if ((words[i].length() == countLet) && (TextTransformer.isConsonant(firstLetter)) && (!TextTransformer.isNonLet(""+words[i].charAt(words[i].length()-1)))) {
                    newStr += "";
                } else if ((words[i].length() == (countLet + 1)) && (TextTransformer.isNonLet("" + words[i].charAt(countLet)))) {
                    newStr += words[i].charAt(countLet) + " ";
                } else if ((words[i].length() == (countLet + 1)) && (TextTransformer.isNonLet(""+firstLetter))) {
                    newStr += firstLetter + " ";
                } else if ((words[i].length() == (countLet + 2)) && (TextTransformer.isNonLet("" + words[i].charAt(countLet + 1))) && (TextTransformer.isNonLet(""+firstLetter))) {
                    newStr += firstLetter + " " + words[i].charAt(countLet + 1);
                } else {
                    newStr += (words[i] + " ");
                }
            }
            String formatedStr = newStr.replaceAll("\\. ", "\\. \n");
            return formatedStr.trim();
        } else {
            return "There are no text for deleting words";
        }
    }

    public static String deleteLettersFromWord(String text) {
        if ((text != null) && text != "") {
            String trimText = text.replaceAll("[\\s]+", " ");
            String[] words = trimText.split(" ");
            String newStr = "";
            StringBuilder sb = null;
            for (int i = 0; i < words.length; i++) {
                sb = new StringBuilder();
                char firstLetter = words[i].charAt(0);
                sb.append(firstLetter);
                if (!isConsonant(firstLetter) && !isVowel(firstLetter) && (words[i].length() > 1) ) {
                    String newWords = words[i].substring(1);
                    words[i] = newWords;
                    firstLetter = words[i].charAt(0);
                    sb.append(firstLetter);
                }
                for (int j = 1; j < words[i].length(); j++) {
                    String currentCharacter = "" + words[i].charAt(j);
                    if (currentCharacter.equals(firstLetter) || currentCharacter.equals((""+firstLetter).toLowerCase())) {
                        sb.append("");
                    } else {
                        sb.append(currentCharacter);
                    }
                }
                newStr += sb + " ";

            }
            return newStr.trim();
        } else {
            return "There are no text for deleting letters in word";
        }
    }


    public static boolean isConsonant(char charact) {
        String character = ""+charact;
        Pattern p = Pattern.compile("[bcdfghjklmnpqrstvwxzбвгджзйклмнпрстфхцчшщБВГДЖЗЙКЛМНПРСТФХЦЧШЩ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(character);
        return matcher.matches();
    }

    public static boolean isVowel(char charact) {
        String character = ""+charact;
        Pattern p = Pattern.compile("[aeioyuауоыиэяюёеАУОЫИЭЯЮЁЕ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(character);
        return matcher.matches();
    }

    public static boolean isNonLet(String character) {
        Pattern p = Pattern.compile("[,|\\!|:|;|\\?|\\.|\\\"|)|(]");
        Matcher matcher = p.matcher(character);
        return matcher.matches();
    }

}
