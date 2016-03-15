package com.epam.task2;

import com.epam.task2.exception.KeyNotFoundException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Olga Kramska on 14-Mar-16.
 */
public class PropertiesReaderTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testReadProperty() throws KeyNotFoundException {
        String fileName = "config.properties";
        PropertiesReader.load(fileName);
        Assert.assertEquals("OlgaK", PropertiesReader.getProperty("user"));
    }

    @Test(expected = KeyNotFoundException.class)
    public void testNotFoundProperty() throws KeyNotFoundException {
        String fileName = "config.properties";
        PropertiesReader.load(fileName);
        PropertiesReader.getProperty("xxxxx");
        thrown.expect(KeyNotFoundException.class);
    }
}
