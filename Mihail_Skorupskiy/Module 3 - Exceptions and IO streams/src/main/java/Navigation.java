import textfiles.CodeAnalyzer;
import textfiles.IOMethods;
import textfiles.PropertiesAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Navigation {
    private static File currentPath = new File("");
    private static File currentFile = new File("");

    //Call this method in main() to launch the navigator.
    public static void navigateFileSystem(){
        int input;
        do {
            input = -1;
            if(currentPath.getPath().equals("")){
                System.out.println("Current location: root");
            } else {
                System.out.println("Current location: " + currentPath.getPath());
            }
            System.out.println("What would you like to do:");
            System.out.println("1 - Return to previous folder.");
            System.out.println("2 - Go to folder.");
            System.out.println("3 - Read a file.");
            System.out.println("4 - Add to a file.");
            System.out.println("5 - Rewrite a file.");
            System.out.println("6 - Analyze java code from a file.");
            System.out.println("7 - Get values from a .properties file.");
            System.out.println("0 - Return to previous menu.");
            try {
                input = IOMethods.intInput();
            } catch (IOException e){
                System.out.println(e.toString());
            }
            switch(input){
                case 0:
                    break;
                case 1:
                    goToParentDirectory();
                    break;
                case 2:
                    goToDirectory();
                    break;
                case 3:
                    IOMethods.stringArrayPrint(readFile());
                    break;
                case 4:
                    addToFile();
                    break;
                case 5:
                    rewriteFile();
                    break;
                case 6:
                    analyzeJavaFile();
                    break;
                case 7:
                    analyzePropertiesFile();
                    IOMethods.stringArrayPrint(PropertiesAnalyzer.getValues());
                    break;
                default:
                    System.out.println("Incorrect menu item, try again.");
            }
        } while (input != 0);
    }

    private static void goToParentDirectory(){
        if (currentPath.getParent() != null) {
            currentPath = new File(currentPath.getParent());
        } else if (currentPath.getPath().endsWith(":\\") || currentPath.getPath().endsWith(":")){
            currentPath = new File("");
        } else{
            System.out.println("Can't return to previous folder, already at root.");
        }
    }

    private static void goToDirectory(){
        System.out.println("Specify a directory:");
        try {
            String input = IOMethods.validatedInput();
            File temp = new File(currentPath.getPath() + '/' + input);
            if (temp.exists() && temp.isDirectory()){
                currentPath = temp;
            } else{
                System.out.println("No such directory.");
            }
        } catch (IOException e){
           System.out.println(e.toString());
        }
    }

    private static String[] readFile(){
        String[] output = null;
        try {
            System.out.println("Specify a file:");
            String input = IOMethods.validatedInput();
            try {
                checkFile(currentPath.getPath() + '/' + input);
            } catch(FileNotFoundException e){
                System.out.println(e.toString());
            }
            output = IOMethods.readStringsFromFile(currentFile.getPath());
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return output;
    }

    private static void addToFile(){
        System.out.println("Specify a file:");
        String input = null;
        try{
            input = IOMethods.validatedInput();
        } catch (IOException e){
            System.out.println(e.toString());
        }
        try {
            checkFile(currentPath.getPath() + '/' + input);
            System.out.println("Enter your text:");
            String[] buffer = new String[1];
            buffer[0] = IOMethods.validatedInput();
            IOMethods.writeStringsToFile(buffer, currentFile.getPath());
        } catch(Exception e){
        System.out.println(e.toString());
        }
    }

    private static void rewriteFile(){
        System.out.println("Specify a file:");
        String input = null;
        try{
            input = IOMethods.validatedInput();
        } catch (IOException e){
            System.out.println(e.toString());
        }
        try {
            checkFile(currentPath.getPath() + '/' + input);
            System.out.println("Enter your text:");
            String[] buffer = new String[1];
            buffer[0] = IOMethods.validatedInput();
            IOMethods.rewriteStringsToFile(buffer, currentFile.getPath());
        } catch(Exception e){
            System.out.println(e.toString());
        }
    }

    private static void analyzeJavaFile(){
        System.out.println("Specify a file:");
        String input = null;
        try {
            input = IOMethods.validatedInput();
        } catch (IOException e){
            System.out.println(e.toString());
        }
        try {
            checkFile(currentPath.getPath() + '/' + input);
        } catch (FileNotFoundException e){
            System.out.println(e.toString());
        }
        try {
            IOMethods.stringArrayPrint(CodeAnalyzer.analyzeByteArray(IOMethods.readBytesFromFile(currentFile.getPath())));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void analyzePropertiesFile(){
        System.out.println("Specify a file:");
        String input = null;
        try {
            input = IOMethods.validatedInput();
        } catch (IOException e){
            System.out.println(e.toString());
        }
        try {
            checkFile(currentPath.getPath() + '/' + input);
        } catch (FileNotFoundException e){
            System.out.println(e.toString());
        }
        try {
            PropertiesAnalyzer.fill(currentFile.getPath());
        } catch(Exception e){
            System.out.println(e.toString());
        }
    }

    private static void checkFile(String fileName) throws FileNotFoundException{
        currentFile = new File("");
        File temp = new File(fileName);
        if (temp.exists() && temp.isFile()){
            currentFile = temp;
        } else {
            System.out.println("No such file.");
            throw new FileNotFoundException();
        }
    }
}

