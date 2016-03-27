package javase04.t02.demo;

import javase04.common.library.ScannerUtils;
import javase04.t02.logger.CrazyLogger;
import java.io.IOException;
import java.util.Scanner;

/**
 * CrazyLogger demo class
 * Created by Yury Vislobodsky on 18.03.2016.
 */
public class CrazyLoggerDemo {
    private CrazyLogger logger;
    private Scanner in;

    public CrazyLoggerDemo() {
        logger = new CrazyLogger();
        in = new Scanner(System.in);
    }

    public void doLoadFromFile(String fileName) {
        logger.loadFromFile(fileName);
    }

    public void doSaveToFile(String fileName) {
        logger.saveToFile(fileName);
    }

    public void doAddNewRecord() {
        System.out.print("Input a record to add : ");
        in.nextLine();
        logger.addRecord(in.nextLine());
        System.out.println("Record is added!");
    }

    public void doClearLog() {
        if (ScannerUtils.tryToContinue(in, "Are you sure to clear all the records?") == 1) {
            logger.clear();
            System.out.println("All records are deleted!");
        }
    }

    public void doSearch() {
        System.out.print("Input a text to find (regex) : ");
        in.nextLine();
        try {
            logger.searchResultToStream(in.nextLine(), System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }

    public void doPrintLog() {
        try {
            logger.linesToStream(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("LOGGER MENU : ");
        System.out.println("1 - Add a new record");
        System.out.println("2 - Clear log");
        System.out.println("3 - Search");
        System.out.println("4 - Print log");
        System.out.println("0 - Quit");
    }

    public void doMainMenu() {
        int selectedItem;
        do {
            printMainMenu();
            selectedItem = ScannerUtils.tryToInputInteger(in, "Your choice : ");
            switch (selectedItem) {
                case 1: doAddNewRecord();
                    break;
                case 2: doClearLog();
                    break;
                case 3: doSearch();
                    break;
                case 4: doPrintLog();
                    break;
            }
            if (selectedItem != 0) {
                selectedItem = ScannerUtils.tryToContinue(in, "Continue?");
            }
        } while (selectedItem != 0);
    }

    public static void main(String[] args) {
        CrazyLoggerDemo demo = new CrazyLoggerDemo();
        final String fileName = "logger.txt";
        demo.doLoadFromFile(fileName);
        demo.doMainMenu();
        demo.doSaveToFile(fileName);
    }
}
