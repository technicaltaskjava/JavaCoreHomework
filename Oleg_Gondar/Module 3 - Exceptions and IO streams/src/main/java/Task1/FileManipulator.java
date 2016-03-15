package Task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Oleg on 14.03.2016.
 */
public class FileManipulator {

    private File file;

    public FileManipulator(String fileName) {
        file = new File(fileName);
    }

    public void showFile() throws IOException {
        FileReader fileReader = new FileReader(file);
        char[] buffer = new char[(int) file.length()];
        fileReader.read(buffer);
        System.out.println(buffer);
        fileReader.close();
    }

    public void deleteFile() {
        Path path = Paths.get(file.getAbsolutePath());
        try {

            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String wrstr) throws IOException {
        FileWriter fileWriter = new FileWriter(file, false);
        filePerformWrite(fileWriter, wrstr);
    }

    public void appendFile(String appstr) throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        filePerformWrite(fileWriter, appstr);

    }

    private void filePerformWrite(FileWriter fileWriter, String stringToWrite) throws IOException {
        fileWriter.write(stringToWrite);
        fileWriter.flush();
        fileWriter.close();
    }

}
