package ua.valentin.textBook;

import java.util.LinkedList;
import java.util.List;
/**
 *Word of sentence.
 * Created by valentin.yakimenko on 22.03.16.
 */
public class Word {
    private final static String WORDS_SEPARATOR = " ";
    private Symbol symbols;
    private List<Word> words;

    public Word(String word) {
        this.words = new LinkedList<>();
        this.symbols = new Symbol('S');
        symbols.addSymbols(getSymbolsFromWord(word));
    }

    private List<Symbol> getSymbolsFromWord(String word) {
        return Parser.getSymbolsFromWord(word);
    }

    public String getWords() {
        StringBuilder result = new StringBuilder();
        for (Word word : words) {
            result.append(word.symbols.getSymbols()).append(WORDS_SEPARATOR);
        }
        return result.toString();
    }

    public String getWordsAsString() {
        StringBuilder result = new StringBuilder();
        for (Word word : words) {
            result.append(word.symbols.getSymbolsAsString()).append(WORDS_SEPARATOR);
        }
        return result.toString();
    }

    public void addWords(List<Word> newWords) {
        words.addAll(newWords);
    }

    @Override
    public String toString() {
        return symbols.getSymbolsAsString();
    }
 }
