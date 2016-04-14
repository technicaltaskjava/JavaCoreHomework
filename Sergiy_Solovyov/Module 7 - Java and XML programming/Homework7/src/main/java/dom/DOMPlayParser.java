package dom;

import entity.Actor;
import exceptions.MyDOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import constants.Constant;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Matcher;

/**
 * @author Sergey Solovyov
 */
public class DOMPlayParser {

    private  HashMap<String, Actor> map = new HashMap<>();

    public void parse(final String path) throws MyDOMException{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File(path));
            Element root = document.getDocumentElement();
            process(root);
        } catch (ParserConfigurationException|SAXException|IOException e) {//NOSONAR
            throw new MyDOMException(e.getMessage(), e);
        }

    }

    private void process(final Element root){
        NodeList nodes = root.getElementsByTagName(Constant.SPEECH);
        for (int i = 0; i < nodes.getLength(); i++) {
            Element actorElement = (Element) nodes.item(i);
            Actor actor = getActor(actorElement);
            putToMap(actor);
        }
    }
    private  Actor getActor(final Element element) {
        Actor actor = new Actor();
        actor.setName(getSingleChild(element, Constant.SPEAKER).getTextContent().trim());
        NodeList nodes = element.getElementsByTagName(Constant.LINE);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodes.getLength(); i++) {
            Element element1 = (Element) nodes.item(i);
            sb.append(element1.getTextContent());
            sb.append("\n");
        }
        actor.setWordsQuy(getQuantityWords(sb));
        return actor;
    }

    private  int getQuantityWords(final StringBuilder sb){
        Matcher matcher = Constant.PATTERN.matcher(sb);
        int wordsQty = 0;
        while (matcher.find()) {
            wordsQty++;
        }
        return wordsQty;
    }
    private  Element getSingleChild(final Element element, final String childName) {
        NodeList nList = element.getElementsByTagName(childName);
        return (Element) nList.item(0);
    }
    private  void putToMap(Actor actor){
        String name = actor.getName();
        if (!map.containsKey(name)){
            map.put(name, actor);
        }
        else {
            Actor act = map.get(name);
            act.setWordsQuy(actor.getWordsQuy());
            map.put(name, act);
        }
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
