package com.epam.task2;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Oleg on 11.04.2016.
 */
public class TestsForWritigXML {

    public static final String FILE_NAME_FOR_TESTS = "pom1.xml";
    private static final Logger logger = Logger.getLogger(TestsForWritigXML.class);

    @Test
    public void testValidationXML() throws IOException {

        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME_FOR_TESTS);
            fileWriter.write("test");
            fileWriter.close();
            Assert.assertFalse(ValidatePOM.isValid(FILE_NAME_FOR_TESTS));
            Assert.assertTrue(ValidatePOM.isValid("pom.xml"));
        } catch (IOException e) {
            logger.error(e);
            throw new IOException();
        }
    }

    @Test
    public void testWritingWithDOM() {
        WritingWithDOM.performWrite(FILE_NAME_FOR_TESTS);
        Assert.assertTrue(ValidatePOM.isValid(FILE_NAME_FOR_TESTS));
    }

    @Test
    public void testWritingWithStAX() {
        WritingWithStAX.performWrite(FILE_NAME_FOR_TESTS);
        Assert.assertTrue(ValidatePOM.isValid(FILE_NAME_FOR_TESTS));
    }
}
