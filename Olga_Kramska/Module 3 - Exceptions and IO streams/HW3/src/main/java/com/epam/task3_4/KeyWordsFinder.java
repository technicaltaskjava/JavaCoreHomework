package com.epam.task3_4;

/**
 * Created by Olga Kramska on 12-Mar-16.
 */
public class KeyWordsFinder {

    private KeyWordsFinder() {
    }

    public static Result[] find(String source, String[] keyWords) {
        int size = 0;
        int[] occurrence = new int[keyWords.length];
        for (int i = 0; i < keyWords.length; i++) {
            int index = source.indexOf(keyWords[i]);
            int occur = 0;
            while (index >= 0) {
                occur++;
                index = source.indexOf(keyWords[i], index + 1);
            }
            if (occur > 0) {
                size++;
            }
            occurrence[i] = occur;
        }
        int j = 0;
        Result[] results = new Result[size];
        for (int i = 0; i < occurrence.length; i++) {
            if (occurrence[i] > 0) {
                results[j] = new Result(keyWords[i], occurrence[i]);
                j++;
            }
        }
        return results;
    }
}
