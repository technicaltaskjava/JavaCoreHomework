package main.java.com.epam.t01;

import java.io.File;

public class WorkWithFolder {
    public static String currentFolder = System.getProperty("user.dir");

    public static void printOutCurrentDirectory () {
        File currentDir = new File(currentFolder);
        String[] currentDirList = currentDir.list();
        for(int folderIndex = 0; folderIndex < currentDirList.length; folderIndex++)
        {
            File currentFile = new File(currentFolder + File.separator + currentDirList[folderIndex]);
            if(currentFile.isFile()) {
                System.out.println(currentDirList[folderIndex]);
            }
            else {
                System.out.println("[ DIR ] " + currentDirList[folderIndex]);
            }
        }
    }

    public static boolean changeDirectory (String newDirectory) {
        File currentDir = new File(newDirectory);
        if ((currentDir.exists()) && (currentDir.isDirectory())) {
            currentFolder = newDirectory;
            return true;
        }
        else {
            return false;
        }
    }


}
