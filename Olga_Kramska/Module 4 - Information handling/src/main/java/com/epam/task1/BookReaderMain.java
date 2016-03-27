package com.epam.task1;

import com.epam.task1.util.BookUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Olga Kramska on 18-Mar-16.
 */
public class BookReaderMain {
    public static void main(String[] args) {
        String book = BookReader.readBook("book.txt");

        String[] sentences = BookReader.getSentencesFromText(book);
        for (String sentence : sentences) {
            Sentence wordsInSentence = new Sentence(sentence);
            if (wordsInSentence.getWords().length > 1) {
                System.out.println(wordsInSentence.toString());
                wordsInSentence.replaceFirstWord2Last();
                System.out.println(wordsInSentence.toString());
            }
        }

        System.out.println(Arrays.toString(BookUtils.sortWords(book)));

        System.out.println(BookUtils.delWordsFromText(book, 5));


        Word[] words = BookUtils.getWordsArray(book);
        for (Word word : words) {
            System.out.print(word.changeWord() + " ");
        }
    }
}
