package handlers;

import elements.Speech;
import elements.TagNames;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StAXHandler {

    private ArrayList<Speech> fileContent = new ArrayList<>();
    private Speech current;
    private TagNames currentTag;
    private XMLStreamReader reader;

    private StAXHandler(){}

    public StAXHandler(XMLStreamReader reader){
        this.reader = reader;
    }

    public void process() throws XMLStreamException{
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    startHandler();
                    break;
                case XMLStreamConstants.CHARACTERS:
                    charactersHandler();
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    endHandler();
                    break;
                default:
            }
        }
    }

    private void startHandler(){
        currentTag = TagNames.valueOf(reader.getLocalName().toUpperCase());
        if ("SPEECH".equals(currentTag.name())){
            current = new Speech();
        }
    }

    private void charactersHandler(){
        String text = reader.getText().trim();
        switch (currentTag){
            case SPEAKER:
                current.setSpeaker(text);
                break;
            case LINE:
                current.setLine(text);
                break;
            default:
        }

    }

    private void endHandler(){
        currentTag = TagNames.valueOf(reader.getLocalName().toUpperCase());
        if ("SPEECH".equals(currentTag.name())){
            fileContent.add(current);
        }
    }

    public List<Speech> getFileContent(){
        return fileContent;
    }

}
