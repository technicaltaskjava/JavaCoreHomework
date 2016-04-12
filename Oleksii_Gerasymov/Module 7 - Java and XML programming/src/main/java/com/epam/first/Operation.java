package com.epam.first;

public class Operation {

    private Operation() {
    }

    public static int countSpeechWords(String speech) {
        String[] words = speech.split(" ");
        return words.length;
    }
}
