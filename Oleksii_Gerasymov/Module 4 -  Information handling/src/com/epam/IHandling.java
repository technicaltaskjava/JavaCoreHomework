package com.epam;

import com.epam.t01.TxtHandleMain;
import com.epam.t02.CrazyLogger;
import com.epam.t02.CrazyLoggerMain;
import com.epam.t03.HtmlHandleMain;

import java.util.Scanner;

public class IHandling {

    public static Scanner userInput;
    public static CrazyLogger currentLogger = new CrazyLogger();

    public static void main(String[] args) {

        userInput = new Scanner(System.in);
        currentLogger.addLogData("Starting program.");

        while (true) {
            System.out.println("Enter the number of task:");
            System.out.println("[1] String Classes. Text-formating.");
            System.out.println("[2] StringBuilder. LOG-Class.");
            System.out.println("[3] String Classes. RegExp using. HTML-searching.");
            System.out.println("[4] Exit program.");

            String userLine = userInput.nextLine();

            try {
                int numberOfTask = Integer.parseInt(userLine);
                switch (numberOfTask) {
                    case 1: {
                        currentLogger.addLogData("Running first task.");
                        TxtHandleMain.mainMenu();
                        break;
                    }
                    case 2: {
                        currentLogger.addLogData("Running second task.");
                        CrazyLoggerMain.mainMenu();
                        break;
                    }
                    case 3: {
                        currentLogger.addLogData("Running third task.");
                        HtmlHandleMain.mainMenu();
                        break;
                    }
                    case 4: {
                        System.exit(0);
                    }
                    default: {
                        currentLogger.addLogData("Incorrect user input.");
                        System.out.println("Incorrect number. You must input one digit from 1 to 4.\n");
                        break;
                    }
                }
            }
            catch (NumberFormatException userException) {
                currentLogger.addLogData("Incorrect user input.");
                System.out.println("Incorrect input. You must input digit from 1 to 4.\n");
            }
        }
    }
}
