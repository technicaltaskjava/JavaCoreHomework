package com.epam.task1;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Yuriy Krishtop on 10.04.2016.
 */
public class SAXParser {

    private SAXParser() {
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        String fileName = (args.length == 0) ? "http://www.ibiblio.org/xml/examples/shakespeare/rich_ii.xml" : args[0];
        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser parser = factory.newSAXParser();
        SpeakerSAXHandler handler = new SpeakerSAXHandler();
        parser.parse(fileName, handler);
        factory.setValidating(true);
        Map<String, Speaker> speakers = handler.getSpeakerHashMap();
        for (Speaker s : speakers.values()) {
            System.out.println(s);
        }
    }
}
