package com.epam.task1.sax;

import com.epam.task1.otherclasses.Speech;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


/**
 * Created by Oleg on 09.04.2016.
 */
public class MySAXParser {

    private static final String LOAD_EXTERNAL_DTD_URL = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
    private static final Logger logger = Logger.getLogger(MySAXParser.class);

    private MySAXParser() {
    }

    public static List<Speech> performParse(String url) {

        XMLReader reader = null;
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setFeature(LOAD_EXTERNAL_DTD_URL, false);
            MySAXHandler handler = new MySAXHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(url));
            return handler.getSpeechList();
        } catch (SAXException | IOException e) {
            org.apache.log4j.BasicConfigurator.configure();
            logger.error(e);
        }
        return Collections.emptyList();
    }
}
