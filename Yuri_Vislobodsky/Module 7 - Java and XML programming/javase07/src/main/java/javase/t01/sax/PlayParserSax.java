package javase.t01.sax;

import javase.t01.exception.PlayParserException;
import javase.t01.play.Play;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;

/**
 * Play Parser Class (method SAX)
 * Created by Yury Vislobodsky on 06.04.2016.
 */
public class PlayParserSax {
    private PlayParserSax() {}

    public static Play parsing(String xmlFileName) throws PlayParserException {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            PlayParserSaxHandler handler = new PlayParserSaxHandler();
            reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            reader.setContentHandler(handler);
            reader.parse(new InputSource(xmlFileName));
            return handler.getPlay();
        } catch (IOException | SAXException e) {
            throw new PlayParserException(e);
        }
    }
}