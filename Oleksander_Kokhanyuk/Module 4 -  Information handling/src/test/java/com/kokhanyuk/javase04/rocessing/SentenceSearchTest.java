package com.kokhanyuk.javase04.rocessing;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class SentenceSearchTest {


    @Test
    public void testLineSelection() throws Exception {
        SentenceSearch test = new SentenceSearch();
        String text = "Р; Привет, ; строка;";
        int[] i = test.lineSelection(text, ";");
        assertEquals(i.length, 3);
    }
}