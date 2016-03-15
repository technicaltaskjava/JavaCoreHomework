import textfiles.IOMethods;

import java.io.IOException;

public class Main {
    public static void main(String[] args){

        System.out.println("Tasks 1-4:");
        Navigation.navigateFileSystem();

        System.out.println("Task 5:");
        System.out.println("Didn't have the time to implement a menu for it, so just input any symbol to see how it works");
        try {
            String input = IOMethods.validatedInput();
        } catch (IOException e) {
            System.out.println("Whoops." + e.toString());
        }
        Movies.initFromFile();
        Movies.show();
        try {
            Movies.writeToFile();
            System.out.println("Serialization complete, now deserializing.");
            Movies.readFromFile();
        } catch (IOException e){
            e.printStackTrace();
        }
        Movies.show();
    }
}
