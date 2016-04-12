package com.kokhanyuk.xml.parsers;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Map;

/**
 * SaxParser
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class SaxParser {
   static Logger log = Logger.getLogger(SaxParser.class);

    public void saxParsing() {
        Map<String, Speaker> rezult;
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SaxHandler handler = new SaxHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource("hamlet.xml"));
            rezult = handler.getRezult();
            for (Map.Entry<String, Speaker> speak : rezult.entrySet()) {
                String message = speak.getKey() + ": replica: " + speak.getValue().getCounterReplica() + " average number of words: "
                        + speak.getValue().getAverageWords();
                log.info(message);
            }
        } catch (SAXException | IOException e) {
            log.warn("Error parsing: ", e);
        }
    }
}
