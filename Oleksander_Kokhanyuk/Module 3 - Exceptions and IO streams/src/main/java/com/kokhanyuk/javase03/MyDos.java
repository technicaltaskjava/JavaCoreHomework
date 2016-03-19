package com.kokhanyuk.javase03;

import com.kokhanyuk.javase03.actions.MyDosOperation;
import com.kokhanyuk.javase03.actions.FileIO;
import com.kokhanyuk.javase03.actions.MyFileNotFound;

import javax.swing.*;
import java.util.Scanner;

import static java.io.File.separator;

/**
 * MyDos
 * <p/>
 * Emulation of access to file system,
 * view the contents of directories,
 * create, view and add information to the file.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MyDos {
    public static void main(String[] args) throws MyFileNotFound {

        String st = "";
        String fileName;
        System.out.println("\nMyDos v 1.0\nhelp :to view commands ");
        Scanner in = new Scanner(System.in);
        MyDosOperation dos = new MyDosOperation();
        JFileChooser chooser = new JFileChooser();
        String dirName = chooser.getCurrentDirectory().toString();
        while (st != "exit") {
            System.out.print(dirName + ">");
            st = in.nextLine();
            switch (st) {
                case "cd":
                    System.out.println("New current directory:");
                    String newDirName = in.nextLine();
                    dirName = dos.cd(newDirName);
                    System.out.println("");
                    break;
                case "dir":
                    dos.dir(dirName);
                    System.out.println("");
                    break;
                case "read":
                    System.out.println("Enter file name:");
                    fileName = in.nextLine();
                    System.out.println(FileIO.readSymbFile(dirName + separator + fileName));
                    System.out.println("");
                    break;
                case "write":
                    System.out.println("Enter file name:");
                    fileName = in.nextLine();
                    System.out.println("Type the text and press Enter:");
                    String text = in.nextLine();
                    FileIO.writeSymbFile(dirName + separator + fileName, text, true);
                    System.out.println("");
                    break;
                case "make":
                    System.out.println("Enter file name:");
                    fileName = in.nextLine();
                    dos.makeFile(dirName + separator + fileName);
                    System.out.println("");
                    break;
                case "rem":
                    System.out.println("Enter file name:");
                    fileName = in.nextLine();
                    dos.remFile(dirName + separator + fileName);
                    System.out.println("");
                    break;
                case "help":
                    System.out.println(FileIO.readSymbFile("iodata" + separator + "mydosmessage.txt"));
                    break;
                default:
                    System.out.println("Command no found.");
                    break;
                case "exit":
                    st = "exit";
                    break;
            }
        }
    }
}