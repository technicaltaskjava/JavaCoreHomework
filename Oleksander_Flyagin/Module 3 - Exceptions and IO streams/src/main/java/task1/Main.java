package task1;

import com.sun.javafx.tk.Toolkit;
import task1.Task1;

import java.io.File;

public class Main

    {
        public static final String PAKAGE_NAME = ".";
        public static final String File_NAME = "src/Main/java/task3and4/test.txt";


        public static void main(String[] args)
            {
                File dir = new File(PAKAGE_NAME);

                Task1 task1 = new Task1();
                task1.testExistsFile(File_NAME);
                //task1.deletFile("test");

                System.out.println("-------------------------------------");
                task1.testExistsFile(File_NAME);
                task1.createFile(File_NAME);
                task1.testExistsFile(File_NAME);
                //task1.writeToTxt("test");
                System.out.println("-------------------------------------");
                task1.readTxt(File_NAME);
               // task1.showTreeDirect(PAKAGE_NAME);


            }
    }
