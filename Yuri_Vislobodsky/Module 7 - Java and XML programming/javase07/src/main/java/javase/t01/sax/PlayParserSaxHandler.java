package javase.t01.sax;

import javase.t01.play.Play;
import javase.t01.play.Speech;
import javase.t01.play.TagNames;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Handler Class for Play Parser (method SAX)
 * Created by Yury Vislobodsky on 06.04.2016.
 */
public class PlayParserSaxHandler extends DefaultHandler {
    private Play play;
    private Speech speech;
    private StringBuilder text;

    public PlayParserSaxHandler() {
        play = new Play();
    }

    public Play getPlay() {
        return play;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();
        if ("SPEECH".equals(qName)) {
            speech = new Speech();
        }
    }

    @Override
    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        TagNames tagName = TagNames.getElementTagName(qName.toUpperCase());
        switch (tagName) {
            case SPEAKER:
                speech.addSpeaker(text.toString());
                break;
            case LINE:
                speech.addLine(text.toString());
                break;
            case SPEECH:
                play.add(speech);
                speech = null;
                break;
            default:
        }
    }

    @Override
    public void warning(SAXParseException exception) {
        System.err.printf("WARNING: line %d: %s%n", exception.getLineNumber(), exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) {
        System.err.printf("ERROR: line %d: %s%n", exception.getLineNumber(), exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        System.err.printf("FATAL: line %d: %s%n", exception.getLineNumber(), exception.getMessage());
        throw exception;
    }
}
