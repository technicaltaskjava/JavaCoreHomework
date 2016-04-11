package handlers;

import elements.Speech;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class DOMHandler {
    private ArrayList<Speech> fileContent = new ArrayList<>();

    public void process(Document document){
        Element root = document.getDocumentElement();
        NodeList speeches = root.getElementsByTagName("SPEECH");
        for (int i = 0; i < speeches.getLength(); i++){
            Element speech = (Element) speeches.item(i);
            fileContent.add(getSpeech(speech));
        }
    }

    private Speech getSpeech(Element element){
        Speech speech = new Speech();
        NodeList lines = element.getElementsByTagName("LINE");
        NodeList speaker = element.getElementsByTagName("SPEAKER");
        speech.setSpeaker(speaker.item(0).getTextContent().trim());
        for (int i = 0; i < lines.getLength(); i++){
            speech.setLine(lines.item(i).getTextContent().trim());
        }
        return speech;
    }

    public List<Speech> getFileContent(){
        return fileContent;
    }
}
