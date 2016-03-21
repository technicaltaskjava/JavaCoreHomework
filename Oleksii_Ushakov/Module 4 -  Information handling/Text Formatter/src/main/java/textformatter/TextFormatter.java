package textformatter;

import textformatter.textformatterutils.TextPatterns;
import textformatter.textformatterutils.TextSorter;

import java.io.*;
import java.util.regex.Pattern;

/**
 * @author Alexey Ushakov
 */
public class TextFormatter implements TextPatterns {

    private static String getPathForReport(File outputFile, String reportName) {
        return outputFile.getParent() + System.getProperty("file.separator") + reportName + "_" + outputFile.getName();
    }

    private static String[] splitOnSentences(String paragraph) {
        return paragraph.replaceAll(PATTERN_TABS_OR_SPACES, " ").trim().split(PATTERN_NEW_SENTENCE);
    }

    public static void reverseFirstLastWords(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
             Writer writer = new FileWriter(getPathForReport(file, "swapped"))) {
            String paragraph = reader.readLine();

            while (paragraph != null) {
                String[] sentences = splitOnSentences(paragraph);
                StringBuilder newParagraph = new StringBuilder(paragraph.length());

                for (String sentence : sentences) {
                    StringBuilder sentenceBuilder = new StringBuilder(sentence);

                    int indexFirstSpace = sentenceBuilder.indexOf(" ");
                    int indexLastSpace = sentenceBuilder.lastIndexOf(" ");

                    if (indexFirstSpace != -1) {
                        int containPoint = 0;
                        if (sentenceBuilder.lastIndexOf(".") != -1) {
                            containPoint = 1;
                        }
                        newParagraph.append(sentenceBuilder.subSequence(indexLastSpace + 1, sentenceBuilder.length() - containPoint));
                        newParagraph.append(sentenceBuilder.substring(indexFirstSpace, indexLastSpace + 1));
                        newParagraph.append(sentenceBuilder.subSequence(0, indexFirstSpace));
                        if (containPoint == 1) {
                            newParagraph.append(". ");
                        }
                    } else {
                        writer.write(String.valueOf(sentenceBuilder));
                    }
                }
                writer.write(String.valueOf(newParagraph).trim() + '\n');
                paragraph = reader.readLine();
            }
        }
    }

    public static void sortWordTextAscendingVowels(File file, int newLineLength)
            throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
             Writer writer = new FileWriter(getPathForReport(file, "sorted"))) {
            TextSorter.read(reader);
            TextSorter.sort();
            TextSorter.write(writer, newLineLength);
        }
    }

    public static void removeWordsByLength(File file, int wordLength) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
             Writer writer = new FileWriter(getPathForReport(file, "deletedByLength"))) {
            String paragraph = reader.readLine();
            Pattern patternConsonants = Pattern.compile(PATTERN_CONSONANTS);
            Pattern patternDeletedWord = Pattern.compile("[а-яА-Я]{" + wordLength + "}" + PATTERN_TYPOGRAPHIC_SYMBOL + "*");

            while (paragraph != null) {
                String[] words = paragraph.replaceAll(PATTERN_TABS_OR_SPACES, " ").split(" ");
                StringBuilder newParagraph = new StringBuilder(paragraph.length());

                for (String word : words) {
                    if (patternConsonants.matcher(String.valueOf(word.charAt(0))).matches()) {
                        if (patternDeletedWord.matcher(word).matches()) {
                            continue;
                        }
                    }
                    newParagraph.append(word);
                    newParagraph.append(' ');
                }

                newParagraph.replace(newParagraph.length() - 1, newParagraph.length(), "\n");
                writer.write(String.valueOf(newParagraph));
                paragraph = reader.readLine();
            }
        }
    }

    private static boolean isEqualsChars(Character first, Character second) {
        int result = first.compareTo(second);
        if ((result == 0) || (+result == 32)) {
            return true;
        }
        return false;
    }

    public static void removeAllOccurrenceOfFirstLetter(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
             Writer writer = new FileWriter(getPathForReport(file, "deleteAllOccurrence"))) {
            String paragraph = reader.readLine();

            while (paragraph != null) {
                String[] sentences = splitOnSentences(paragraph);
                StringBuilder newParagraph = new StringBuilder(paragraph.length());

                if (paragraph.length() != 0) {
                    for (String sentence : sentences) {
                        char firstLetter = sentence.charAt(0);
                        newParagraph.append(firstLetter);

                        for (int i = 1; i < sentence.length(); i++) {
                            if (sentence.charAt(i) == ' ') {
                                firstLetter = sentence.charAt(i + 1);
                                newParagraph.append(' ');
                                newParagraph.append(sentence.charAt(i + 1));
                                continue;
                            }

                            if (!isEqualsChars(firstLetter, sentence.charAt(i))) {
                                newParagraph.append(sentence.charAt(i));
                            }
                        }

                        newParagraph.append(' ');

                    }
                }

                writer.write(String.valueOf(newParagraph));
                writer.write('\n');
                paragraph = reader.readLine();
            }
        }
    }

}
