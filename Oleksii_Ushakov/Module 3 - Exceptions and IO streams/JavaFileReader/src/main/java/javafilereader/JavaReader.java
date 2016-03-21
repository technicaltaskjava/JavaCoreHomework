package javafilereader;

import javafilereader.wordchecker.ReservedWordChecker;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Alexey Ushakov
 */
public abstract class JavaReader {
    private String filePath;
    private String[] reservedWords;
    private int reservedWordsCount;

    public JavaReader(String filePath) {
        this.filePath = new File(filePath).getAbsolutePath();
        this.reservedWordsCount = 0;
        this.reservedWords = new String[ReservedWordChecker.length()];
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String[] getReservedWords() {
        return Arrays.copyOfRange(reservedWords, 0, reservedWordsCount);
    }

    public String getReservedWord(int index) {
        if ((index >= 0) && (index < reservedWordsCount)) {
            return reservedWords[index];
        } else {
            throw new ArrayIndexOutOfBoundsException("Wrong index " + index +
                                                             ". Must be in [0:" + reservedWordsCount + "]");
        }
    }

    public int getReservedWordsCount() {
        return reservedWordsCount;
    }

    public void addReservedWord(String word) {
        for (int i = 0; i < reservedWordsCount; i++) {
            if (reservedWords[i].equals(word)) {
                return;
            }
        }
        reservedWords[reservedWordsCount++] = word;
    }

    public boolean isReserved(String word) {
        return ReservedWordChecker.isReservedWord(word);
    }

    public abstract void checkReservedWord() throws IOException;

    public abstract void writeReport() throws IOException;

    public abstract void writeReport(String reportPath) throws IOException;
}
