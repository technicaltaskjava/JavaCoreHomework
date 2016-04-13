package com.epam.task1;

import com.epam.task1.dom.MyJavaDOMParser;
import com.epam.task1.dom.MySunDOMParser;
import com.epam.task1.otherclasses.Speech;
import com.epam.task1.sax.MySAXParser;
import com.epam.task1.stax.MyStAXParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by O.Gondar on 12.04.2016.
 *
 *  <b>Note:</b> You need Internet connection for successfully perform tests!
 *
 */
public class TestsForParsers {

    public static final int SPEAKER_NUMBER_FOR_TEST = 0;
    public static final int SPEAKER_WORDS_COUNT_TEST_RESULT = 11;
    public static final String TEST_PIECE_URL = "http://www.ibiblio.org/xml/examples/shakespeare/all_well.xml";
    public static final String SPEAKER_NAME_TEST_RESULT = "COUNTESS";
    public static final String SPEAKER_SPEECH_TEST_RESULT = "[In delivering my son from me, I bury a second husband.]";

    List<Speech> speechList;

    @Test
    public void testSunDOMParser() {
        speechList = MySunDOMParser.performParse(TEST_PIECE_URL);
        testParserOnSampleSpeaker(speechList);
    }

    @Test
    public void testJavaDOMParser() {
        speechList = MyJavaDOMParser.performParse(TEST_PIECE_URL);
        testParserOnSampleSpeaker(speechList);
    }

    @Test
    public void testSAXParser() {
        speechList = MySAXParser.performParse(TEST_PIECE_URL);
        testParserOnSampleSpeaker(speechList);
    }

    @Test
    public void testStAXParser() {
        try {
            speechList = MyStAXParser.performParse(TEST_PIECE_URL);
            testParserOnSampleSpeaker(speechList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testParserOnSampleSpeaker(List<Speech> speechList) {
        Assert.assertEquals(SPEAKER_NAME_TEST_RESULT, speechList.get(SPEAKER_NUMBER_FOR_TEST).getSpeaker());
        Assert.assertEquals(SPEAKER_SPEECH_TEST_RESULT, speechList.get(SPEAKER_NUMBER_FOR_TEST).getSpeech().toString());
        Assert.assertTrue(SPEAKER_WORDS_COUNT_TEST_RESULT == speechList.get(SPEAKER_NUMBER_FOR_TEST).getWordsInSpeech());
    }
}






