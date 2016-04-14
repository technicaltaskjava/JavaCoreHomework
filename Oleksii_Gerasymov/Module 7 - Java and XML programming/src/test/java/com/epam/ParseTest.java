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
        String play = "src/main/resources/com_err.xml";
        PlayList domSpeakerList = ParsingByDom.parseRun(play);
        PlayList saxSpeakerList = ParsingBySax.parseRun(play);
        PlayList staxSpeakerList = ParsingByStax.parseRun(play);
        Speaker[] domSortedList = domSpeakerList.sortSpeaker();
        Speaker[] saxSortedList = saxSpeakerList.sortSpeaker();
        Speaker[] staxSortedList = staxSpeakerList.sortSpeaker();

        String firstSpeaker = "ADRIANA";
        assertEquals(firstSpeaker, domSortedList[0].getName());
        assertEquals(79, domSortedList[0].getNumberOfSpeech());
        assertEquals(firstSpeaker, saxSortedList[0].getName());
        assertEquals(79, saxSortedList[0].getNumberOfSpeech());
        assertEquals(firstSpeaker, staxSortedList[0].getName());
        assertEquals(79, staxSortedList[0].getNumberOfSpeech());
        Random random = new Random();
        int randomSpeaker = random.nextInt(domSortedList.length);
        assertEquals(saxSortedList[randomSpeaker].getName(), domSortedList[randomSpeaker].getName());
        assertEquals(saxSortedList[randomSpeaker].getName(), staxSortedList[randomSpeaker].getName());
        assertEquals(saxSortedList[randomSpeaker].getNumberOfSpeech(), domSortedList[randomSpeaker].getNumberOfSpeech());
        assertEquals(saxSortedList[randomSpeaker].getNumberOfSpeech(), staxSortedList[randomSpeaker].getNumberOfSpeech());
    }
}
