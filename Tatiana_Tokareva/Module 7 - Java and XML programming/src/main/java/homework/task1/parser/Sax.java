package homework.task1.parser;

import homework.Constant;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.*;

public class Sax extends DefaultHandler {
    private  String key;
    private  String value;
    private  List<String> tempPhrase;
    private final Map<String, ArrayList<String>> persons = new HashMap<>();
    private boolean speech;

    public Map<String, ArrayList<String>> parse() {
        String url = Constant.URL_XML_FILE;
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            saxParser.parse(url, this);

            return persons;

        } catch (SAXException | ParserConfigurationException | IOException e) {
            System.out.println(e);
        }
        return Collections.emptyMap();
    }


    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase(Constant.SPEECH)) {
            speech = true;
            tempPhrase = new ArrayList<>();
        }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {

        if (qName.equalsIgnoreCase(Constant.SPEECH)) {
            speech = false;
            final StringBuilder builder = new StringBuilder();

            for (String element : tempPhrase) {

                builder.append(element).append(" ");
            }
            final String value1 = builder.toString();

            if (persons.containsKey(key)) {
                final ArrayList<String> oldValue = persons.get(key);
                oldValue.add(value1);
                persons.put(key, oldValue);
            } else {
                final ArrayList<String> newValue = new ArrayList<>();
                newValue.add(value1);
                persons.put(key, newValue);
            }
        }
        if (qName.equalsIgnoreCase(Constant.SPEAKER) && speech) {
            key = value;
        }
        if (qName.equalsIgnoreCase(Constant.LINE) && speech) {
            tempPhrase.add(value);
        }

    }

    @Override
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        value = new String(ch, start, length);
    }
}



