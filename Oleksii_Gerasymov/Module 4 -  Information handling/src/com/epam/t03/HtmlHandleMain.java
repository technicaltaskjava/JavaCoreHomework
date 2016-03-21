package com.epam.t03;

import com.epam.IHandling;
import java.io.IOException;

public class HtmlHandleMain {

    public static void mainMenu() {
        boolean returnToMainMenu = false;
        while (!returnToMainMenu) {
            System.out.println("Current folder is: "+ WorkWithHtmlFile.currentFolder);
            System.out.println("Current input file is: "+ WorkWithHtmlFile.currentInputFile);

            System.out.println("Choose the action:");
            System.out.println("[1] Print out current directory html-files.");
            System.out.println("[2] Change directory.");
            System.out.println("[3] Choose html-file to handle.");
            System.out.println("[4] Find and out sentences with picture links.");
            System.out.println("[5] Return to main menu.");

            String userLine = IHandling.userInput.nextLine();
            try {
                int numberOfTask = Integer.parseInt(userLine);
                switch (numberOfTask) {
                    case 1: {
                        IHandling.currentLogger.addLogData("Printing current directory.");
                        WorkWithHtmlFile.printOutCurrentDirectory();
                        break;
                    }

                    case 2: {
                        IHandling.currentLogger.addLogData("Changing current directory.");
                        System.out.println("Enter new directory.");
                        userLine = IHandling.userInput.nextLine();
                        if (WorkWithHtmlFile.changeDirectory(userLine)) {
                            System.out.println("Directory has changed.");
                        }
                        else {
                            IHandling.currentLogger.addLogData("Incorrect directory input.");
                            System.out.println("Directory '" +userLine+ "' does not exist.");
                        }
                        break;
                    }

                    case 3: {
                        IHandling.currentLogger.addLogData("Changing input file.");
                        System.out.println("Enter new filename.");
                        userLine = IHandling.userInput.nextLine();
                        WorkWithHtmlFile.changeInputFile(userLine);
                        System.out.println("Input file has changed.");
                        break;
                    }

                    case 4: {
                        IHandling.currentLogger.addLogData("Searching at HTML-file.");
                        String resultData = HtmlHandle.searchPictureLinks(WorkWithHtmlFile.currentInputFile);
                        HtmlHandle.outResultData(resultData);
                        break;
                    }

                    case 5: {
                        IHandling.currentLogger.addLogData("Returning to main menu.");
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
            catch (IOException e) {
                IHandling.currentLogger.addLogData("Incorrect filename.");
                System.out.println("Incorrect filename");
            }
        }
    }
}
