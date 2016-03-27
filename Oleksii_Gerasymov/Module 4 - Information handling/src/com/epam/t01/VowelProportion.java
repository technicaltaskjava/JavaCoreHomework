package com.epam.t01;

public class VowelProportion implements Comparable<VowelProportion> {
    private String word;
    private float proportion;

    public VowelProportion(String word, Float proportion) {
        this.word = word;
        this.proportion = proportion;
    }

    public String getWord() {
        return word;
    }

    public Float getProportion() {
        return proportion;
    }

    public int compareTo(VowelProportion newProportion) {
        float anotherProportion = newProportion.getProportion();
        if (this.getProportion() > anotherProportion) {
            return 1;
        }
        else if (this.getProportion() < anotherProportion) {
            return -1;
        }
        return 0;
    }
}
