import filemanager.FileManager;
import filemanager.filesystemviewer.FileSystemViewer;
import consoledialog.ConsoleDialog;

import java.io.File;
import java.io.IOException;

/**
 * @author Alexey Ushakov
 */
public class MenuFileManager {
    private static FileManager manager;

    static {
        ConsoleDialog.addMenuItems(new String[]{ "View file system",
                "Create text file", "Edit text file", "Delete text file" });
        ConsoleDialog.setInput(System.in);
        ConsoleDialog.setOutput(System.out);
        manager = new FileManager();
    }

    private static void dialogViewFileSystem() {
        try {
            String root = ConsoleDialog.getUserAnswerString("Input folder to view");
            String[] tree = FileSystemViewer.viewFolder(root);
            for (String path : tree) {
                ConsoleDialog.println(path);
            }
        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    private static void dialogCreateTextFile() {
        try {
            String pathToFile = ConsoleDialog.getUserAnswerString("Input file name");
            manager.createFile(new File(pathToFile));
            ConsoleDialog.println("File " + pathToFile + " created");
        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    private static void dialogEditTextFile() {
        try {
            String pathToFile = ConsoleDialog.getUserAnswerString("Input file name");
            File file = new File(pathToFile);
            boolean notOwerride = true;

            if (file.exists()) {
                ConsoleDialog.println("File " + file.getCanonicalPath() + " exist");
                notOwerride = !ConsoleDialog.askUser("Owerride file");
            }

            manager.editFile(file, System.in, notOwerride);
        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    private static void dialogDeleteTextFile() {
        try {
            String pathToFile = ConsoleDialog.getUserAnswerString("Input file name");
            manager.deleteFile(new File(pathToFile));
            ConsoleDialog.println("File " + pathToFile + " deleted");
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
                        dialogViewFileSystem();
                        break;
                    case 2:
                        dialogCreateTextFile();
                        break;
                    case 3:
                        dialogEditTextFile();
                        break;
                    case 4:
                        dialogDeleteTextFile();
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
