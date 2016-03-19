package com.epam.task1;

import java.util.Scanner;

/**
 * Created by Olga Kramska on 11-Mar-16.
 */
public class MainTask1 {
    public static void main(String[] args) {
        System.out.println("Please, enter a root directory path:");

        Scanner in = new Scanner(System.in);
        String dir = in.nextLine();
        IFileManager fileManager = new FileManager(dir);
        fileManager.printDirContent();

        fileManager.makeFile("abc.txt");
        fileManager.makeFile("def.txt");

        fileManager.updateFile("def.txt", "Test File Manager:");
        fileManager.updateFile("def.txt", "Test OK!!");

        fileManager.deleteFile("abc.txt");
    }
}
