package task1;

import com.sun.javafx.tk.Toolkit;
import org.junit.Assert;

import java.io.File;

import static org.junit.Assert.*;

public class Task1Test
    {

        @org.junit.Before
        public void setUp() throws Exception
            {

            }

        @org.junit.Test
        public void testCreateFile() throws Exception
            {
                Task1 task1 = new Task1();
                task1.createFile("test");
                Assert.assertTrue(task1.testExistsFile(new File("test.txt")));

            }

        @org.junit.Test
        public void testDeletFile() throws Exception
            {
                Task1 task1 = new Task1();
                task1.deletFile("test");
                Assert.assertTrue(!task1.testExistsFile(new File("test.txt")));

            }

        @org.junit.Test
        public void testTestExistsFile() throws Exception
            {
                Task1 task1 = new Task1();
                File file = new File("test.txt");
                task1.createFile(file);
                Assert.assertTrue(task1.testExistsFile(file));

            }
    }