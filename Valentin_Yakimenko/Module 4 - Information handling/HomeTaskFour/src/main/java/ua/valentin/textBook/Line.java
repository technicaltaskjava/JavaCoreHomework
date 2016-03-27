package ua.valentin.textBook;

import java.util.LinkedList;
import java.util.List;

/**
 * Line of Book.
 * Created by valentin.yakimenko on 22.03.16.
 */
public class Line {
    private final static String LINE_SEPARATOR = "\n";
    private Sentence sentences;
    private List<Line> lines;

    public Line(String line) {
        lines = new LinkedList<>();
        sentences = new Sentence("Sentences");
        sentences.addSentences(parseLineForSentences(line));
    }

    public void addLines(List<Line> newLines) {
        lines.addAll(newLines);
    }

    private List<Sentence> parseLineForSentences(String newLine) {
        return Parser.getSentencesFromLine(newLine);
    }

    public String getLines() {
        StringBuilder result = new StringBuilder();
        for (Line line : lines) {
            result.append(line.sentences.getSentences()).append(LINE_SEPARATOR);
        }
        return result.toString();
    }

    public String getLinesAsString() {
        StringBuilder result = new StringBuilder();
        for (Line line : lines) {
            result.append(line.sentences.getSentencesAsString()).append(LINE_SEPARATOR);
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return sentences.getSentencesAsString();
    }
 }
