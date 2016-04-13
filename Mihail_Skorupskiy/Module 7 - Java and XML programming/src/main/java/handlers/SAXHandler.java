package handlers;

import elements.Speech;
import elements.TagNames;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXHandler extends DefaultHandler {

    private StringBuilder tagContent;
    private Speech buffer;
    private ArrayList<Speech> fileContent = new ArrayList<>();

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Parsing...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tagContent = new StringBuilder();
        if ("Speech".equalsIgnoreCase(qName)){
            buffer = new Speech();
        }
    }

    @Override
    public void characters(char[] buffer, int start, int length) {
        tagContent.append(buffer, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        TagNames name = TagNames.valueOf(qName.toUpperCase());
        switch (name){
            case SPEAKER:
                buffer.setSpeaker(tagContent.toString());
                break;
            case LINE:
                buffer.setLine(tagContent.toString());
                break;
            case SPEECH:
                fileContent.add(buffer);
                break;
            default:
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Done!");
    }

    public List<Speech> getFileContent(){
        return fileContent;
    }
}
