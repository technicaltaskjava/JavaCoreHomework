import propertiesfilereader.PropertiesFileReader;
import propertiesfilereader.exceptions.PropertiesFileNotFoundException;
import consoledialog.ConsoleDialog;

import java.io.IOException;

/**
 * @author Alexey Ushakov
 */
public class MenuPropertiesFileReader {

    private static PropertiesFileReader reader;

    static {
        ConsoleDialog.addMenuItems(new String[]{ "Load property file",
                "View properties keys", "Get property"
        });
    }

    public static void dialogLoadProperty() {
        try {
            String fileName = ConsoleDialog.getUserAnswerString("Input property file");
            reader.read(fileName);
            ConsoleDialog.println("Properties file loaded");
        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    public static void dialogViewPropertiesKey() {
        try {
            String[] propertiesList = reader.getProperties();
            for (String property : propertiesList) {
                ConsoleDialog.println(property);
            }
            ConsoleDialog.println("Keys count: " + reader.size());
        } catch (PropertiesFileNotFoundException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    public static void dialogGetProperty() {
        try {
            if (!reader.isEmpty()) {
                String key = ConsoleDialog.getUserAnswerString("Input key");
                ConsoleDialog.println(reader.getProperty(key));
            } else {
                ConsoleDialog.println("Properties file is empty");
            }
        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        reader = new PropertiesFileReader();
        int userChoice = -1;

        while (userChoice != ConsoleDialog.EXIT_CODE) {
            try {
                ConsoleDialog.printMenu();
                userChoice = ConsoleDialog.getUserAnswerInt();

                switch (userChoice) {
                    case ConsoleDialog.EXIT_CODE:
                        ConsoleDialog.close();
                        break;
                    case 1:
                        dialogLoadProperty();
                        break;
                    case 2:
                        dialogViewPropertiesKey();
                        break;
                    case 3:
                        dialogGetProperty();
                        break;
                    default:
                        ConsoleDialog.print("Unknown menu item " + userChoice);
                        break;
                }

                ConsoleDialog.println();
            } catch (Exception e) {
                ConsoleDialog.close();
                break;
            }
        }
    }
}
