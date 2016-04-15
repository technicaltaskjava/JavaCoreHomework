package com.epam.task1.model;

/**
 * Created by Olga Kramska on 09-Apr-16.
 */
public class Speaker {
    private String speakerName;
    private int cueCount;
    private int wordsCount;

    public Speaker(String speakerName, int cueCount, int wordsCount) {
        this.speakerName = speakerName;
        this.cueCount = cueCount;
        this.wordsCount = wordsCount;
    }

    public String getSpeakerName() {
        return speakerName;
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

    public int getAverageCueLength() {
        return wordsCount / cueCount;
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

        return cueCount == speaker.getCueCount() &&
                wordsCount == speaker.getWordsCount() &&
                speakerName.equals(speaker.getSpeakerName());
    }

    @Override
    public int hashCode() {
        int result = speakerName.hashCode();
        result = 31 * result + cueCount;
        result = 31 * result + wordsCount;
        return result;
    }

    @Override
    public String toString() {
        return "Speaker{" + speakerName + ", cue count" + cueCount +
                ", average cue length" + getAverageCueLength() + '}';
    }
}
