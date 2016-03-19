package com.epam.task1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Olga Kramska on 13-Mar-16.
 */
public class FileManager implements IFileManager {
    private File rootDir;

    public FileManager(String rootDirPath) {
        rootDir = new File(rootDirPath);
    }

    @Override
    public void printDirContent() {
        printDir(rootDir);
    }

    @Override
    public boolean makeFile(String fileName) {
        boolean isCreated = false;
        try {
            isCreated = getAbsolutePath(fileName).createNewFile();
            if (isCreated) {
                System.out.println("File " + fileName + " was created");
            } else {
                System.out.println("Can not create file: " + fileName);
            }
        } catch (IOException e) {
            System.out.println("Can not create file: " + e.getMessage());
        }
        return isCreated;
    }

    @Override
    public boolean deleteFile(String fileName) {
        boolean isDeleted = getAbsolutePath(fileName).delete();
        if (isDeleted) {
            System.out.println("File " + fileName + " was deleted");
        } else {
            System.out.println("Can not delete file: " + fileName);
        }
        return isDeleted;
    }

    @Override
    public void updateFile(String fileName, String text) {
        try (PrintWriter add = new PrintWriter(new BufferedWriter(new FileWriter(getAbsolutePath(fileName), true)))) {
            add.println(text);
            System.out.println("Text was successfully written to the file");
        } catch (IOException e) {
            System.out.println("Can not change file or file does not exist: " + e.getMessage());
        }
    }

    private void printDir(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println(file.getAbsolutePath());
                    printDir(file);
                } else {
                    System.out.println(file.getName());
                }
            }
        } else {
            throw new IllegalArgumentException("This directory does not exist");
        }
    }

    private File getAbsolutePath(String fileName) {
        return new File(rootDir.getAbsolutePath() + "\\" + fileName);
    }
}
