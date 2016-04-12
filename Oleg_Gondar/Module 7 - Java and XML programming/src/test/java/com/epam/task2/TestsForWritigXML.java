package com.epam.task2;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Oleg on 11.04.2016.
 */
public class TestsForWritigXML {

    public static final String FILE_NAME_FOR_TESTS = "pom1.xml";

    @Test
    public void testValidationXML() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME_FOR_TESTS);
            fileWriter.write("test");
            fileWriter.close();
            Assert.assertFalse(ValidatePOM.isValid(FILE_NAME_FOR_TESTS));
            Assert.assertTrue(ValidatePOM.isValid("pom.xml"));

        } catch (IOException e) {
            e.printStackTrace();
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
