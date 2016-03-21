import com.augustprime.consoledialog.ConsoleDialog;
import textformatter.TextFormatter;

import java.io.File;
import java.io.IOException;

/**
 * @author Alexey Ushakov
 */
public class MenuTextFormatter {
    static {
        ConsoleDialog.addMenuItems(new String[]{
                "Reversed in the sentence the first word to the last",
                "Sort text words ascending proportion of vowels",
                "Remove all words of a given length, beginning with a consonant letter",
                "Remove from the word all subsequent occurrences of the first letter of the word"
        });
    }

    private static File getInputFile() throws IOException {
        String pathToFile = ConsoleDialog.getUserAnswerString("Input path to file ").replaceAll("'", "");
        return new File(pathToFile);
    }

    private static void dialogReversedWords() {
        try {
            TextFormatter.reverseFirstLastWords(getInputFile());
            ConsoleDialog.println("Done");
        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    private static void dialogSortTextAscending() {
        try {

            ConsoleDialog.println("For readability using line breaks by the number of words. It is recommended to use 10-15");
            int length = ConsoleDialog.getUserAnswerInt("Input number of words in the sentence");

            if (length > 0) {
                TextFormatter.sortWordTextAscendingVowels(getInputFile(), length);
            } else {
                ConsoleDialog.println("Length must be greater than 0");
            }
            ConsoleDialog.println("Done");
        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    private static void dialogRemoveWords() {
        try {
            int length = ConsoleDialog.getUserAnswerInt("Input word length");

            if (length > 0) {
                TextFormatter.removeWordsByLength(getInputFile(), length);
                ConsoleDialog.println("Done");
            } else {
                ConsoleDialog.println("Length must be greater than 0");
            }

        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    private static void dialogRemoveSubsequent() {
        try {
            TextFormatter.removeAllOccurrenceOfFirstLetter(getInputFile());
            ConsoleDialog.println("Done");
        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        int userChoice = ConsoleDialog.EXIT_FROM_MENU + 1;

        while (userChoice != ConsoleDialog.EXIT_FROM_MENU) {
            try {
                ConsoleDialog.printMenu();
                userChoice = ConsoleDialog.getUserAnswerInt();

                switch (userChoice) {
                    case ConsoleDialog.EXIT_FROM_MENU:
                        ConsoleDialog.close();
                        break;
                    case 1:
                        dialogReversedWords();
                        break;
                    case 2:
                        dialogSortTextAscending();
                        break;
                    case 3:
                        dialogRemoveWords();
                        break;
                    case 4:
                        dialogRemoveSubsequent();
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
