package task2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import java.io.File;

import static org.junit.Assert.*;

public class Task2Test
    {

        @Test
        public void testGetProperties() throws Exception
            {
                File file = new File("src/Main/java/task2/config.properties");
                Task2 task2 = new Task2();
                String resoult = task2.getProperties(file);
                String testResoult = "HOST: http://localhost:8888/mydb\r\n" +
                        "LOGIN: root\r\n" +
                        "PASSWORD: dbroot";
                Assert.assertEquals(resoult, testResoult );
            }

        @Test
        public void testShowInfoProperties() throws Exception
            {   File file = new File("src/Main/java/task2/config.properties");
                Task2 task2 = new Task2();
                String resoult = task2.showInfoProperties(file);
                String testResoult = " http://localhost:8888/mydb "+ "root "+ "dbroot";
                Assert.assertEquals(resoult, testResoult );




            }
    }