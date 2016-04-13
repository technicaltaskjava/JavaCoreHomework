package javase.t01.play;

/**
 * Totals class for Play
 * Created by Yury Vislobodsky on 06.04.2016.
 */
public class Totals {
    private int speechCount;
    private int wordsCount;

    public Totals() {
        speechCount = 0;
        wordsCount = 0;
    }

    public int getSpeechCount() {
        return speechCount;
    }

    public void setSpeechCount(int speechCount) {
        this.speechCount = speechCount;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount = wordsCount;
    }

    public int getAverageWordsCountPerSpeech() {
        return speechCount == 0 ? 0 : wordsCount / speechCount;
    }
}
