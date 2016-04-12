package stax;

import constants.Constant;
import entity.Actor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  @author Sergey Solovyov
 */
public class StAXParser {

    private String name;
    private StringBuilder sb = new StringBuilder();
    private HashMap<String, Actor> map = new HashMap<>();
    private Pattern pattern = Pattern.compile("\\w+");
    private static final Logger LOGGER = LoggerFactory.getLogger(StAXParser.class);

    public void parse(final String path) throws XMLStreamException {

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream input = null;
        try {
            input = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            LOGGER.info(e.getClass().toString(), e);
        }
        XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
        process(reader);
    }

    private void process(final XMLStreamReader reader) throws XMLStreamException {
        String elementName = "";
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = reader.getLocalName();
                    break;

                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;}
                    characters(text, elementName);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    endElement(reader.getLocalName());
                    break;
                default:break;
            }
        }
    }

    private void characters(final String text, final String elementName) {
        if (Constant.SPEAKER.equals(elementName)) {
            name = text;
        }
        if (Constant.LINE.equals(elementName)) {
            sb.append(text);
            sb.append("\n");
        }
        if (Constant.STAGE_DIR.equals(elementName)) {
            sb.append(text);
            sb.append("\n");
        }
    }
    private void endElement(final String elementEnd) {
        if (Constant.SPEECH.equals(elementEnd)) {
            putToMap();
            sb.setLength(0);
            name = null;
        }
    }

    private void putToMap() {
        if (!map.containsKey(name)) {
            map.put(name, new Actor(name, getQuantityWords()));
        } else {
            Actor actor = map.get(name);
            actor.setWordsQuy(getQuantityWords());
            map.put(name, actor);
        }
    }

    private int getQuantityWords() {
        Matcher matcher = pattern.matcher(sb);
        int wordsQty = 0;
        while (matcher.find()) {
            wordsQty++;
        }
        return wordsQty;
    }
        @Override
        public String toString () {
            StringBuilder builder = new StringBuilder();
            Collection<Actor> collection = map.values();
            for (Actor a : collection) {
                builder.append(a);
            }
            return builder.toString();
        }
    }

