package com.kokhanyuk.javase04.rocessing;

import com.kokhanyuk.javase03.actions.FileIO;
import com.kokhanyuk.javase04.crazylogger.CrazyLogger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SentenceSearch
 *In this class, searches for the text containing proposals for a regular expression
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class SentenceSearch {
    public SentenceSearch(){
        log = new CrazyLogger("textSearcher");
        fio = new FileIO();
    }
    FileIO fio;
    CrazyLogger log;

    public void textSearcher(String fileName) {
        //This method looks in the text regular expressions, and highlights from
        // the text of the proposal which contain regular expressions

        String text = fio.readFile(fileName, "windows-1251");
        int[] indexInner = lineSelection(text, "\\(Рис. [\\d]{1,2}");
        int[] indexStartTeg = lineSelection(text, "<div");
        int[] indexEndTeg = lineSelection(text, "</div>");
        int start = 0;
        int startLast = 0;
        int end = 0;
        System.out.println("Найдены следующие предложения:\n");
        for (int inner : indexInner) {
            for (int i = 0; i < indexStartTeg.length; i++) {
                if (inner > indexStartTeg[i]) {
                    start = indexStartTeg[i];
                }
            }
            for (int j = indexEndTeg.length - 1; j > 0; j--) {
                if (inner < indexEndTeg[j]) {
                    end = indexEndTeg[j];
                }
            }
            if (start != startLast) {
                String s = text.substring(start + 1, end);
                s = s.replaceAll("\\(Рис\\.", "Pic");
                String[] sArr = s.split("\\.|\\?|\\n|;");
                for (int i = 0; i < sArr.length; i++) {
                    sArr[i] = sArr[i].replaceAll("Pic", "(Рис.");
                    if (sArr[i].contains("(Рис. ")) {
                        sArr[i] = sArr[i].replaceAll("\\t+| +|&nbsp|<p>|</p>|<div>|</div>|<sub>|</sub>", " ");
                        sArr[i] = sArr[i].trim();
                        System.out.println(sArr[i]);
                    }
                }
            }
            startLast = start;
        }
    }

    public int[] lineSelection(String text, String patern) {
        //This method looks for all regular expressions in the text

        String indexTeg = "";
        String regex3 = patern;
        Pattern p3 = Pattern.compile(regex3);
        Matcher m3 = p3.matcher(text);
        while (m3.find()) {
            indexTeg += m3.end() + ",";
        }
        String[] index = indexTeg.split(",");
        int[] indexTegArr = new int[index.length];
        try {
            for (int i = 0; i < indexTegArr.length; i++) {
                indexTegArr[i] = Integer.parseInt(index[i]);
            }
        } catch (NumberFormatException e) {
            log.err("Error in lineSelection ", e.toString());
        }
        m3.reset();
        return indexTegArr;
    }
}