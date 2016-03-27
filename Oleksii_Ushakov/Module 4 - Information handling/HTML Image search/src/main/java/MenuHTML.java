import com.augustprime.consoledialog.ConsoleDialog;
import htmlimagesearch.HTMLImageSearch;

import java.io.IOException;

/**
 * @author Alexey Ushakov
 */
public class MenuHTML {
    private static HTMLImageSearch imageSearch;

    static {
        imageSearch = new HTMLImageSearch();
        ConsoleDialog.addMenuItems(new String[]{ "Download HTML" });
        ConsoleDialog.setInput(System.in);
        ConsoleDialog.setOutput(System.out);
    }

    private static void printLinks(String[] links) {
        ConsoleDialog.println();
        for (int i = 0; i < links.length; i++) {
            ConsoleDialog.println("Link " + (i + 1) + ": " + links[i] + '\n');
        }
    }

    public static void dialogUseCustomHTML() {
        try {
            String pathToFile = ConsoleDialog.getUserAnswerString("Input path to file ");
            imageSearch.load(pathToFile.replaceAll("'", "")); /*Drag&Drop in linux puts on the edges ' */
            printLinks(imageSearch.getLinksOnImage());
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
                        dialogUseCustomHTML();
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
