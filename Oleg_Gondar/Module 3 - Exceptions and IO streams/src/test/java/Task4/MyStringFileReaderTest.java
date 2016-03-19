package Task4;

import Task3.MyByteFileReader;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oleg on 15.03.2016.
 */
public class MyStringFileReaderTest {

    @Test

    public void test() throws Exception {

        MyStringFileReader.main(null);
        String s = new Scanner(new File("test.txt")).useDelimiter("\\Z").next();

        assertEquals("for find 1 times", s);
    }

}
