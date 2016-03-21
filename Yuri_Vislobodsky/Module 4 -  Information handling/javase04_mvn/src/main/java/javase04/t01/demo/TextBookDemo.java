package javase04.t01.demo;

import javase04.common.library.ScannerUtils;
import javase04.t01.textbook.TextBook;
import java.util.Scanner;

/**
 * TextBook demo class
 * Created by Yury Vislobodsky on 18.03.2016.
 */
public class TextBookDemo {
    private TextBook textBook;
    private Scanner in;
    private boolean demoSuccess;

    public TextBookDemo() {
        textBook = new TextBook();
        in = new Scanner(System.in);
        demoSuccess = true;
    }

    public void doLoadFromFile() {
        demoSuccess = textBook.loadFromFile("task02.txt");
        if (!demoSuccess) {
            demoSuccess = textBook.loadFromFile(ScannerUtils.tryToInputString(in, "Load from file : "));
        }
    }

    public void doSaveToFile() {
        if (demoSuccess) {
            demoSuccess = textBook.saveToFile(ScannerUtils.tryToInputString(in, "Save to file : "));
        }
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("SELECT A METHOD : ");
        System.out.println("1 - Method A (swap first and last word for every sentence)");
        System.out.println("2 - Method B (sort all the words by part of vowel letters)");
        System.out.println("3 - Method C (delete all the words of given length, starting with consonant letter)");
        System.out.println("4 - Method D (delete all next occurrences of first letter for every words)");
    }

    public void doMainMenu() {
        if (!demoSuccess) {
            return;
        }
        printMainMenu();
        switch (ScannerUtils.tryToInputInteger(in, "Your choice : ")) {
            case 1: textBook.methodA();
                break;
            case 2: textBook.methodB();
                break;
            case 3: textBook.methodC(ScannerUtils.tryToInputInteger(in, "Input word length : "));
                break;
            case 4: textBook.methodD();
                break;
            default: System.out.println("Invalid choice!");
                demoSuccess = false;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TextBookDemo demo = new TextBookDemo();
        demo.doLoadFromFile();
        demo.doMainMenu();
        demo.doSaveToFile();
    }
}
