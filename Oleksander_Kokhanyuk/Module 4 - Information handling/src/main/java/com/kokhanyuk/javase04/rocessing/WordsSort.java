package com.kokhanyuk.javase04.rocessing;

import com.kokhanyuk.javase03.actions.FileIO;
import com.kokhanyuk.javase04.crazylogger.CrazyLogger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static java.io.File.separator;

/**
 * WordSort
 * In this class is done searching, sorting and processing of words and sentences
 * in the text according to the specified rules.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class WordsSort {
    public WordsSort() {
        fio = new FileIO();
        vovels = fio.readFile(fileVovels, "UTF-8").toCharArray();
        consonats = fio.readFile(fileConsonats, "UTF-8").toCharArray();
        log = new CrazyLogger("WordsSort");
    }

    String fileVovels = "Data" + separator + "vovelsRuChar.txt";
    String fileConsonats = "Data" + separator + "consonatsRuChar.txt";
    char[] vovels;
    char[] consonats;
    FileIO fio;
    CrazyLogger log;

    public StringBuilder sortVowels(String fileName) {
        //this method looks for words in the text, and sorts them according to the rules

        StringBuilder sb = new StringBuilder();
        String wordTmp;
        String text;
        double counterOne = 0;
        double counterTwo = 0;
        text = fio.readFile(fileName, "UTF-8");
        String[] textArr = text.split("\\s");
        textArr[0] = cleanWord(textArr[0]);
        for (int i = 0; i < textArr.length; i++) {
            for (int j = 0; j < textArr.length - 1 - i; j++) {
                textArr[j + 1] = cleanWord(textArr[j + 1]);
                if (textArr[j].length() > 1) {
                    counterOne = calculateVovel(textArr[j]) / textArr[j].length();
                }
                if (textArr[j + 1].length() > 1) {
                    counterTwo = calculateVovel(textArr[j + 1]) / textArr[j + 1].length();
                }
                if (counterOne < counterTwo) {
                    wordTmp = textArr[j];
                    textArr[j] = textArr[j + 1];
                    textArr[j + 1] = wordTmp;
                }
                counterOne = 0;
                counterTwo = 0;
            }
        }
        for (String s : textArr) {
            if (s.length() > 1 && (calculateVovel(s) != 0)) {
                sb.append(s + "\n");
            }
        }
        return sb;
    }

    private String cleanWord(String word) {
        //this method is to remove the excess marks on the edges of words

        String clean = word;
        String[] dell = new String[]{".", ",", ";", ":", "?", "!", "-", "\""};
        for (String d : dell) {
            if (clean.endsWith(d)) {
                clean = clean.substring(0, (clean.length() - 1));
            }
            if (clean.startsWith(d)) {
                clean = clean.substring(1, clean.length());
            }
        }
        clean = clean.trim();
        return clean;
    }

    public double calculateVovel(String word) {
        //This method considers the vowels and verifies that all of the characters word letters

        char[] wordCh = word.toCharArray();
        int calcVovel = 0;
        int calcConsonant = 0;
        for (char ch : wordCh) {
            for (char vo : this.vovels) {
                if (ch == vo) {
                    calcVovel++;
                }
            }
            for (char co : this.consonats) {
                if (ch == co) {
                    calcConsonant++;
                }
            }
        }
        if (word.length() != (calcVovel + calcConsonant)) {
            calcVovel = 0;
        }
        return calcVovel;
    }

    public StringBuilder changeWordPlases(String fileName) {
        //This method reverses the words in a sentence

        String text = fio.readFile(fileName, "UTF-8");
        StringBuilder sb = new StringBuilder();
        String[] textArr = text.split("\\r|\\n|\\.|;");
        String[] textArrOut = new String[textArr.length];
        int countOut = 0;
        for (int i = 0; i < textArr.length; i++) {
            textArr[i] = textArr[i].trim();
            int n = textArr[i].indexOf(" ");
            int m = textArr[i].lastIndexOf(" ");
            if (textArr[i].length() > 1 & n > 0 & m > 0) {
                textArrOut[countOut] = textArr[i].substring(m + 1, textArr[i].length()) + textArr[i].substring(n, m) +
                        " " + textArr[i].substring(0, n);
                countOut++;
            }
        }
        for (String s : textArrOut) {
            if (s != null) {
                s = s.replaceAll("\\t+| +", " ");
                sb.append(s + "\n");
            }
        }
        return sb;
    }

    public String dellWordLength(String fileName, int length) {
        //This method removes a specified length of words that begin with a vowel

        String text = fio.readFile(fileName, "UTF-8");
        CrazyLogger log = new CrazyLogger("dellWordLength");
        StringBuilder textEdited = new StringBuilder(text);
        FileIO fio = new FileIO();
        String fileConsonats = "Data" + separator + "consonatsRu.txt";
        String textOrigin = text;
        String textEdit = text;
        String[] consonats = fio.readFile(fileConsonats, "UTF-8").split(",");
        for (String con : consonats) {
            String pattern = "([\\s]" + "[" + con + "]" + "[\\W]{" + (length - 1) + "}[\\s])";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(textOrigin);
            try {
                while (m.find()) {
                    if (wordVerif(m.group(0).toString(), length)) {
                        textEdit = textEdit.replaceAll(m.group(0), " ");
                    }
                }
            } catch (PatternSyntaxException e) {
                log.err("Error in dellWordLength", e.toString());
            }
            m.reset();
        }
        return textEdit;
    }

    private boolean wordVerif(String word, int length) {
        //This method checks for the presence of the word inside the extraneous characters
        //I know it looks terrible, but I have to reach the goal

        String text = word.trim();
        String textTest = "";
        boolean ver = true;
        char[] check = text.toCharArray();
        for (char ch : check) {
            if (ch == ' ' | ch == '.' | ch == ',' | ch == '-' | ch == ':') {
                ver = false;
            } else {
                textTest += ch;
            }
        }
        if (textTest.length() != length) {
            ver = false;
        }
        return ver;
    }

    public String wordConverter(String fileName) {
        //This method removes from the word letter word that starts with

        String fileAlphabet = "Data" + separator + "alphabetRu.txt";
        String[] alphabetRu = fio.readFile(fileAlphabet, "UTF-8").split(",");
        String textEdited = fio.readFile(fileName, "UTF-8");
        String textOrigin = fio.readFile(fileName, "UTF-8");
        String textTmp1, textTmp2;
        for (String letter : alphabetRu) {
            for (int wordLength = 1; wordLength < 30; wordLength++) {
                String pattern = "([\\s]" + "[" + letter + "]" + "[\\W]{" + wordLength + "}[\\s])";
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(textOrigin);
                try {
                    while (m.find()) {
                        if (wordVerif(m.group(0).toString(), wordLength + 1)) {
                            textTmp1 = m.group().trim();
                            textTmp2 = m.group().trim();
                            char ch = textTmp1.charAt(0);
                            textTmp2 = textTmp2.replaceAll(Character.toString(ch), "");
                            textTmp2 = ch + textTmp2;
                            textEdited = textEdited.replaceAll(textTmp1, textTmp2);
                        }
                    }
                } catch (PatternSyntaxException e) {
                    log.err("Error in wordConverter", e.toString());
                }
                m.reset();
            }
        }
        return textEdited;
    }
}
