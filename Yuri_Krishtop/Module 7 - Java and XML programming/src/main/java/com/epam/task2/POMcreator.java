package com.epam.task2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Yuriy Krishtop on 11.04.2016.
 */
public class POMcreator {

    static Document document;
    static final String DEPENDENCY = "dependency";

    private POMcreator(){}

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.newDocument();
        Element project = document.createElement("project");
        project.setAttribute("xmlns", "http://maven.apache.org/POM/4.0.0");
        project.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        project.setAttribute("xsi:schemaLocation",
                "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd");

        Element modelVersion = document.createElement("modelVersion");
        modelVersion.setTextContent("4.0.0");
        project.appendChild(modelVersion);

        addGroupArtifactVersion(project, new String[]{"epam", "hm7", "1.0-SNAPSHOT"});

        Element dependencies = document.createElement("dependencies");
        project.appendChild(dependencies);

        Element dependencyOne = document.createElement(DEPENDENCY);
        dependencies.appendChild(dependencyOne);

        addGroupArtifactVersion(dependencyOne, new String[]{"org.slf4j", "slf4j-api", "1.7.20"});

        Element dependencyTwo = document.createElement(DEPENDENCY);
        dependencies.appendChild(dependencyTwo);

        addGroupArtifactVersion(dependencyTwo, new String[]{"junit", "junit", "4.12"});

        Element dependencyThree = document.createElement(DEPENDENCY);
        dependencies.appendChild(dependencyThree);

        addGroupArtifactVersion(dependencyThree, new String[]{"org.apache.hbase", "hbase-client", "1.2.1"});

        Element build = document.createElement("build");
        project.appendChild(build);

        Element plugins = document.createElement("plugins");
        build.appendChild(plugins);

        Element plugin = document.createElement("plugin");
        plugins.appendChild(plugin);

        addGroupArtifactVersion(plugin, new String[]{"org.sonarsource.scanner.maven", "sonar-maven-plugin", "3.0.1"});

        document.appendChild(project);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new FileWriter("./src/main/resources/pom.xml"));
        transformer.transform(source, result);
    }

    public static Element[] addGroupArtifactVersion(Element parent, String[] content) {
        Element[] elements = new Element[3];
        String[] tagNames = {"groupId", "artifactId", "version"};
        for(int i = 0; i < elements.length; i++) {
            elements[i] = document.createElement(tagNames[i]);
            elements[i].setTextContent(content[i]);
            parent.appendChild(elements[i]);
        }
        return elements;
    }
}
