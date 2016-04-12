package com.epam.task1;

import com.epam.task1.dom.DOMPlayParser;
import com.epam.task1.model.Speaker;
import com.epam.task1.model.Speech;
import com.epam.task1.util.PlayAnalyser;
import com.epam.task1.sax.SAXPlayParser;
import com.epam.task1.stax.StAXPlayStreamReader;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Olga Kramska on 12-Apr-16.
 */
public class ParserTest {
    private InputStream in;
    private Map<String, Speaker> expectedSpeakerMap;

    @Before
    public void init() {
        in = Thread.currentThread().getContextClassLoader().getResourceAsStream("hamlet_part.xml");
        expectedSpeakerMap = new HashMap<>();
        expectedSpeakerMap.put("FRANCISCO", new Speaker(6, 42));
        expectedSpeakerMap.put("BERNARDO", new Speaker(6, 40));
    }

    @Test
    public void testSAXParser() throws IOException, SAXException, ParserConfigurationException {
        List<Speech> speeches = SAXPlayParser.getSpeechesFromXML(in);
        PlayAnalyser playAnalyser = new PlayAnalyser(speeches);
        assertEquals(expectedSpeakerMap, playAnalyser.getSpeakerMap());
    }

    @Test
    public void testDOMParser() throws IOException, SAXException, ParserConfigurationException {
        List<Speech> speeches = DOMPlayParser.getSpeechesFromXML(in);
        PlayAnalyser playAnalyser = new PlayAnalyser(speeches);
        assertEquals(expectedSpeakerMap, playAnalyser.getSpeakerMap());
    }

    @Test
    public void testStAXParser() throws IOException, SAXException, ParserConfigurationException, XMLStreamException {
        List<Speech> speeches = StAXPlayStreamReader.getSpeechesFromXML(in);
        PlayAnalyser playAnalyser = new PlayAnalyser(speeches);
        assertEquals(expectedSpeakerMap, playAnalyser.getSpeakerMap());
    }
}
