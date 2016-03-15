package com.kokhanyuk.javase03.actions;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static java.io.File.separator;

/**
 * FileIOTest
 *
 * Class FileIO testing
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class FileIOTest {

    @Test
    public void testReadSymbFile() throws Exception {
        Assert.assertNotNull(FileIO.readSymbFile("readme.txt"));
    }

    @Test
    public void testReadByteFile() throws Exception {
        Assert.assertNotNull(FileIO.readByteFile("readme.txt"));
    }

    @Test
    public void testWriteByteFile() throws Exception {
        String fileName = "iodata" + separator + "tmp.txt";
        FileIO.writeByteFile(fileName, "123");
        Assert.assertNotNull(FileIO.readSymbFile(fileName));
        File f = new File(fileName);
        if (f.exists()) {
            f.delete();
        } else {
            throw new IOException();
        }
    }

    @Test
    public void testWriteSymbFile() throws Exception {
        String fileName = "iodata" + separator + "tmp.txt";
        FileIO.writeSymbFile(fileName, "123", false);
        Assert.assertNotNull(FileIO.readSymbFile(fileName));
        File f = new File(fileName);
        if (f.exists()) {
            f.delete();
        } else {
            throw new IOException();
        }
    }
}