package com.epam.javase03.task01.miniExplorer;

import java.io.File;
import java.io.IOException;

public class MiniExplorer {
    File file = new File("NewFileNew.txt");
    File directory = new File("DirectoryNew");

    public File getFile() {
        return file;
    }
    public void showDirectoryList() {
        int index = 0;
        if (directory.exists() && directory.isDirectory()) {
            String[] directoryContents = directory.list();
            for (index = 0; index < directoryContents.length; index++) {
                System.out.println(directoryContents[index]);
            }
            if (index == 0) {
                System.out.println("Directory is empty.");
            }
        }
    }
    public void createNewDirectory() {
        directory.mkdir();
    }
    public void getDirectoryPath() {
        System.out.println(directory.getAbsolutePath());
    }
    public void createFile() {
        try {
            if (file.createNewFile()) {
                System.out.println("File was created: " + file.getName());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void deleteFile() {
        if(file.exists()) {
            System.out.println("File was deleted: " + file.getName() + " " + file.delete());
        } else {
            System.out.println("File does not exists.");
       }
    }
}
