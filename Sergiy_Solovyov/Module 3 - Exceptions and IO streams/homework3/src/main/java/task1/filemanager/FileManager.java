package task1.filemanager;

import task1.fileview.FileViewer;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 09.03.2016
 */
public class FileManager {

    FileViewer fileViewer;

    public FileManager(){
        this.fileViewer = new FileViewer();
    }
    public void  run(){

        System.out.println( "Thank you for buying our program \n");
        System.out.println( "Now you can to work with your file system!\n");
        System.out.println(" \"back\" to return \n \"txt\" to create .txt file\n \"dir\" " +
                          "to create directory\n  press number" +
                          " in front of folder/.txt file to see it\n" +
                          "  to delete file/folder press number with minus for example \"-3\"\n" +
                        " \"exit\" to exit the program\n");

        String drive = fileViewer.drivesMenu(fileViewer.showDrives());

        while (true){

          drive = fileViewer.showDirectory(drive);

            if (drive == null)

                drive = fileViewer.drivesMenu(fileViewer.showDrives());
                }





    }
}
