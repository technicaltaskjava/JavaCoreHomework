package com.kokhanyuk.javase04.crazylogger;

import com.kokhanyuk.javase03.actions.FileIO;
import org.junit.Test;

import java.io.File;

import static java.io.File.separator;
import static org.junit.Assert.*;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class CrazyLoggerMenuTest {

    @Test
    public void testNotesSearcher() throws Exception {
        CrazyLogger log = new CrazyLogger();
        CrazyLoggerMenu test = new CrazyLoggerMenu();
        FileIO fio = new FileIO();
        log.info("Hello");
        log.info("World!");
        log.err("Error", "Exceptions");
        test.notesSearcher("ERROR", true);
        String text = fio.readFile("Data" + separator + "LoggerSearchResult.txt", "UTF-8");
        int i = text.length();
        assertEquals(i, 27);
    }
}