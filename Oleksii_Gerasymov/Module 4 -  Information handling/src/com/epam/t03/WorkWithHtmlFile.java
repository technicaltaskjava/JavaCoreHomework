package com.epam.t03;

import java.io.File;
import java.io.IOException;

public class WorkWithHtmlFile {
    public static String currentFolder = System.getProperty("user.dir");
    public static String currentInputFile = currentFolder + File.separator + "input.html";

    public static String getCurrentFilePath (String newFile) {
        return currentFolder + File.separator + newFile;
    }

    public static void printOutCurrentDirectory() {
        File currentDir = new File(currentFolder);
        String[] currentDirList = currentDir.list();
        for(int folderIndex = 0; folderIndex < currentDirList.length; folderIndex++)
        {
            File currentFile = new File(currentFolder + File.separator + currentDirList[folderIndex]);
            if((currentFile.isFile()) && (currentDirList[folderIndex].contains(".html"))) {
                System.out.println(currentDirList[folderIndex]);
            }
        }
    }

    public static boolean changeDirectory(String newDirectory) {
        File currentDir = new File(newDirectory);
        if ((currentDir.exists()) && (currentDir.isDirectory())) {
            currentFolder = newDirectory;
            return true;
        }
        else {
            return false;
        }
    }

    public static void changeInputFile(String newFile) throws IOException {
        File currentFile = new File(getCurrentFilePath(newFile) + ".html");
        if (!currentFile.exists()) {
            currentFile.createNewFile();
        }
        currentInputFile = currentFile.getName();
    }
}
