package sax.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import entity.Actor;
import constants.Constant;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Matcher;

/**
 * @author Sergey Solovyov
 */
public class SaxHandler extends DefaultHandler {
    boolean bSpeech = false;
    boolean bSpeaker = false;
    boolean bLine = false;
    private String name;
    private StringBuilder sb = new StringBuilder();
    private HashMap<String, Actor> map = new HashMap<>();

    @Override
    public void startElement(String uri,
                             String localName, String qName, Attributes attributes)
            throws SAXException {

        switch (qName) {
            case Constant.SPEECH:
                bSpeech = true;
                break;
            case Constant.SPEAKER:
                bSpeaker = true;
                break;
            case Constant.LINE:
                bLine = true;
                break;
            default:break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        switch (qName) {
            case Constant.SPEECH:
                putToMap();
                name = null;
                sb.setLength(0);
                bSpeech = false;
                break;
            case Constant.SPEAKER:
                bSpeaker = false;
                break;
            case Constant.LINE:
                bLine = false;
                break;
            default:break;
        }
    }

    @Override
    public void characters(char []ch,
                           int start, int length) throws SAXException {
        if (bSpeaker) {
            name = new String(ch, start, length);
            bSpeaker = false;
        } else if (bLine) {
            sb.append(ch, start, length);
            sb.append("\n");
        }
    }
    private int getQuantityWords(){
        Matcher matcher = Constant.PATTERN.matcher(sb);
        int wordsQty = 0;
        while (matcher.find()) {
            wordsQty++;
        }
        return wordsQty;
    }
    private void putToMap(){
        if (!map.containsKey(name)){
            map.put(name, new Actor(name, getQuantityWords()));
        }
        else {
            Actor actor = map.get(name);
            actor.setWordsQuy(getQuantityWords());
            map.put(name, actor);
        }
    }
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Parsing started.");
    }
    @Override
    public void endDocument() throws SAXException {
        System.out.println("Parsing ended.");
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Collection<Actor> collection = map.values();
        for (Actor a: collection){
            builder.append(a);
        }
        return  builder.toString();
    }
}

