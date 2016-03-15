package main.java.com.epam.t01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static String getCurrentFilePath (String newFile) {
        return WorkWithFolder.currentFolder + File.separator + newFile;
    }

    public static void createTxtFile (String newFile) throws IOException {
        File currentFile = new File(getCurrentFilePath(newFile) + ".txt");
        if(!currentFile.exists()) {
            currentFile.createNewFile();
        }
        else {
            throw new IOException("File exists");
        }
    }

    public static void deleteTxtFile (String newFile) throws IOException {
        File currentFile = new File(getCurrentFilePath(newFile) + ".txt");
        if(currentFile.exists()) {
            currentFile.delete();
        }
        else {
            throw new IOException("File does not exist");
        }
    }

    public static void updateTxtFile (String newFile, String newFileLine) throws IOException {
        File currentFile = new File(getCurrentFilePath(newFile) + ".txt");
        if(currentFile.exists()) {
            FileWriter currentWritter = new FileWriter(getCurrentFilePath(newFile) + ".txt", true);

            currentWritter.write(System.getProperty("line.separator") + newFileLine);
            currentWritter.close();
        }
        else {
            throw new IOException("File does not exist");
        }
    }
}
