package task2;

import org.junit.Test;
import task2.exceptions.KeyNotFoundException;
import task2.propreader.PropertiesReader;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 13.03.2016
 */
public class PropertiesReaderTest {

    private PropertiesReader pr = new PropertiesReader();

    @Test
    public void testGetReadProperties(){

        HashMap<String, String> props = pr.readProperties("test.properties");
        assertEquals("Maps don't equal", PropMap.props, props);

    }
    @Test
    public void testGetValue() throws KeyNotFoundException {

        String keyAndValue = pr.getValue(PropMap.props, "message.username");
        assertEquals("String isn't correct", keyAndValue, "message.username = Por favor ingrese el nombre de usuario");

    }
    @Test (expected = KeyNotFoundException.class)
    public void testGetValueException() throws KeyNotFoundException {

        pr.getValue(PropMap.props, "message");

    }


}
