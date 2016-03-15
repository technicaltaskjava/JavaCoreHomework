import javafilereader.JavaByteReader;
import javafilereader.JavaCharReader;
import javafilereader.JavaReader;
import consoledialog.ConsoleDialog;

import java.io.IOException;

/**
 * @author Alexey Ushakov
 */

public class MenuJavaFileReader {
    private static String pathToFile;

    static {
        ConsoleDialog.addMenuItems(new String[]{ "Read file using byte stream",
                "Read file using char stream" });
        ConsoleDialog.setInput(System.in);
        ConsoleDialog.setOutput(System.out);
        pathToFile = null;
    }

    private static void loadFile() {
        if ((pathToFile == null) || (!ConsoleDialog.askUser("Use file " + pathToFile))) {
            try {
                pathToFile = ConsoleDialog.getUserAnswerString("Input file");
            } catch (IOException e) {
                ConsoleDialog.println(e.getMessage());
            }
        }
    }

    private static void checkFile(JavaReader reader) {
        try {
            reader.checkReservedWord();
            String suffix = "Byte";
            if (reader instanceof JavaCharReader) {
                suffix = "Char";
            }
            ConsoleDialog.println("File checked. Find " +
                                          reader.getReservedWordsCount() + " reserved words");
            reader.writeReport(pathToFile.concat("report" + suffix + ".txt"));
        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

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
                        loadFile();
                        checkFile(new JavaByteReader(pathToFile));
                        break;
                    case 2:
                        loadFile();
                        checkFile(new JavaCharReader(pathToFile));
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
