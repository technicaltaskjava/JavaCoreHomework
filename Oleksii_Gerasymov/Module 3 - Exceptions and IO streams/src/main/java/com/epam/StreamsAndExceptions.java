package main.java.com.epam;

import main.java.com.epam.t01.WorkingWithTxtFiles;
import main.java.com.epam.t02.WorkWithPropertyFiles;
import main.java.com.epam.t03.WorkWithByteIO;
import main.java.com.epam.t04.WorkWithCharIO;
import main.java.com.epam.t05.FilmFactory;

import java.util.Scanner;

public class StreamsAndExceptions {

    public static Scanner userInput;

    public static void main(String[] args) {
        userInput = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the number of task:");
            System.out.println("[1] Working with txt-files with Exception managing.");
            System.out.println("[2] Reading and Searching in propereties-files. Exception managing.");
            System.out.println("[3] Find java-keywords using byte-streams.");
            System.out.println("[4] Find java-keywords using character-streams.");
            System.out.println("[5] Film Collection with serialization.");
            System.out.println("[6] Exit program.");

            String userLine = userInput.nextLine();

            try {
                int numberOfTask = Integer.parseInt(userLine);
                switch (numberOfTask) {
                    case 1: {
                        WorkingWithTxtFiles.mainMenu();
                        break;
                    }
                    case 2: {
                        WorkWithPropertyFiles.mainMenu();
                        break;
                    }
                    case 3: {
                        WorkWithByteIO.mainMenu();
                        break;
                    }
                    case 4: {
                        WorkWithCharIO.mainMenu();
                        break;
                    }
                    case 5: {
                        FilmFactory.mainMenu();
                        break;
                    }
                    case 6: {
                        userInput.close();
                        System.exit(0);
                    }
                    default: {
                        System.out.println("Incorrect number. You must input one digit from 1 to 6.\n");
                        break;
                    }
                }
            }
            catch (NumberFormatException userException) {
                System.out.println("Incorrect input. You must input digit from 1 to 6.\n");
            }
        }
    }
}
