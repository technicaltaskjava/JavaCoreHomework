package Task1;

import Task5.FilmCollection;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Oleg on 15.03.2016.
 */
public class FileManipulatorTest {

    @Test

    public void test() throws Exception{

        FileManipulator fileManipulator = new FileManipulator("test.txt");
        fileManipulator.writeFile("test");

        assertEquals("test", fileManipulator.showFile());



    }

}
