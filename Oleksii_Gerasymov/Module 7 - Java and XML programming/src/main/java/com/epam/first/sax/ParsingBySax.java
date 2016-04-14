package com.epam.first.sax;

import com.epam.first.PlayList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.logging.Logger;

public class ParsingBySax {
    private static Logger classLog = Logger.getLogger("SAX_log");

    private ParsingBySax() {
    }

    public static PlayList parseRun(String play) {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            PlaySaxHandler handler = new PlaySaxHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(play));
            return handler.getSpeakerList();
        }
        catch (SAXException | IOException parsingException) {
            classLog.info(String.valueOf(parsingException));
        }
        return null;
    }
}
