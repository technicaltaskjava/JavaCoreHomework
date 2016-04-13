package com.epam.task1;

import org.junit.Test;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * Created by Yuriy Krishtop on 10.04.2016.
 */
public class ParsersTest {
    private static final Logger log = Logger.getLogger(ParsersTest.class);

    String[] nameFile = {"src/main/resources/test.xml", " "};

    @Test
    public void testSAXParser() {
        System.out.println("SAX parser:");
        try {
            SAXParser.main(nameFile);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testDOMParser() {
        System.out.println("DOM parser:");
        try {
            DOMParser.main(nameFile);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testStAXParser() {
        System.out.println("StAX parser:");
        try {
            StAXParser.main(nameFile);
        } catch (XMLStreamException | IOException e) {
            log.error(e.getMessage());
        }
    }
}
