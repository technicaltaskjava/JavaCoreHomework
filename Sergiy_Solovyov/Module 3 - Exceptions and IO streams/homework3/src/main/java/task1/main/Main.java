package task1.main;

import task1.filemanager.FileManager;
import task1.fileview.FileViewer;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 09.03.2016
 */
public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        fileManager.run();
    }
}
