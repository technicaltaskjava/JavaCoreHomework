package javafilereader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Alexey Ushakov
 */
public class JavaByteReader extends JavaReader {
    private static final int[] BYTE_CODE_ARRAY = { -1, 10, 32, 34, 40, 41, 42,
            43, 44, 45, 46, 47, 59, 91, 93, 125, 123 };

    public JavaByteReader(String filePath) {
        super(filePath);
    }

    private boolean isCorrectCode(int code) {
        for (int byteCode : BYTE_CODE_ARRAY) {
            if (code == byteCode) {
                return false;
            }
        }
        return true;
    }

    public void checkReservedWord() throws IOException {
        try (FileInputStream input = new FileInputStream(getFilePath())) {
            while (input.available() > 0) {
                int symbolCode = input.read();
                char[] line = new char[200];
                int lineLength;

                for (lineLength = 0; isCorrectCode(symbolCode); lineLength++) {
                    line[lineLength] = (char) symbolCode;
                    symbolCode = input.read();
                }

                String word;
                if (lineLength > 1) {
                    word = String.valueOf(Arrays.copyOfRange(line, 0, lineLength));
                    if (isReserved(word)) {
                        addReservedWord(word);
                    }
                }
            }
            input.close();
        }
    }

    public void writeReport() throws IOException {
        writeReport("JavaByteReaderReport.log");
    }

    public void writeReport(String reportPath) throws IOException {
        try (FileOutputStream output = new FileOutputStream(reportPath)) {
            output.write(("Words count " + getReservedWordsCount() + "\n\n").getBytes());

            for (String word : getReservedWords()) {
                output.write((word + "\n").getBytes());
            }

            output.close();
        }
    }
}
