package Task2;

import Task1.FileManipulator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Oleg on 15.03.2016.
 */
public class UniversalPropertiesReaderTest {

    @Test

    public void test() throws Exception{

        UniversalPropertiesReader universalPropertiesReader = new UniversalPropertiesReader();
        universalPropertiesReader.createProperties("test.txt");
        universalPropertiesReader.setProperty("1","test");
        universalPropertiesReader.createProperties("test.txt");

        assertEquals("test", universalPropertiesReader.getPropertyValue("1"));



    }

}
