package com.kokhanyuk.javase03.actions;

import javax.swing.*;
import java.io.*;
import java.util.Date;

/**
 * MyDosOperation
 * <p/>
 * This class perfoms creation? editing the file, displays the content
 * of a directory and changes the current directory.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MyDosOperation {

    public void makeFile(String fileName) {
        File fp = new File(fileName);
        try {
            if (!fp.exists()) {
                fp.createNewFile();
                System.out.println("Created " + fileName);
            } else System.out.println("File " + fileName + " is exist");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void remFile(String fileName) {
        File fp = new File(fileName);
        if (fp.exists()) {
            fp.delete();
            System.out.println("Removed " + fileName);
        } else System.out.println("File " + fileName + " not found");
    }

    public String cd(String dirName) {
        File currentDir = new File(dirName);
        JFileChooser chooser = new JFileChooser();
        if (currentDir.exists() && currentDir.isDirectory() && currentDir.isAbsolute()) {
            chooser.setCurrentDirectory(new File(dirName));
        } else {
            System.out.println("Directory " + dirName + " not found");
        }
        return chooser.getCurrentDirectory().toString();
    }

    public void dir(String dirName) {//method prints to the console the contents of the specified directory
        try {
            File dir = new File(dirName);
            if (dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles();
                for (int i = 0; i < files.length; i++) {
                    Date date = new Date(files[i].lastModified());
                    System.out.print("\n" + files[i].getPath() + " \t| " + files[i].length() + "\t|" + date);
                }
            } else {
                System.out.println("Directory not found");
            }
        } catch (NullPointerException id1) {
            System.out.print("Empty directory name");
        }
    }
}