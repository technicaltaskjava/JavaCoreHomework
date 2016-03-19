package Task3;

import Task1.FileManipulator;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oleg on 15.03.2016.
 */
public class MyByteFileReaderTest {

    @Test

    public void test() throws Exception {

        MyByteFileReader.main(null);
        String s = new Scanner(new File("test.txt")).useDelimiter("\\Z").next();

        assertEquals("for find 1 times", s);
    }

}
