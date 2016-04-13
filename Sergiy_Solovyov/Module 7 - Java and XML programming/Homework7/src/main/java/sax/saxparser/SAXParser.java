package sax.saxparser;


import exceptions.MySAXException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import sax.handlers.SaxHandler;

import java.io.IOException;
/**
 * @author Sergey Solovyov
 */
public class SAXParser {

    public void parse(String path) throws MySAXException {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SaxHandler handler = new SaxHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(path));
            System.out.println(handler.toString());
        } catch (IOException|SAXException e) {
            throw new MySAXException(e.getMessage());
        }

    }
}
