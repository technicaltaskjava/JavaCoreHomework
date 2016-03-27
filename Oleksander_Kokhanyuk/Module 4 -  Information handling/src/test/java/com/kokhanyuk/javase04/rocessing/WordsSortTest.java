package com.kokhanyuk.javase04.rocessing;

import com.kokhanyuk.javase03.actions.FileIO;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static java.io.File.separator;
import static org.junit.Assert.*;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class WordsSortTest {

    @Test
    public void testCalculateVovel() throws Exception {
        WordsSort test = new WordsSort();
        FileIO fio = new FileIO();
        String text = fio.readFile("Data" + separator + "test.txt", "UTF-8");
        int i = (int) test.calculateVovel(text);
        System.out.println(test.calculateVovel(text));
        assertEquals(i, 5);
    }

}