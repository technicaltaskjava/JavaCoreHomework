import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import sax.handlers.SaxHandler;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class SAXParserTest {

    @Test
    public void test() throws  IOException, SAXException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        SaxHandler handler = new SaxHandler();
        reader.setContentHandler(handler);
        reader.parse(new InputSource("src/main/resources/test.xml"));
        assertEquals(handler.toString(), Constant.RESULT);
    }
}
