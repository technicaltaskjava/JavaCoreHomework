package homework.task1.parser;

import homework.Constant;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class Stax {

    private String key;
    private String value;
    private List<String> tempPhrase;
    private final Map<String, ArrayList<String>> persons = new HashMap<>();
    private boolean speech;

    public Map<String, ArrayList<String>> parse() {

        try {
            URL url = new URL(Constant.URL_XML_FILE);
            InputStream in = url.openStream();
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(in);
            while (reader.hasNext()) {
                final int next = reader.next();
                switch (next) {
                    case XMLStreamConstants.START_ELEMENT:
                        statElement(reader);
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        endElement(reader);
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        value = reader.getText().trim();
                        break;

                    default:
                        break;
                }
            }
            return persons;


        } catch (XMLStreamException | IOException e)//NOSONAR
        {
            e.getStackTrace();
        }
        return Collections.emptyMap();
    }

    private void endElement(XMLStreamReader reader) {
        if (Constant.SPEECH.equalsIgnoreCase(reader.getLocalName())) {
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
        if (Constant.SPEAKER.equalsIgnoreCase(reader.getLocalName()) && speech) {
            key = value;
        }
        if (Constant.LINE.equalsIgnoreCase(reader.getLocalName()) && speech) {
            tempPhrase.add(value);
        }
    }

    private void statElement(XMLStreamReader reader) {
        if (Constant.SPEECH.equalsIgnoreCase(reader.getLocalName())) {
            speech = true;
            tempPhrase = new ArrayList<>();
        }
    }
}
