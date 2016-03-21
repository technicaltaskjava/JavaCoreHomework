package com.epam.t01;

import java.io.File;
import java.io.IOException;

public class WorkWithFile {
    public static String currentFolder = System.getProperty("user.dir");
    public static String currentInputFile = currentFolder + File.separator + "input.txt";
    public static String currentOutputFile = currentFolder + File.separator + "output.txt";
    public static boolean currentOutputToFile = true;

    public static String getCurrentFilePath (String newFile) {
        return currentFolder + File.separator + newFile;
    }

    public static void printOutCurrentDirectory () {
        File currentDir = new File(currentFolder);
        String[] currentDirList = currentDir.list();
        for (String folderIndex : currentDirList) {
            File currentFile = new File(currentFolder + File.separator + folderIndex);
            if ((currentFile.isFile()) && (folderIndex.contains(".txt"))) {
                System.out.println(folderIndex);
            }
        }
    }

    public static boolean changeDirectory (String newDirectory) {
        File currentDir = new File(newDirectory);
        if ((currentDir.exists()) && (currentDir.isDirectory())) {
            currentFolder = newDirectory;
            return true;
        }
        else {
            return false;
        }
    }

    public static void changeInputFile (String newFile) throws IOException {
        File currentFile = new File(getCurrentFilePath(newFile) + ".txt");
        if (!currentFile.exists()) {
            currentFile.createNewFile();
        }
        currentInputFile = currentFile.getName();
    }
}
