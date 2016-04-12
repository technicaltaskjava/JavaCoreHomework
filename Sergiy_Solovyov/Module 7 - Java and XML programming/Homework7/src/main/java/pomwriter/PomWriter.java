package pomwriter;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import constants.Constant;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 *  @author Sergey Solovyov
 */
public class PomWriter {

    private Document doc;

    public PomWriter() throws ParserConfigurationException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        doc = docBuilder.newDocument();
    }

    public  void writePomXML(String path) throws TransformerException {

        Element rootElement = doc.createElement(Constant.PROJECT);
        doc.appendChild(rootElement);

        setAttr(rootElement,Constant.XMLNS, Constant.XMLNS_VALUE );
        setAttr(rootElement,Constant.XMLNS_XSI, Constant.XMLNS_XSI_VALUE );
        setAttr(rootElement,Constant.XMLNS_SCHEMA_LOCATION, Constant.XMLNS_SCHEMA_LOCATION_VALUE);

        appChild(rootElement, Constant.MODEL_VERSION, "4.0.0");
        appChild(rootElement, Constant.GROUP_ID, "Homework7");
        appChild(rootElement, Constant.ARTIFACT_ID, "Homework7");
        appChild(rootElement, Constant.VERSION, "1.0-SNAPSHOT");

        Element element5 = createElement(rootElement, Constant.PROPERTIES);
        appChild(element5, Constant.SOURCE_ENCODING, "UTF-8");

        Element element = createElement(rootElement, Constant.DEPENDENCIES);
        Element dep = createElement(element, Constant.DEPENDENCY);

        appChild(dep, Constant.GROUP_ID, "junit");
        appChild(dep, Constant.ARTIFACT_ID, "junit");
        appChild(dep, Constant.VERSION, "4.11");
        appChild(dep, Constant.SCOPE, "test");

        Element dep2 = createElement(element, Constant.DEPENDENCY);

        appChild(dep2, Constant.GROUP_ID, "org.slf4j");
        appChild(dep2, Constant.ARTIFACT_ID, "slf4j-api");
        appChild(dep2, Constant.VERSION, "1.7.13");

        Element dep3 = createElement(element, Constant.DEPENDENCY);

        appChild(dep3, Constant.GROUP_ID, "ch.qos.logback");
        appChild(dep3, Constant.ARTIFACT_ID, "logback-classic");
        appChild(dep3, Constant.VERSION, "1.1.3");

        Element build = createElement(rootElement, Constant.BUILD);
        Element plugins = createElement(build, Constant.PLUGINS);
        Element plugin = createElement(plugins, Constant.PLUGIN);

        appChild(plugin, Constant.GROUP_ID, "org.apache.maven.plugins");
        appChild(plugin, Constant.ARTIFACT_ID, "maven-compiler-plugin");
        appChild(plugin, Constant.VERSION, "3.5.1");

        Element configuration = createElement(plugin, Constant.CONFIGURATION);

        appChild(configuration, Constant.SOURCE, "1.7");
        appChild(configuration, Constant.TARGET, "1.7");


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(path));

        transformer.transform(source, result);
    }
    private void appChild(Element parent, String tag, String content){
        Element child = doc.createElement(tag);
        child.appendChild(doc.createTextNode(content));
        parent.appendChild(child);

    }
    private void setAttr(Element element, String attrName, String attrValue){
        Attr attr = doc.createAttribute(attrName);
        attr.setValue(attrValue);
        element.setAttributeNode(attr);
    }
    private Element createElement(Element parent, String tag){
        Element element = doc.createElement(tag);
        parent.appendChild(element);
        return  element;
    }
}
