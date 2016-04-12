package com.epam;

import com.epam.first.PlayList;
import com.epam.first.Speaker;
import com.epam.first.dom.ParsingByDom;
import com.epam.first.sax.ParsingBySax;
import com.epam.first.stax.ParsingByStax;
import org.junit.Test;

import java.util.Random;

import static junit.framework.Assert.assertEquals;

public class ParseTest {
    @Test
    public void listTest() {

        PlayList domSpeakerList = ParsingByDom.parseRun("src/main/resources/com_err.xml");
        PlayList saxSpeakerList = ParsingBySax.parseRun("src/main/resources/com_err.xml");
        PlayList staxSpeakerList = ParsingByStax.parseRun("src/main/resources/com_err.xml");
        Speaker[] domSortedList = domSpeakerList.sortSpeaker();
        Speaker[] saxSortedList = domSpeakerList.sortSpeaker();
        Speaker[] staxSortedList = domSpeakerList.sortSpeaker();

        assertEquals("ADRIANA", domSortedList[0].getName());
        assertEquals(79, domSortedList[0].getNumberOfSpeech());
        assertEquals("ADRIANA", saxSortedList[0].getName());
        assertEquals(79, saxSortedList[0].getNumberOfSpeech());
        assertEquals("ADRIANA", staxSortedList[0].getName());
        assertEquals(79, staxSortedList[0].getNumberOfSpeech());
        Random random = new Random();
        int randomSpeaker = random.nextInt(domSortedList.length);
        assertEquals(saxSortedList[randomSpeaker].getName(), domSortedList[randomSpeaker].getName());
        assertEquals(saxSortedList[randomSpeaker].getName(), staxSortedList[randomSpeaker].getName());
        assertEquals(saxSortedList[randomSpeaker].getNumberOfSpeech(), domSortedList[randomSpeaker].getNumberOfSpeech());
        assertEquals(saxSortedList[randomSpeaker].getNumberOfSpeech(), staxSortedList[randomSpeaker].getNumberOfSpeech());
    }
}
