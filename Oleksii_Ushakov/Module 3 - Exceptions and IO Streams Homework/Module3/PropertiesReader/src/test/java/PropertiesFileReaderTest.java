import org.junit.Before;
import org.junit.Test;
import propertiesfilereader.PropertiesFileReader;
import propertiesfilereader.exceptions.PropertiesFileException;
import propertiesfilereader.exceptions.PropertiesFileNoKeyException;
import propertiesfilereader.exceptions.PropertiesFileNotFoundException;

import static org.junit.Assert.assertTrue;

public class PropertiesFileReaderTest {
    private PropertiesFileReader reader;

    @Before
    public void onStart() {
        reader = new PropertiesFileReader();
    }

    @Test
    public void testRead() throws Exception {
        reader.read("./src/test/java/testFiles/config.properties");
    }

    @Test(expected = PropertiesFileNotFoundException.class)
    public void testCanReadInReadMethod() throws Exception {
        reader.read("someWrongWay");
    }

    @Test(expected = PropertiesFileException.class)
    public void testCanReadInGetProperty() throws Exception {
        reader.getProperty("key1");
    }

    @Test
    public void testGetProperty() throws Exception {
        reader.read("./src/test/java/testFiles/config.properties");
        for (int i = 1; i < 10; i++) {
            assertTrue(("value" + i).equals(reader.getProperty("key" + i)));
        }
    }

    @Test(expected = PropertiesFileNoKeyException.class)
    public void testNoSuchProperty() throws Exception {
        reader.read("./src/test/java/testFiles/config.properties");
        reader.getProperty("someFailProperty");

    }
}