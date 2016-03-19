package com.epam.task1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Olga Kramska on 14-Mar-16.
 */

public class FileManagerTest {

    @Test
    public void testFileManager() {
        String rootDir = System.getProperty("user.home");
        IFileManager fileManager = new FileManager(rootDir);
        String testFile = "test.txt";
        assertEquals(true, fileManager.makeFile(testFile));
        assertEquals(true, fileManager.deleteFile(testFile));
    }
}
