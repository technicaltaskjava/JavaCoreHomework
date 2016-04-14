package com.epam.first;

public class Speaker implements Comparable<Speaker> {
    private String name;
    private int numberOfSpeech;
    private float averageSpeechWords;

    public Speaker() {
        name = "";
        numberOfSpeech = 0;
        averageSpeechWords = 0.0f;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfSpeech() {
        return numberOfSpeech;
    }

    public void setNumberOfSpeech(int numberOfSpeech) {
        this.numberOfSpeech = numberOfSpeech;
    }

    public float getAverageSpeechWords() {
        return averageSpeechWords;
    }

    public void setAverageSpeechWords(float averageSpeechWords) {
        this.averageSpeechWords = averageSpeechWords;
    }

    public String outSpeakerData() {
        return String.format("%-40s%-5d%-10.4f%n", this.getName(),this.getNumberOfSpeech(),
                this.getAverageSpeechWords());
    }

    @Override
    public int compareTo(Speaker anotherSpeaker) {
        return this.getName().compareTo(anotherSpeaker.getName());
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
        if (this.getNumberOfSpeech() != speaker.getNumberOfSpeech()) {
            return false;
        }
        if (Float.floatToRawIntBits(this.getAverageSpeechWords()) ==
                Float.floatToRawIntBits(speaker.getAverageSpeechWords())) {
            return false;
        }
        return this.getName().equals(speaker.getName());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getNumberOfSpeech();
        result = 31 * result + (int) getAverageSpeechWords();
        return result;
    }
}
