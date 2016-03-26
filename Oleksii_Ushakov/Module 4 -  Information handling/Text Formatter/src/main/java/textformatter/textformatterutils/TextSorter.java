package textformatter.textformatterutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/**
 * @author Alexey Ushakov
 */

public class TextSorter implements TextPatterns {
    private static FrequencyWord[] frequencyWords;
    private static int frequencyWordsCount = 0;

    private static void resizeWordsArray() {
        FrequencyWord[] newWords = new FrequencyWord[frequencyWords.length * 2];
        System.arraycopy(frequencyWords, 0, newWords, 0, frequencyWords.length);
        frequencyWords = newWords;
    }

    private static void addFrequencyWord(FrequencyWord word) {
        if (frequencyWordsCount == frequencyWords.length) {
            resizeWordsArray();
        }
        frequencyWords[frequencyWordsCount] = word;
        frequencyWordsCount++;
    }

    public static void read(BufferedReader reader) throws IOException {
        frequencyWords = new FrequencyWord[200];
        String paragraph = reader.readLine();

        while (paragraph != null) {
            String[] words = paragraph.replaceAll("[[^а-яА-Я0-9 ]|" + PATTERN_TYPOGRAPHIC_SYMBOL + "]", "").split(PATTERN_TABS_OR_SPACES);

            if (paragraph.length() > 0) {
                for (String word : words) {
                    addFrequencyWord(new FrequencyWord(word));
                }
            }

            paragraph = reader.readLine();
        }
    }

    public static void sort() {
        frequencyWords = Arrays.copyOfRange(frequencyWords, 0, frequencyWordsCount);
        Arrays.sort(frequencyWords);
    }

    public static void write(Writer writer, int lineLength) throws IOException {
        for (int i = 0; i < frequencyWordsCount; i++) {
            writer.write(frequencyWords[i].toString() + " ");
            if ((i + 1) % lineLength == 0) {
                writer.write('\n');
            }
        }
    }

}
