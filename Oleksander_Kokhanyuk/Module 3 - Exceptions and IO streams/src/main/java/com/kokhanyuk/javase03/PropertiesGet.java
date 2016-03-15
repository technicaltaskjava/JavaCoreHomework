package com.kokhanyuk.javase03;

import com.kokhanyuk.javase03.actions.FileIO;
import com.kokhanyuk.javase03.actions.MyFileNotFound;

import java.util.Scanner;

import static java.io.File.separator;

/**
 * PPropertiesGet
 * <p/>
 * This class offers *.property file,
 * reads data from it,
 * conducts a search key values.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class PropertiesGet {
    public static void main(String[] args) throws MyFileNotFound {

        String fileName = "iodata" + separator + "config.properties";
        String fileData = FileIO.readSymbFile(fileName);
        System.out.println("This properties file contain:\n " + fileData);
        String[] fileDataArr = fileData.split("\\n");
        System.out.println("Found this data:\n");
        for (int i = 0; i < fileDataArr.length; i++) {
            Scanner find = new Scanner(fileDataArr[i]);
            find.findInLine("=");
            if (find.hasNext()) {
                System.out.println("value key " + i + ": " + find.next());
            } else {
                System.out.println("Not contain the key properties.");
            }
        }
    }
}
