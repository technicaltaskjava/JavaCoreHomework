
import org.junit.Test;

import java.awt.Desktop;
import java.io.*;
import java.util.Scanner;


public class Task1 {
    public static void main(String[] args) {

        Desktop desktop = null;

        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        try {
            desktop.open(new File(String.valueOf(fileDirection())));
        } catch (IOException ioe) {
            System.err.println("Error 404!");
        } catch (IllegalArgumentException e) {
            System.err.println("Illegal file name");


        }
        workTree();
    }

    public static String fileDirection (){
        System.out.println("Input file directory:");
       String filedirection = scanner();
        return filedirection;
    }

    public  static String fileName(){
        System.out.println("Please input name for file: ");
        String fname = scanner();
        return fname;
    }
   @Test
    public static String scanner(){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        return s1;
    }



    public static void workTree (){
        System.out.println("What should i do next?(create/delete/edit file):");

        String s2 = scanner();

        if(s2.equals("create")){

            File newFile = new File(fileDirection() + fileName()+ ".txt");
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Invalid filename");
            }
            System.out.println("File successfully created!");

        } else if (s2.equals("delete")){

            File newFile = new File(fileDirection() + fileName()+ ".txt");

                newFile.delete();

            System.out.println("File successfully deleted!");


        }else if (s2.equals("edit")){
            try {
                 PrintStream printStream = new PrintStream(new FileOutputStream(fileDirection() + fileName() + ".txt", true), true);
                System.out.println("Input what you want to add in file:");
                 printStream.println(scanner());
                 printStream.close();
                System.out.println("Information successfully saved to file!");
            }catch (FileNotFoundException e){
                System.out.println("Wrong filename!");
            }

        }else{
            System.out.println("You did wrong things");
        }



    }

}


