package javase.t02;

import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Create pom.xml using DOM Parser (Demo)
 * Created by Yury Vislobodsky on 10.04.2016.
 */
public class CreatePomDemo {
    private CreatePomDemo() {}

    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException {
        final String tagGroupId = "groupId";
        final String tagArtifactId = "artifactId";
        final String tagVersion = "version";
        final String tagDependency = "dependency";
        final String tagPlugin = "plugin";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element project = document.createElement("project");
        project.setAttribute("xmlns", "http://maven.apache.org/POM/4.0.0");
        project.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        project.setAttribute("xsi:schemaLocation",
                "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd");

        appendChildElement(document, project, "modelVersion", "4.0.0");
        appendChildElement(document, project, tagGroupId, "javase");
        appendChildElement(document, project, tagArtifactId, "javase");
        appendChildElement(document, project, tagVersion, "1.0-SNAPSHOT");

        Element build = appendChildElement(document, project, "build", "");
        Element plugins = appendChildElement(document, build, "plugins", "");

        Element plugin = appendChildElement(document, plugins, tagPlugin, "");
        appendChildElement(document, plugin, tagGroupId, "org.apache.maven.plugins");
        appendChildElement(document, plugin, tagArtifactId, "maven-compiler-plugin");
        Element configuration = appendChildElement(document, plugin, "configuration", "");
        appendChildElement(document, configuration, "source", "1.7");
        appendChildElement(document, configuration, "target", "1.7");

        Element dependencies = appendChildElement(document, project, "dependencies", "");

        Element dependency = appendChildElement(document, dependencies, tagDependency, "");
        appendChildElement(document, dependency, tagGroupId, "org.slf4j");
        appendChildElement(document, dependency, tagArtifactId, "slf4j-api");
        appendChildElement(document, dependency, tagVersion, "1.5.6");

        dependency = appendChildElement(document, dependencies, tagDependency, "");
        appendChildElement(document, dependency, tagGroupId, "org.reflections");
        appendChildElement(document, dependency, tagArtifactId, "reflections");
        appendChildElement(document, dependency, tagVersion, "0.9.9-RC1");

        dependency = appendChildElement(document, dependencies, "dependency", "");
        appendChildElement(document, dependency, tagGroupId, "junit");
        appendChildElement(document, dependency, tagArtifactId, "junit");
        appendChildElement(document, dependency, tagVersion, "4.0");
        appendChildElement(document, dependency, "type", "jar");
        appendChildElement(document, dependency, "scope", "test");
        appendChildElement(document, dependency, "optional", "true");

        document.appendChild(project);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new FileWriter("src/main/resources/pom.xml"));
        transformer.transform(source, result);
    }

    private static Element appendChildElement(Document document, Element parentElement,
                                              String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.setTextContent(textContent);
        parentElement.appendChild(element);
        return element;
    }
}

