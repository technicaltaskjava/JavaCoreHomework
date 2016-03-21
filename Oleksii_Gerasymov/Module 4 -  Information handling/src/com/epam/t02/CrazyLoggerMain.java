package com.epam.t02;

import com.epam.IHandling;

public class CrazyLoggerMain {

    public static void mainMenu() {
        boolean returnToMainMenu = false;
        while (!returnToMainMenu) {
            System.out.println("Choose the action:");
            System.out.println("[1] Print full LOG data.");
            System.out.println("[2] Search LOG information by date (dd-MM-YYYY).");
            System.out.println("[3] Search LOG information by time (hh-mm).");
            System.out.println("[4] Search LOG information by text.");
            System.out.println("[5] Return to main menu.");

            String userLine = IHandling.userInput.nextLine();
            try {
                int numberOfTask = Integer.parseInt(userLine);
                switch (numberOfTask) {
                    case 1: {
                        IHandling.currentLogger.addLogData("Printing full LOG data.");
                        IHandling.currentLogger.outFullLogData();
                        break;
                    }

                    case 2: {
                        IHandling.currentLogger.addLogData("Searching at LOG by date.");
                        System.out.println("Enter searching date.");
                        userLine = IHandling.userInput.nextLine();
                        IHandling.currentLogger.searchLogData(userLine, 1);
                        break;
                    }

                    case 3: {
                        IHandling.currentLogger.addLogData("Searching at LOG by time.");
                        System.out.println("Enter searching time.");
                        userLine = IHandling.userInput.nextLine();
                        IHandling.currentLogger.searchLogData(userLine, 2);
                        break;
                    }

                    case 4: {
                        IHandling.currentLogger.addLogData("Searching at LOG by text.");
                        System.out.println("Enter searching text.");
                        userLine = IHandling.userInput.nextLine();
                        IHandling.currentLogger.searchLogData(userLine, 3);
                        break;
                    }

                    case 5: {
                        returnToMainMenu = true;
                        break;
                    }

                    default: {
                        IHandling.currentLogger.addLogData("Incorrect user input.");
                        System.out.println("Incorrect number. You must input one digit.\n");
                        break;
                    }
                }
            }
            catch (NumberFormatException userException) {
                IHandling.currentLogger.addLogData("Incorrect user input.");
                System.out.println("Incorrect input. You must input digit.\n");
            }
        }
    }
}
