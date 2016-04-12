package com.epam.task2;

import org.apache.log4j.Logger;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Oleg on 11.04.2016.
 */
public class WritingWithStAX {

    private static final Logger logger = Logger.getLogger(WritingWithStAX.class);

    private WritingWithStAX() {
    }

    public static void performWrite(String pomFileName) {

        String artifactId = "artifactId";
        String groupId = "groupId";
        String version = "version";

        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(pomFileName));

            writer.writeStartDocument();
            writer.writeStartElement("project");
            writer.writeAttribute("xmlns", "http://maven.apache.org/POM/4.0.0");
            writer.writeAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            writer.writeAttribute("xsi:schemaLocation", "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd");

            writer.writeStartElement("modelVersion");
            writer.writeCharacters("4.0.0");
            writer.writeEndElement();

            writer.writeStartElement(artifactId);
            writer.writeCharacters(artifactId);
            writer.writeEndElement();

            writer.writeStartElement(groupId);
            writer.writeCharacters(groupId);
            writer.writeEndElement();

            writer.writeStartElement(version);
            writer.writeCharacters("1.0-SNAPSHOT");
            writer.writeEndElement();

            writer.writeStartElement("build");
            writer.writeStartElement("plugins");
            writer.writeStartElement("plugin");

            writer.writeStartElement(groupId);
            writer.writeCharacters("org.apache.maven.plugins");
            writer.writeEndElement();
            writer.writeStartElement(artifactId);
            writer.writeCharacters("maven-compiler-plugin");
            writer.writeEndElement();
            writer.writeStartElement("configuration");
            writer.writeStartElement("source");
            writer.writeCharacters("1.7");
            writer.writeEndElement();
            writer.writeStartElement("target");
            writer.writeCharacters("1.7");
            writer.writeEndElement();

            writer.writeEndElement();
            writer.writeEndElement();
            writer.writeEndElement();
            writer.writeEndElement();

            writer.writeStartElement("dependencies");

            writer.writeStartElement("dependency");
            writer.writeStartElement(groupId);
            writer.writeCharacters("org.jsoup");
            writer.writeEndElement();
            writer.writeStartElement(artifactId);
            writer.writeCharacters("jsoup");
            writer.writeEndElement();
            writer.writeStartElement(version);
            writer.writeCharacters("1.8.3");
            writer.writeEndElement();
            writer.writeEndElement();

            writer.writeStartElement("dependency");
            writer.writeStartElement(groupId);
            writer.writeCharacters("junit");
            writer.writeEndElement();
            writer.writeStartElement("artifactId");
            writer.writeCharacters("junit");
            writer.writeEndElement();
            writer.writeStartElement(version);
            writer.writeCharacters("4.12");
            writer.writeEndElement();
            writer.writeEndElement();

            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();
        } catch (XMLStreamException | IOException e) {
            logger.error(e);
        }
    }
}
