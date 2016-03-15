package main.java.com.epam.t04;

import main.java.com.epam.StreamsAndExceptions;
import main.java.com.epam.t01.WorkWithFile;
import main.java.com.epam.t01.WorkWithFolder;

import java.io.*;

public class WorkWithCharIO {

    public static void mainMenu() {

        boolean returnToMainMenu = false;
        while (returnToMainMenu == false) {
            System.out.println("Current folder is: "+WorkWithFolder.currentFolder);
            System.out.println("Choose the action:");
            System.out.println("[1] Print out current directory.");
            System.out.println("[2] Change directory.");
            System.out.println("[3] Count keywords in java file (using CharIO).");
            System.out.println("[4] Return to main menu.");

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
                        System.out.println("Enter filename of java-file");
                        String currentLine = StreamsAndExceptions.userInput.nextLine();
                        System.out.println("Enter filename of new out txt-file");
                        String currentLineOut = StreamsAndExceptions.userInput.nextLine();

                        try {
                            String totalText = readTextFromFile(currentLine);
                            Integer [] entranceArray;
                            entranceArray = JavaKeywordsFinder.countKeywordEntrance(totalText);
                            writeResultToFile(currentLineOut, entranceArray);
                            System.out.println("Results saved at file '" +currentLineOut+ ".txt'");
                        }
                        catch (IOException methodException) {
                            System.out.println("Exception!");
                            if (methodException.getMessage().equals("File does not exist")) {
                                System.out.println("JAVA File '" +currentLine+ ".java' does not exist.");
                            }
                            if (methodException.getMessage().equals("File already exists")) {
                                System.out.println("TXT File '" +currentLineOut+ ".txt' already exists.");
                            }
                        }
                        break;
                    }

                    case 4: {
                        returnToMainMenu = true;
                        break;
                    }
                    default: {
                        System.out.println("Incorrect number. You must input one digit from 1 to 4.\n");
                        break;
                    }
                }
            }
            catch (NumberFormatException userException) {
                System.out.println("Incorrect input. You must input digit from 1 to 4.\n");
            }
        }
    }

    static public String readTextFromFile(String newFile) throws IOException {
        File currentFile = new File(WorkWithFile.getCurrentFilePath(newFile) + ".java");
        if(currentFile.exists()) {
                String newData=" ";
                BufferedReader javaFileReader = new BufferedReader(new FileReader(currentFile));
                String currentLine = null;
                while ((currentLine = javaFileReader.readLine()) != null) {
                    newData = newData + currentLine + " ";
                }
                javaFileReader.close();
                return newData;
        }
        else {
            throw new IOException("File does not exist");
        }
    }

    static public void writeResultToFile(String newFile, Integer[] entranceArray) throws IOException {
        File currentFile = new File(WorkWithFile.getCurrentFilePath(newFile) + ".txt");
        if(!currentFile.exists()) {
            FileWriter currentWritter = new FileWriter(WorkWithFile.getCurrentFilePath(newFile) + ".txt");
            for (int indexArray = 0; indexArray < entranceArray.length; indexArray++) {
                currentWritter.write(JavaKeywordsFinder.keywordsArray[indexArray] + "=");
                currentWritter.write(Integer.toString(entranceArray[indexArray]));
                currentWritter.write(System.getProperty("line.separator"));
            }
            currentWritter.close();
        }
        else {
            throw new IOException("File already exists");
        }
    }
}
