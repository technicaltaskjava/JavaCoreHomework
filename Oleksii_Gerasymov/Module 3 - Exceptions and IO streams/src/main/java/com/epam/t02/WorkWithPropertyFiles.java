package main.java.com.epam.t02;

import main.java.com.epam.StreamsAndExceptions;
import main.java.com.epam.t01.WorkWithFile;
import main.java.com.epam.t01.WorkWithFolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WorkWithPropertyFiles {
    public static void mainMenu() {

        Properties propertyFile = null;

        boolean returnToMainMenu = false;
        while (returnToMainMenu == false) {
            System.out.println("Current folder is: "+WorkWithFolder.currentFolder);
            System.out.println("What do you want to do:");
            System.out.println("[1] Print out current directory.");
            System.out.println("[2] Change directory.");
            System.out.println("[3] Read properties-file.");
            System.out.println("[4] Find property.");
            System.out.println("[5] Return to main menu.");

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
                        if (WorkWithFolder.changeDirectory(currentLine) == true) {
                            System.out.println("Directory has changed.");
                        }
                        else {
                            System.out.println("Directory '" +currentLine+ "' does not exist.");
                        }
                        break;
                    }

                    case 3: {
                        System.out.println("Enter filename of properties-file");
                        String currentLine = StreamsAndExceptions.userInput.nextLine();
                        try {
                            propertyFile = loadPropertyFile(currentLine);
                            System.out.println("Properties file '" + currentLine + "' loaded.");
                        }
                        catch (IOException methodException) {
                            System.out.println("Exception!");
                            if (methodException.getMessage().equals("File does not exist")) {
                                System.out.println("File '" +currentLine+ ".properties' does not exist.");
                            }
                        }
                        break;
                    }

                    case 4: {
                        if (propertyFile != null) {
                            System.out.println("Enter property name to search");
                            String currentLineToSearch = StreamsAndExceptions.userInput.nextLine();
                            try {
                                String propertyValue = searchPropertyInFile(propertyFile, currentLineToSearch);
                                System.out.println("Value of property '" + currentLineToSearch + "' is " + propertyValue);
                            } catch (IOException methodException) {
                                System.out.println("Exception!");
                                if (methodException.getMessage().equals("Property not found")) {
                                    System.out.println("Property '" + currentLineToSearch + "' not found at file.");
                                }
                            }
                        }
                        else {
                            System.out.println("Properties file not loaded.");
                        }
                        break;
                    }

                    case 5: {
                        returnToMainMenu = true;
                        break;
                    }
                    default: {
                        System.out.println("Incorrect number. You must input one digit from 1 to 5.\n");
                        break;
                    }
                }
            }
            catch (NumberFormatException userException) {
                System.out.println("Incorrect input. You must input digit from 1 to 5.\n");
            }
        }
    }

    public static Properties loadPropertyFile(String currentLine) throws IOException {
        File currentFile = new File(WorkWithFile.getCurrentFilePath(currentLine) + ".properties");
        if(currentFile.exists()) {
            FileInputStream  newStream = new FileInputStream(currentFile);
            Properties propertiesLoad = new Properties();
            propertiesLoad.load(newStream);
            return propertiesLoad;
        }
        else {
            throw new IOException("File does not exist");
        }
    }

    public static String searchPropertyInFile(Properties propertyFile, String currentLineToSearch) throws IOException {
                    String resultValue = propertyFile.getProperty(currentLineToSearch);
                    if (resultValue != null) {
                        return resultValue;
                    }
                    else {
                        throw new IOException("Property not found");
                    }
    }
}
