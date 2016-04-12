package com.epam.task1;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * Created by Yuriy Krishtop on 10.04.2016.
 */
public class ParsersTest {

    String[] nameFile = {"src/main/resources/test.xml", " "};

    @Test
    public void testSAXParser() throws IOException, SAXException, ParserConfigurationException {
        System.out.println("SAX parser:");
        SAXParser.main(nameFile);
    }

    @Test
    public void testDOMParser() throws IOException, SAXException, ParserConfigurationException {
        System.out.println("DOM parser:");
        DOMParser.main(nameFile);
    }

    @Test
    public void testStAXParser() throws IOException, SAXException, ParserConfigurationException, XMLStreamException {
        System.out.println("StAX parser:");
        StAXParser.main(nameFile);
    }
}
