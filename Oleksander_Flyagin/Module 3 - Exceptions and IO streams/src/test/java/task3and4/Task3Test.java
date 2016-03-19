package task3and4;

import org.junit.*;
import task3and4.myExeption.NotCreatedFilsTXT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

public class Task3Test
    {

        @org.junit.Test
              (expected = IOException.class )
        public void testAssayFile() throws IOException
            {
                        Task3 task3 = new Task3();
                        File file = new File("src/Main/java/FileIO.txt");
                        File newFileIO = new File("src/Main/java/task3and4/FileIOTest.txt");
                        task3.assayFile(file,  newFileIO);




            }
    }