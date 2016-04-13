package com.kokhanyuk.xml.parsers;

/**
 * Speaker
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class Speaker {
    private int counterReplica;
    private int counterWord;
    private int averageWords;

    public int getAverageWords() {
        if(counterReplica!=0){
            averageWords=counterWord/counterReplica;
        }
        return averageWords;
    }

    public int getCounterReplica() {
        return counterReplica;
    }

    public void setCounterReplica() {
        this.counterReplica++;
    }

    public void setCounterWord(int counterWord) {
        this.counterWord += counterWord;
    }

    @Override
    public String toString() {
        return getCounterReplica() + " " + getAverageWords();
    }
}
