package com.epam.task1.stax;

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
        Speech speech = null;
        StringBuilder cueBuilder = null;
        String text = "";
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    if (("SPEECH").equals(reader.getLocalName())) {
                        speech = new Speech();
                        cueBuilder = new StringBuilder();
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    text = reader.getText().trim();
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    switch (reader.getLocalName()) {
                        case "SPEAKER":
                            if (speech != null) {
                                speech.setSpeaker(text);
                            }
                            break;
                        case "LINE":
                            if (cueBuilder != null) {
                                cueBuilder.append(text).append('\n');
                            }
                            break;
                        case "SPEECH":
                            if (speech != null) {
                                speech.setCue(cueBuilder.toString());
                            }
                            speechList.add(speech);
                        default:
                            break;
                    }
                default:
                    break;
            }
        }
        return speechList;
    }
}
