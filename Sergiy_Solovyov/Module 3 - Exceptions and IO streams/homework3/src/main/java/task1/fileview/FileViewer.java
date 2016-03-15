package task1.fileview;

import task1.exceptions.ExitException;
import task1.exceptions.FileUpdateException;
import task1.exceptions.InvalidDriveInputException;
import console.Console;
import messages.Message;

import javax.swing.filechooser.FileSystemView;
import java.io.*;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 09.03.2016
 */
public class FileViewer {

    private Console console = new Console();
    private Message m = new Message();

    public File[] showDrives(){

        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] paths = File.listRoots();
        m.message("Next drives found on your computer: ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paths.length; i++)
        {
            sb.append("Description: ");
            sb.append(fsv.getSystemTypeDescription(paths[i]));
            sb.append("; Drive: ");
            sb.append(paths[i]);
            sb.append(" - ");
            sb.append(i);
            sb.append("\n");
        }
        System.out.println(sb.toString());
        return paths;
    }

    public String drivesMenu(File[] paths){
        String drivePath = null;
        int menu = -1;
        while ( menu == -1 )
            try {
                m.message("You are in root directory, press number in front of the drive to see its contains");
                menu = menuDrives(paths);
            } catch (InvalidDriveInputException e) {
                m.warn("Enter numbers from menu");
            } catch (ExitException e) {
                System.out.println("Bye-bye");
                System.exit(0);
            }

        if (menu >= 0)
            drivePath =  paths[menu].getAbsolutePath();

        return drivePath;
    }

    public String showDirectory(String path){
        if (path == null){
            showDrives();
            return null;}
        File dir = new File(path);
        if (path.endsWith(".txt")) {
            System.out.println(readTXT(path));;
            m.message("You may made notation in this file: write end press enter or input \"back\"");
            try {
                writeTXT(path, console.stringInput());
            } catch (FileUpdateException e) {
                m.warn(e.getMessage());
            }

            return dir.getParent();}

        File [] files = dir.listFiles();
        if (files == null) {
            m.message("You may made notation in this file: write end press enter or input \"back\"");
            return dir.getParent();}

        System.out.println("\n");

        StringBuilder sb = new StringBuilder();
        int []arr = new int[files.length];
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                sb.append("Directory: ");
                sb.append(files[i]);
                sb.append(" - ");
                if (files[i].isHidden()){
                    sb.append("hidden folder");
                sb.append("\n");
                    arr [i] = Integer.MAX_VALUE;
                continue;}
                else sb.append(i);
                sb.append("\n");
                arr [i] = i;
            }}
        for (int i = 0; i < files.length; i++) {

            if (files[i].isFile()){
                sb.append("File:      ");
                sb.append(files[i]);
                sb.append(" - ");
                if (!files[i].getName().endsWith("txt"))
                    sb.append("not available to see");
                else  sb.append(i);
                sb.append("\n");
                arr [i] = i;}

        }
        System.out.println(sb.toString());

        String drivePath = null;
        int folder = menuInput(files, arr);

         if (folder == -1000)

             drivePath =  dir.getParent();

        else if (folder == -2000){

             drivePath = path;
             m.info("Please re-enter number from menu");
         }
        else if (folder == -3000){
             drivePath = path;
             m.message("Enter name of the file you want to create");
             createTXT(path, console.stringInput());
         }
        else if (folder == -4000){
             drivePath = path;
             m.message("Enter name of the folder you want to create");
             createDir(path, console.stringInput());
         }
        else if (folder == -5000){
             fileDelete(files[0].getAbsolutePath());
             drivePath = path;
         }
       else if (folder < 0 && folder > -999){
            fileDelete(files[Math.abs(folder)].getAbsolutePath());
            drivePath = path;
        }
        else drivePath = files[folder].getAbsolutePath();


        return drivePath;
    }
    private int menuInput(File[] paths, int[]arr){

        int menu = 0;
        String str = console.stringInput().trim();


        do {
            try {

                switch (str.toLowerCase()) {
                    case "back":
                        return -1000;
                    case "dir":
                        return -4000;
                    case "txt":
                        return -3000;
                    case "-0":
                        return -5000;
                    case "exit":
                        throw new ExitException("exit");
                }

                menu = Integer.parseInt(str) ;
                int menu2 = Math.abs(menu);

                if (menu2 < 0 || menu2 > paths.length-1){
                    m.info("Please, enter the number in front of the folder or the file");
                    menu = menuInput(paths, arr);}
            } catch (ExitException e) {
                m.message("Bye-bye");
                System.exit(0);
            } catch (Exception e) {
                m.warn("Please, enter only numbers in front of the folder or the file");
                menu = menuInput(paths, arr);
            }
        } while (str.isEmpty());


        return menu;
    }
    private  int menuDrives(File[] paths) throws InvalidDriveInputException, ExitException {

        int menu = 0;
        String str = console.stringInput();

        do {
            try {
                if (str.equalsIgnoreCase("back")) return -1;
                if (str.equalsIgnoreCase("exit")) throw new ExitException("exit");

                menu = Integer.parseInt(str.trim());

                if (menu < 0 || menu > paths.length-1){
                    m.info("Please, enter the number in front of the drive");
                    menu = menuDrives(paths);
                }

            } catch (NumberFormatException e) {

                throw new InvalidDriveInputException("Number is invalid");
            }

        } while (str.isEmpty());


        return menu;
    }
    private   boolean isInMenu(int[] arr, int number) {

        for (int i = 0; i < arr.length;i++) {
            if (arr[i]==number)return true;
        }
        m.warn("Please, enter only numbers in front of the folder or the file");
        return false;
    }

    private File createTXT(String path, String fileName){

        String absoluteFilePath = path +File.separator+ fileName+".txt";

        File file = new File(absoluteFilePath);

            try{
                if(file.createNewFile()){
                m.info(file.getName() + " created!");
                m.info("Path to file: " + file.getAbsolutePath());
            }else{
                m.warn("Creation operation is failed: "+file.getName()+" .Try to use unique name for file");
            }
            }catch(IOException e){
                m.warn("You have no permission to create files in this folder");
            }
        return file;
    }

    private boolean fileDelete(String path){

        File file = new File(path);
            if(file.delete()){
                m.info(file.getName() + " is deleted!");
                return true;
            }else{
                m.warn("Delete operation failed.");
            }
        return false;
    }
    private File createDir(String path, String dirName){
        File dir = null;
        String absoluteFilePath = path + File.separator + dirName;
        try{
            dir = new File(absoluteFilePath);
            if(dir.mkdir()){
                m.info(dir.getAbsolutePath() + " - created!");

            }else{
                m.warn("Creation operation is failed.");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return dir;
    }
    private String readTXT(String path){

        File file = new File(path);
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file));) {

            m.info("Reading file: " + file.getAbsolutePath());
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                sb.append(currentLine);
                sb.append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
        }
    private void writeTXT(String path, String note) throws  FileUpdateException {

        File file = new File(path);

        if (note.equalsIgnoreCase("back"))return;

            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));){

                out.println(note);

            } catch (IOException ex) {

                throw new FileUpdateException("Error during writing to file: " + file.getAbsolutePath());
            }

        m.info("Existing file: " + file.getName() + " updated");
    }
    }

