package javafilereader;

import java.io.*;

/**
 * @author Alexey Ushakov
 */
public class JavaCharReader extends JavaReader {

    public JavaCharReader(String filePath) {
        super(filePath);
    }

    @Override
    public void checkReservedWord() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
            String line = reader.readLine();
            while (line != null) {
                line = line.replaceAll("([^a-zA-Z]+|[ ]+|,)", " ").trim();
                String[] words = line.split(" ");
                for (String word : words) {
                    if (word.length() > 1) {
                        if (isReserved(word)) {
                            addReservedWord(word);
                        }
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        }
    }

    @Override
    public void writeReport() throws IOException {
        writeReport("JavaCharReaderReport.log");
    }

    @Override
    public void writeReport(String reportPath) throws IOException {
        try (Writer output = new FileWriter(reportPath)) {
            output.write(("Words count " + getReservedWordsCount() + "\n\n"));

            for (String word : getReservedWords()) {
                output.write((word + "\n"));
            }

            output.close();
        }
    }
}
