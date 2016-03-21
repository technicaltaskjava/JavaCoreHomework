package textformatter.textformatterutils;

import java.util.regex.Pattern;

/**
 * @author Alexey Ushakov
 */
public class FrequencyWord implements Comparable<FrequencyWord>, TextPatterns {
    private String word;
    private double frequency;
    private static final int FREQUENCY_SCALE = 1000;
    private static final Pattern vowelsPattern = Pattern.compile(PATTERN_VOWELS);

    public FrequencyWord(String word) {
        this.word = word;
        this.frequency = 0;
        calculateFrequency();
    }

    public String getWord() {
        return word;
    }

    public double getFrequency() {
        return frequency;
    }

    private void calculateFrequency() {
        int vowelsCount = 0;

        for (char letter : word.toCharArray()) {
            if (vowelsPattern.matcher(String.valueOf(letter)).matches()) {
                vowelsCount++;
            }
        }

        if (vowelsCount == 0) {
            frequency = word.length();
        } else {
            frequency = vowelsCount / (double) word.length();
        }
    }

    @Override
    public String toString() {
        return word + "[" + frequency + "]";
    }

    @Override
    public int compareTo(FrequencyWord frequencyWord) {
        return (int) ((this.frequency * FREQUENCY_SCALE) - (frequencyWord.frequency * FREQUENCY_SCALE));
    }
}
