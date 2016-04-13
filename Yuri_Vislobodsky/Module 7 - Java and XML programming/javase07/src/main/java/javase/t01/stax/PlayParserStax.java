package javase.t01.stax;

import javase.t01.exception.PlayParserException;
import javase.t01.play.Play;
import javase.t01.play.Speech;
import javase.t01.play.TagNames;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Play Parser Class (method StAX)
 * Created by Yury Vislobodsky on 06.04.2016.
 */
public class PlayParserStax {
    private static Play play;
    private static Speech speech;
    private static TagNames elementName;
    private static boolean insideLine;

    private PlayParserStax() {}

    public static Play parsing(String xmlFileName) throws PlayParserException {
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream input = new FileInputStream(xmlFileName);
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            return process(reader);
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new PlayParserException(e);
        }
    }

    private static Play process(XMLStreamReader reader) throws XMLStreamException {
        play = new Play();
        speech = null;
        elementName = TagNames.OTHER;
        insideLine = false;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    caseStartElement(reader.getLocalName());
                    break;
                case XMLStreamConstants.CHARACTERS:
                    caseCharacters(reader.getText().trim());
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    caseEndElement(reader.getLocalName());
                    break;
                default:
            }
        }
        return play;
    }

    private static void caseStartElement(String tagName) {
        elementName = TagNames.getElementTagName(tagName);
        switch (elementName) {
            case SPEECH:
                speech = new Speech();
                break;
            case LINE:
                insideLine = true;
                break;
            default:
        }
    }

    private static void caseCharacters(String text) {
        if (text.isEmpty() || speech == null) {
            return;
        }
        switch (elementName) {
            case SPEAKER:
                speech.addSpeaker(text);
                break;
            case LINE:
                speech.addLine(text);
                break;
            case STAGEDIR:
                if (insideLine) {
                    speech.addLine(text);
                }
                break;
            default:
        }
    }

    private static void caseEndElement(String tagName) {
        elementName = TagNames.getElementTagName(tagName);
        switch (elementName) {
            case SPEECH:
                play.add(speech);
                speech = null;
                break;
            case LINE:
                insideLine = false;
                break;
            default:
        }
    }
}
