package com.epam.task01filesystem;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Viewer {
    private String dirName;
    private File dir;
    private String foldersList = "";
    private String filesList = "";
    private String[] folders;
    private String[] files;

    public Viewer() {
        this("C://");
    }

    public Viewer(String dirName) {
        this.dirName = dirName;
        dir = new File(dirName);
        currentDir();
    }

    public String getFoldersList() {
        return foldersList;
    }

    public String getFilesList() {
        return filesList;
    }

    public void createFileTxt() {
        System.out.println("Enter name of txt-file without filename extension: ");
        Scanner in = new Scanner(System.in);
        String fileTxtName = in.nextLine()+".txt";
        File newFile = new File(dirName + "/" + fileTxtName);
        boolean created = false;
        try {
            created = newFile.createNewFile();
            if(created) {
                System.out.println("File created.");
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            Viewer newViewer = new Viewer(dirName);
        }

    }

    public void deleteFile() {
        System.out.println("Please, enter integer number of file, which you want to delete: ");
        Scanner in = new Scanner(System.in);
        int fileId = in.nextInt();
        try {
            String nameFile = dirName +"/"+ files[fileId-folders.length-2];
            in.close();
            new File(nameFile).delete();
            System.out.println("File deleted.");
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            Viewer newViewer = new Viewer(dirName);
        }
    }

    public void addTextToFile() {
        System.out.println("Please, enter integer number of file for writing text: ");
        Scanner in = new Scanner(System.in);
        int fileId = in.nextInt();
        String nameFile;
        try {
            nameFile = dirName + "/" + files[fileId - folders.length - 2];
            System.out.println("Please, enter information for writing into the file: ");
            Scanner inScan = new Scanner(System.in);
            String info = inScan.nextLine();
            FileOutputStream myFile = new FileOutputStream(nameFile);
            Writer out = new BufferedWriter(new OutputStreamWriter(myFile, "UTF8"));
            out.write(info);
            out.close();
            System.out.println("New information added to file "+files[fileId-folders.length-2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void currentDir() {
        int id = 0;
        if(dir.isDirectory()) {
            for(File item : dir.listFiles()){
                if(item.isDirectory()){
                    foldersList += item.getName()+",";
                }
                else{
                    filesList += item.getName()+",";
                }
            }
        }
        folders = foldersList.split(",");
        files = filesList.split(",");
        System.out.println("Current directory "+ dirName +" contains "+folders.length + " folders and " + files.length + " files:");
        System.out.printf(++id + ". " + "%-60s", "parrent folder");
        System.out.printf("  \tfolder\n");
        for(String fldr : folders) {
            System.out.printf(++id + ". " + "%-60s", fldr);
            System.out.printf("  \tfolder\n");
        }
        for(String fl : files) {
            System.out.printf(++id + ". " + "%-60s", fl);
            System.out.printf("  \tfile\n");
        }
        System.out.println("Please, enter integer number in range 1 - "+(folders.length+files.length+1 )+" for browsing ");
        System.out.println("                                      0 - "+" for exit");
        System.out.println("                                     -1 - "+" create txt-file");
        System.out.println("                                     -2 - "+" delete file");
        System.out.println("                                     -3 - "+" add text to file\n");
        Scanner input = new Scanner(System.in);
        if(input.hasNextInt()) {
            int idFold = input.nextInt();
            if(idFold == -3){
                addTextToFile();
            } else if(idFold == -2){
                deleteFile();
            } else if(idFold == -1) {
                createFileTxt();
            } else if(idFold == 0) {
                System.exit(0);
            } else if(idFold == 1) {
                String[]  tmpFoldNms = dirName.split("/");
                String parrentDirName = dirName.substring(0, dirName.length()-(tmpFoldNms[tmpFoldNms.length-1].length()+1));
                if(parrentDirName.length() < 3) {
                    parrentDirName = dirName;
                }
                Viewer newViewer = new Viewer(parrentDirName);
            } else {
                try {
                    Viewer newViewer = new Viewer(dirName+"/" + folders[idFold - 2]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(e.toString());
                    Viewer newViewer = new Viewer(dirName);
                }
            }
        } else {
            Viewer newViewer = new Viewer(dirName);
        }

    }

}

