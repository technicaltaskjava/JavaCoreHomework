package logic;

import elements.Speaker;
import elements.Speech;
import handlers.DOMHandler;
import handlers.StAXHandler;
import handlers.SAXHandler;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLAnalyzer {

    private String address = "src/main/resources/";
    private Logger logger;
    private List<Speech> content;
    private HashSet<Speaker> speakers;

    public XMLAnalyzer(String fileName){
        address = address + fileName;
        logger = Logger.getLogger("XMLAnalyzer");
        content = null;
        speakers = new HashSet<>();
    }

    public void startSAX() throws SAXException{
        XMLReader reader = XMLReaderFactory.createXMLReader();
        SAXHandler handler = new SAXHandler();
        reader.setContentHandler(handler);
        try {
            System.out.println("\nParsing using SAX.");
            reader.parse(new InputSource(address));
        } catch (IOException e){
            logger.log(Level.WARNING, "Read error.", e);
        }
        content = handler.getFileContent();
        fillSpeakers();
    }

    public void startStAX() throws XMLStreamException{
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream input = null;
        try {
            input = new FileInputStream(address);
        } catch (FileNotFoundException e){
            logger.log(Level.WARNING, "File not found", e);
        }
        if (input != null) {
            System.out.println("\nParsing using StAX.");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input); //NOSONAR
            StAXHandler handler = new StAXHandler(reader);
            handler.process();
            content = handler.getFileContent();
            fillSpeakers();
        }
    }

    public void startDOM() throws SAXException, ParserConfigurationException{ //NOSONAR
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = null;
        try {
            document = db.parse(new File(address));
        } catch (IOException e){
            logger.log(Level.WARNING, "Read error", e);
        }
        if (document != null){
            System.out.println("\nParsing using DOM.");
            DOMHandler handler = new DOMHandler();
            handler.process(document);
            content = handler.getFileContent();
            fillSpeakers();
        }
    }

    private void fillSpeakers(){
        for (Speech speech : content) {
            Speaker current = new Speaker();
            current.setName(speech.getSpeaker());
            if (!speakers.contains(current)){
                speakers.add(collectStats(current));
            }
        }
    }

    private Speaker collectStats(Speaker current){
        for (Speech speech : content) {
            if (current.getName().equals(speech.getSpeaker())){
                current.setLines(current.getLines()+1);
                current.setWords(current.getWords() + wordCounter(speech.getLine()));
            }
        }
        return current;
    }

    private int wordCounter(String input){
        return input.length() - input.replace(" ", "").length() + 1;
    }

    public void showResults(){
        if (content != null) {
            System.out.println("Results:");
            for (Speaker speaker : speakers) {
                System.out.println(speaker);
            }
        } else System.out.println("Run the analyzer first.");
    }
}
