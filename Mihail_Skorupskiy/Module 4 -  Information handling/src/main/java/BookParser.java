import misc.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookParser {
    private static StringBuilder source = new StringBuilder();
    private static StringBuilder result = new StringBuilder();
    private static Matcher word;
    private static String[] words;

    public static void fill(BufferedReader reader) throws IOException{
        try {
            reader.mark(8388608); // - 8 MB in bytes
            int lineCount = 0;
            while (reader.readLine() != null) {
                lineCount++;
            }
            reader.reset();
            for (int i = 0; i < lineCount; i++) {
                source.append(reader.readLine());
            }
            normalizeWhiteSpaces();
        } catch (IOException e){
            throw e;
        }
    }

    private static void normalizeWhiteSpaces(){
        Matcher book = Patterns.space.matcher(source);
        source.replace(0, source.capacity(), book.replaceAll(" "));
    }

    public static void swapFirstLastWords(){
        result.setLength(0);
        String[] sentences = Patterns.sentenceLimiter.split(source);
        for (int i = 0; i < sentences.length; i++){
            String[] currentSentence = Patterns.wordLimiter.split(sentences[i]);
            String temp = currentSentence[0];
            currentSentence[0] = currentSentence[currentSentence.length-1];
            currentSentence[currentSentence.length-1] = temp;
            for (int j = 0; j < currentSentence.length; j++){
                result.append(" ");
                result.append(currentSentence[j]);
            }
            result.append(".");
        }
    }

    public static void sortWordsBySyllables(){
        result.setLength(0);
        words = Patterns.wordLimiter.split(source);
        Words[] wordsRatios = new Words[words.length];
        for (int i = 0; i < words.length; i++){
            word = Patterns.syllable.matcher(words[i]);
            int quantity = 0;
            while (word.find()){
                quantity++;
            }
            if(quantity != 0){
                wordsRatios[i] = new Words(words[i], (double)quantity / words[i].length());
            } else {
                wordsRatios[i] = new Words(words[i], 0);
            }
        }
        Arrays.sort(wordsRatios);
        for (int i = 0; i < wordsRatios.length; i++){
            result.append(wordsRatios[i].getWord() + " ");
        }
    }

    public static void removeSpecifiedWords(int n){
        result.setLength(0);
        words = Patterns.wordLimiter.split(source);
        for (int i = 0; i < words.length; i++){
            word = Patterns.consonant.matcher(words[i]);
            if(word.lookingAt() && words[i].length() == n){
                words[i] = "";
            }
        }
        for (int i = 0; i < words.length; i++){
            result.append(words[i] + " ");
        }
    }

    public static void deleteFirstLetter(){
        result.setLength(0);
        words = Patterns.wordLimiter.split(source);
        for (int i = 0; i < words.length; i++) {
            word = Patterns.letter.matcher(words[i]);
            if (word.find()) {
                String firstLetter = word.group();
                words[i] = words[i].replaceAll(firstLetter, "");
            }
        }
        for (int i = 0; i < words.length; i++){
            result.append(words[i] + " ");
        }
    }


    public static void showSource(){
        System.out.println(source.toString());
    }
    public static void showResult(){
        System.out.println(result.toString());
    }
}
