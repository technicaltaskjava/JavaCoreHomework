package com.kokhanyuk.javase03.actions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * MyDosOperationTest
 *
 * Class MyDosOperation testing
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MyDosOperationTest {

    @Test
    public void testMakeFile() throws Exception {
        String fn = "test.tmp";
        MyDosOperation testDO = new MyDosOperation();
        testDO.makeFile(fn);
        File f = new File(fn);
        if (f.exists()) {
            f.delete();
        } else {
            throw new IOException();
        }

    }

}