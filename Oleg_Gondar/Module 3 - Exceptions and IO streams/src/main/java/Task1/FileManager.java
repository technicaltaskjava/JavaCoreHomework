package Task1;

import java.io.IOException;
import java.nio.file.InvalidPathException;

/**
 * Created by Oleg on 14.03.2016.
 */
public class FileManager {

    public static void main(String[] args) {

        try {
            DirectoryViewer.showAllSubdirectories(System.getProperty("user.dir"));
          //  DirectoryViewer.showOnlyFiles(WorkWithConsoleInput.enterPath());
          //  DirectoryViewer.showOnlySelectedDirectory(WorkWithConsoleInput.enterPath());
        } catch (NullPointerException e){
            System.out.println("Directory not found");
        }

        try {
            FileManipulator fileManipulator = new FileManipulator("test.txt");


        try {
            fileManipulator.writeFile("testString1\n");
        } catch (IOException e) {
            System.out.println("File not writed");
        }
        try {
            fileManipulator.appendFile("testString2");
        } catch (IOException e) {
            System.out.println("File not appended");

        }
        try {
            System.out.println(fileManipulator.showFile());
        } catch (IOException e) {
            System.out.println("File not found");
        }
            try {
                fileManipulator.deleteFile();
            }catch (Exception e) {
                System.out.println("File not found");
            }

        } catch ( InvalidPathException e){
            System.out.println("Path not finded "+ e.getMessage());
        }


    }


}
