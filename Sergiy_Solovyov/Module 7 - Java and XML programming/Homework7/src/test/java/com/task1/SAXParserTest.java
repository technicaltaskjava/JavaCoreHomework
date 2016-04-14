package com.task1;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import sax.handlers.SaxHandler;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class SAXParserTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SAXParserTest.class);
    @Test
    public void test() throws   SAXException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        SaxHandler handler = new SaxHandler();
        reader.setContentHandler(handler);
        try {
            reader.parse(new InputSource("src/main/resources/test.xml"));
        } catch (IOException e) {
            LOGGER.info(e.getClass().toString(), e);
        }
        assertEquals(handler.toString(), Constant.RESULT);
    }
}
