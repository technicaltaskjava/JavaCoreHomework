package com.epam.task1;

import com.epam.task1.dom.DOMPlayParser;
import com.epam.task1.model.Speaker;
import com.epam.task1.model.Speech;
import com.epam.task1.sax.SAXPlayParser;
import com.epam.task1.stax.StAXPlayStreamReader;
import com.epam.task1.util.PlayAnalyser;
import org.junit.Before;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
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
        in = getClass().getResourceAsStream("/hamlet_part.xml");
        expectedSpeakerMap = new HashMap<>();
        expectedSpeakerMap.put("FRANCISCO", new Speaker("FRANCISCO", 6, 42));
        expectedSpeakerMap.put("BERNARDO", new Speaker("BERNARDO", 6, 40));
    }

    @Test
    public void testSAXParser() {
        List<Speech> speeches = SAXPlayParser.getSpeechesFromXML(in);
        assertEquals(expectedSpeakerMap, PlayAnalyser.analyse(speeches));
    }

    @Test
    public void testDOMParser() {
        List<Speech> speeches = DOMPlayParser.getSpeechesFromXML(in);
        assertEquals(expectedSpeakerMap, PlayAnalyser.analyse(speeches));
    }

    @Test
    public void testStAXParser() throws XMLStreamException {
        List<Speech> speeches = StAXPlayStreamReader.getSpeechesFromXML(in);
        assertEquals(expectedSpeakerMap, PlayAnalyser.analyse(speeches));
    }
}
