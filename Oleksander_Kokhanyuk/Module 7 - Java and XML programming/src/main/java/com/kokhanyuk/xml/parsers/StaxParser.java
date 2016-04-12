package com.kokhanyuk.xml.parsers;

import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * StaxParser
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class StaxParser {
    private Map<String, Speaker> speakers = new HashMap();
    private String text;
    private String name;
    private boolean insideSpeech;
    static Logger log = Logger.getLogger(StaxParser.class);

    public void staxParsing() {

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            InputStream input = new URL("http://www.ibiblio.org/xml/examples/shakespeare/hamlet.xml").openStream();
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            process(reader);
        } catch (XMLStreamException | IOException e) {
            log.warn("Error parsing. ", e);
        }
        for (Map.Entry<String, Speaker> speak : speakers.entrySet()) {
            String message = speak.getKey() + ": replica: " + speak.getValue().getCounterReplica() + " average number of words: "
                    + speak.getValue().getAverageWords();
            log.info(message);
        }
    }

    private void process(XMLStreamReader reader) throws XMLStreamException {
        String speech = "SPEECH";
        while (reader.hasNext()) {
            int type = reader.next();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    if (reader.getLocalName().equals(speech)) {
                        insideSpeech = true;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    text = reader.getText();
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (insideSpeech) {
                        TagName tagName = TagName.valueOf(reader.getLocalName());
                        setDataSpeaker(tagName);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void setDataSpeaker(TagName tagName) {
        switch (tagName) {
            case SPEECH:
                speakers.get(name).setCounterReplica();
                insideSpeech = false;
                break;
            case SPEAKER:
                if (!speakers.containsKey(text)) {
                    speakers.put(text, new Speaker());
                }
                name = text;
                break;
            case LINE:
                String[] word = text.split(" ");
                speakers.get(name).setCounterWord(word.length);
                break;
            default:
                break;
        }
    }
}
