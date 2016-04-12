package com.epam.task1.model;

/**
 * Created by Olga Kramska on 09-Apr-16.
 */
public class Speaker {
    private int cueCount;
    private int wordsCount;

    public Speaker(int cueCount, int wordsCount) {
        this.cueCount = cueCount;
        this.wordsCount = wordsCount;
    }

    public int getCueCount() {
        return cueCount;
    }

    public void setCueCount(int cueCount) {
        this.cueCount = cueCount;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount = wordsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Speaker)) {
            return false;
        }

        Speaker speaker = (Speaker) o;
        return getCueCount() == speaker.getCueCount() && getWordsCount() == speaker.getWordsCount();
    }

    @Override
    public int hashCode() {
        int result = getCueCount();
        result = 31 * result + getWordsCount();
        return result;
    }
}
