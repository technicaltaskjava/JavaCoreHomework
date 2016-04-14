package com.epam.task1.stax;

import com.epam.task1.type.TagName;
import com.epam.task1.model.Speech;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Kramska on 12-Apr-16.
 */
public class StAXPlayStreamReader {
    private StAXPlayStreamReader() {
    }

    public static List<Speech> getSpeechesFromXML(InputStream inputStream) throws XMLStreamException {
        List<Speech> speechList = new ArrayList<>();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(inputStream);
        Speech speech = new Speech();
        StringBuilder cueBuilder = new StringBuilder();
        String text = "";
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamConstants.CHARACTERS) {
                text = reader.getText().trim();
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                if (TagName.SPEAKER.name().equals(reader.getLocalName())) {
                    speech.setSpeaker(text);
                } else if (TagName.LINE.name().equals(reader.getLocalName())) {
                    cueBuilder.append(text).append('\n');
                } else if (TagName.SPEECH.name().equals(reader.getLocalName())) {
                    speech.setCue(cueBuilder.toString());
                    speechList.add(speech);
                    speech = new Speech();
                    cueBuilder = new StringBuilder();
                }
            }
        }
        return speechList;
    }
}
