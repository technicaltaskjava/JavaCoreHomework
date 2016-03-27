import com.augustprime.consoledialog.ConsoleDialog;
import logger.CrazyLogger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Alexey Ushakov
 */
public class MenuLogger {
    private static final String STOP_TYPING_WORD = "stop logging";
    public static CrazyLogger crazyLogger;

    static {
        crazyLogger = new CrazyLogger();
        ConsoleDialog.addMenuItems(new String[]{ "Add to log from console",
                "Add to log from file", "Show log messages", "Find in log",
                "Save log to file" });
    }

    private static void addMessageFromConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("For exit input \"" + STOP_TYPING_WORD + "\"");

            String inputLine = reader.readLine();
            while (inputLine != null && !inputLine.equalsIgnoreCase(STOP_TYPING_WORD)) {
                crazyLogger.addToLog(inputLine);
                inputLine = reader.readLine();
            }

        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    private static void addMessageFromFile() {
        try {
            String pathToFile = ConsoleDialog.getUserAnswerString("Input file name").replaceAll("'", "");
            try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
                String line = reader.readLine();
                while (line != null) {
                    crazyLogger.addToLog(line);
                    line = reader.readLine();
                }
            }
            ConsoleDialog.println("File loaded");
        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }

    }

    private static void findInLog() {
        try {
            String searchString = ConsoleDialog.getUserAnswerString("Input the search string");
            String[] searchResult = crazyLogger.search(searchString);

            if (searchResult.length != 0) {
                for (String result : searchResult) {
                    ConsoleDialog.println(result);
                }
            } else {
                ConsoleDialog.println("String " + searchString + " not found");
            }

        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        int userChoice = ConsoleDialog.EXIT_FROM_MENU + 1; /*anything except EXIT_FROM_MENU*/

        while (userChoice != ConsoleDialog.EXIT_FROM_MENU) {
            try {
                ConsoleDialog.printMenu();
                userChoice = ConsoleDialog.getUserAnswerInt();

                switch (userChoice) {
                    case ConsoleDialog.EXIT_FROM_MENU:
                        ConsoleDialog.close();
                        break;
                    case 1:
                        addMessageFromConsole();
                        break;
                    case 2:
                        addMessageFromFile();
                        break;
                    case 3:
                        crazyLogger.printLog(System.out);
                        break;
                    case 4:
                        findInLog();
                        break;
                    case 5:
                        crazyLogger.saveToFile();
                        break;
                    default:
                        ConsoleDialog.print("Unknown menu item " + userChoice);
                        break;
                }

                ConsoleDialog.println();
            } catch (Exception e) {
                ConsoleDialog.println(e.getMessage());
                ConsoleDialog.close();
                break;
            }
        }
    }

}
