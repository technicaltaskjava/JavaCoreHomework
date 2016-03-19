package task3and4;

import task2.Task2;
import task3and4.myExeption.NotCreatedFilsTXT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main
    {
        public static void main(String[] args)
            {
                Task4 task4 = new Task4();
                Task3  task3 = new Task3();
                File file = new File("src/Main/java/task3and4/Test.java");
                File newFileRW = new File("src/Main/java/task3and4/FileRW.txt");
                File newFileIO = new File("src/Main/java/task3and4/FileIO.txt");
                task4.assayFile(file, newFileRW);
                try
                    {
                        task3.assayFile(file,newFileIO);
                    }
                catch (IOException e)
                    {
                        e.printStackTrace();
                    }

            }

    }
