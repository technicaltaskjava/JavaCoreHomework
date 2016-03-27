package com.epam.t01;

import com.epam.IHandling;

import java.io.IOException;

public class TxtHandleMain {

    public static void mainMenu() {
        boolean returnToMainMenu = false;
        while (!returnToMainMenu) {
            System.out.println("Current folder is: "+ WorkWithFile.currentFolder);
            System.out.println("Current input file is: "+ WorkWithFile.currentInputFile);
            System.out.println("Current output to file is : "+ WorkWithFile.currentOutputToFile);
            System.out.println("Current output file is: "+ WorkWithFile.currentOutputFile);

            System.out.println("Choose the action:");
            System.out.println("[1] Print out current directory txt-files.");
            System.out.println("[2] Change directory.");
            System.out.println("[3] Choose txt-file to handle.");
            System.out.println("[4] Set out data to System.out");
            System.out.println("[5] Set out data to txt-file.");
            System.out.println("[6] Replace first and last word.");
            System.out.println("[7] Sort txt-file by vowels proportion..");
            System.out.println("[8] Delete words with defined length and consonant first letter.");
            System.out.println("[9] Delete entrance of words first letter inside whole word.");
            System.out.println("[10] Return to main menu.");

            String userLine = IHandling.userInput.nextLine();
            try {
                int numberOfTask = Integer.parseInt(userLine);
                switch (numberOfTask) {
                    case 1: {
                        IHandling.currentLogger.addLogData("Printing current directory.");
                        WorkWithFile.printOutCurrentDirectory();
                        break;
                    }

                    case 2: {
                        IHandling.currentLogger.addLogData("Changing current directory.");
                        System.out.println("Enter new directory.");
                        userLine = IHandling.userInput.nextLine();
                        if (WorkWithFile.changeDirectory(userLine)) {
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
                        WorkWithFile.changeInputFile(userLine);
                        System.out.println("Input file has changed.");
                        break;
                    }

                    case 4: {
                        IHandling.currentLogger.addLogData("Changing output to system.out.");
                        WorkWithFile.currentOutputToFile = false;
                        break;
                    }

                    case 5: {
                        IHandling.currentLogger.addLogData("Changing output to file.");
                        WorkWithFile.currentOutputToFile = true;
                        break;
                    }

                    case 6: {
                        IHandling.currentLogger.addLogData("Replacing words in file.");
                        String resultData = TxtHandle.replaceFirstAndLastWord(WorkWithFile.currentInputFile);
                        TxtHandle.outResultData(resultData, WorkWithFile.currentOutputFile);
                        break;
                    }

                    case 7: {
                        IHandling.currentLogger.addLogData("Counting and Sorting by vowels proportion.");
                        VowelProportionSet proportionItems = TxtHandle.sortWordsByProportion(WorkWithFile.currentInputFile);
                        TxtHandle.outArrayResultData(proportionItems, WorkWithFile.currentOutputFile);
                        break;
                    }

                    case 8: {
                        IHandling.currentLogger.addLogData("Deleting words in file.");
                        System.out.println("Enter the length of deleting words.");
                        userLine = IHandling.userInput.nextLine();
                        int wordLength = Integer.parseInt(userLine);
                        String resultData = TxtHandle.deleteFirstConsonantWords(WorkWithFile.currentInputFile, wordLength);
                        TxtHandle.outResultData(resultData, WorkWithFile.currentOutputFile);
                        break;
                    }

                    case 9: {
                        IHandling.currentLogger.addLogData("Deleting repeated letters.");
                        String resultData = TxtHandle.deleteFirstRepeatedLetters(WorkWithFile.currentInputFile);
                        TxtHandle.outResultData(resultData, WorkWithFile.currentOutputFile);
                        break;
                    }

                    case 10: {
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
