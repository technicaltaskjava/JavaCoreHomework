package com.epam.task1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Olga Kramska on 19-Mar-16.
 */
public class TestSentence {

    @Test
    public void testReplaceWords() {
        Sentence sentence = new Sentence("First, an-other : another, last");
        sentence.replaceFirstWord2Last();
        assertEquals("last an-other another First", sentence.toString());
    }
}
