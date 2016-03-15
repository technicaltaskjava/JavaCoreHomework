package com.kokhanyuk.javase03.actions;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * FindKeyWordTest
 *
 * Class FindKeyWord testing
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class FindKeyWordTest {

    @Test
    public void testFoundKeyWord() throws Exception {
        String where = "one, two,three (two.un ptwo{, twoy two( utwo";
        String key = "two";
        FindKeyWord f = new FindKeyWord();
        assertSame(f.foundKeyWord(where, key), 3);
    }
}