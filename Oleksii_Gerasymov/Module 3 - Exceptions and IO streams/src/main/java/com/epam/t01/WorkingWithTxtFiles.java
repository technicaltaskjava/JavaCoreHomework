package main.java.com.epam.t01;

import main.java.com.epam.StreamsAndExceptions;
import java.io.IOException;

public class WorkingWithTxtFiles {

    public static void mainMenu() {

        boolean returnToMainMenu = false;
        while (!returnToMainMenu) {
            System.out.println("Current folder is: "+WorkWithFolder.currentFolder);
            System.out.println("Choose the action:");
            System.out.println("[1] Print out current directory.");
            System.out.println("[2] Change directory.");
            System.out.println("[3] Create txt-file.");
            System.out.println("[4] Write string to the txt-file.");
            System.out.println("[5] Delete txt-file.");
            System.out.println("[6] Return to main menu.");

            String userLine = StreamsAndExceptions.userInput.nextLine();

            try {
                int numberOfTask = Integer.parseInt(userLine);
                switch (numberOfTask) {

                    case 1: {
                        WorkWithFolder.printOutCurrentDirectory();
                        break;
                    }

                    case 2: {
                        System.out.println("Enter new directory.");
                        String currentLine = StreamsAndExceptions.userInput.nextLine();
                        if (WorkWithFolder.changeDirectory(currentLine)) {
                            System.out.println("Directory has changed.");
                        }
                        else {
                            System.out.println("Directory '" +currentLine+ "' does not exist.");
                        }
                        break;
                    }

                    case 3: {
                        System.out.println("Enter filename of new txt-file");
                        String currentLine = StreamsAndExceptions.userInput.nextLine();
                        try {
                            WorkWithFile.createTxtFile(currentLine);
                            System.out.println("File '" +currentLine+ "'.txt created.");
                        }
                        catch (IOException methodException) {
                            System.out.println("File '" +currentLine+ ".txt' was not created.");
                            if (methodException.getMessage().equals("File exists")) {
                                System.out.println("File '" +currentLine+ ".txt' already exists.");
                            }
                        }
                        break;
                    }

                    case 4: {
                        System.out.println("Enter filename of txt-file to edit");
                        String currentLine = StreamsAndExceptions.userInput.nextLine();
                        System.out.println("Enter string to add");
                        String currentLineToAdd = StreamsAndExceptions.userInput.nextLine();
                        try {
                            WorkWithFile.updateTxtFile(currentLine, currentLineToAdd);
                            System.out.println("String '" +currentLineToAdd+ "' added to file '" + currentLine + ".txt'.");
                        }
                        catch (IOException methodException) {
                            System.out.println("File '" +currentLine+ ".txt' was not updated.");
                            if (methodException.getMessage().equals("File does not exist")) {
                                System.out.println("File '" +currentLine+ ".txt' does not exist.");
                            }
                        }
                        break;
                    }

                    case 5: {
                        System.out.println("Enter filename of txt-file to delete");
                        String currentLine = StreamsAndExceptions.userInput.nextLine();
                        try {
                            WorkWithFile.deleteTxtFile(currentLine);
                            System.out.println("File '" +currentLine+ "'.txt deleted.");
                        }
                        catch (IOException methodException) {
                            System.out.println("File '" +currentLine+ ".txt' was not deleted.");
                            if (methodException.getMessage().equals("File does not exist")) {
                                System.out.println("File '" +currentLine+ ".txt' does not exist.");
                            }
                        }
                        break;
                    }

                    case 6: {
                        returnToMainMenu = true;
                        break;
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
