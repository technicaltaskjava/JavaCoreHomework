package com.epam.javase04.task01_bookHandler.wordsHandler;

public class Word {
    private String word;
    private char[] vowels = "АаЕеЄєИиІіЇїОоУуЮюЯяЁёЫыЭэAaEeIiOoUu".toCharArray();
    private double vowelsPercentage;

    public Word() {}
    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
    public char[] getVowels() {
        return vowels;
    }
    public double countVowelsPercentage() {
        char[] suspect = word.toCharArray();
        double vowelsNumber = 0;
        for (int i = 0; i < suspect.length; i++) {
            for (int j = 0; j < vowels.length; j++) {
                if(suspect[i] == vowels[j]) {
                    vowelsNumber++;
                    //System.out.println(suspect[i] + " " + vowels[j]);
                }
            }
        }
        vowelsPercentage = vowelsNumber / word.length();
        return vowelsPercentage;
    }
    public double getVowelsPercentage() {
        return vowelsPercentage;
    }
}
