package com.epam.task2;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by O.Gondar on 11.04.2016.
 */
public class WritingWithDOM {

    private static final String ARTIFACT_ID = "artifactId";
    private static final String GROUP_ID = "groupId";
    private static final String VERSION = "version";
    private static final String XMLNS = "http://maven.apache.org/POM/4.0.0";
    private static final String XMLNS_XSI = "http://www.w3.org/2001/XMLSchema-instance";
    private static final String XSI_SCHEMA_LOCATION = "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd";
    private static final Logger logger = Logger.getLogger(WritingWithDOM.class);

    private WritingWithDOM() {
    }

    public static void performWrite(String pomFileName) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element project = document.createElement("project");
            project.setAttribute("xmlns", XMLNS);
            project.setAttribute("xmlns:xsi", XMLNS_XSI);
            project.setAttribute("xsi:schemaLocation", XSI_SCHEMA_LOCATION);

            Element modelVersion = document.createElement("modelVersion");
            modelVersion.appendChild(document.createTextNode("4.0.0"));
            project.appendChild(modelVersion);

            Element artifactIdMaven = document.createElement(ARTIFACT_ID);
            artifactIdMaven.appendChild(document.createTextNode(ARTIFACT_ID));
            project.appendChild(artifactIdMaven);

            Element groupIdMaven = document.createElement(GROUP_ID);
            groupIdMaven.appendChild(document.createTextNode(GROUP_ID));
            project.appendChild(groupIdMaven);

            Element versionMaven = document.createElement(VERSION);
            versionMaven.appendChild(document.createTextNode("1.0-SNAPSHOT"));
            project.appendChild(versionMaven);

            Element build = document.createElement("build");
            Element plugins = document.createElement("plugins");
            Element plugin = document.createElement("plugin");

            Element groupIdMavenPlugin = document.createElement(GROUP_ID);
            groupIdMavenPlugin.appendChild(document.createTextNode("org.apache.maven.plugins"));
            plugin.appendChild(groupIdMavenPlugin);

            Element artifactIdMavenPlugin = document.createElement(ARTIFACT_ID);
            artifactIdMavenPlugin.appendChild(document.createTextNode("maven-compiler-plugin"));
            plugin.appendChild(artifactIdMavenPlugin);

            Element configurationMavenPlugin = document.createElement("configuration");

            Element sourceEl = document.createElement("source");
            sourceEl.appendChild(document.createTextNode("1.7"));
            configurationMavenPlugin.appendChild(sourceEl);

            Element target = document.createElement("target");
            target.appendChild(document.createTextNode("1.7"));
            configurationMavenPlugin.appendChild(target);

            plugin.appendChild(configurationMavenPlugin);
            plugins.appendChild(plugin);
            build.appendChild(plugins);
            project.appendChild(build);

            Element dependencies = document.createElement("dependencies");

            Element dependencyJsoup = document.createElement("dependency");

            Element groupIdJsoup = document.createElement(GROUP_ID);
            groupIdJsoup.appendChild(document.createTextNode("org.jsoup"));
            dependencyJsoup.appendChild(groupIdJsoup);

            Element artifactIdJsoup = document.createElement(ARTIFACT_ID);
            artifactIdJsoup.appendChild(document.createTextNode("jsoup"));
            dependencyJsoup.appendChild(artifactIdJsoup);

            Element versionJsoup = document.createElement(VERSION);
            versionJsoup.appendChild(document.createTextNode("1.8.3"));
            dependencyJsoup.appendChild(versionJsoup);

            dependencies.appendChild(dependencyJsoup);

            Element dependencyJunit = document.createElement("dependency");

            Element groupIdJunit = document.createElement(GROUP_ID);
            groupIdJunit.appendChild(document.createTextNode("junit"));
            dependencyJunit.appendChild(groupIdJunit);

            Element artifactIdJunit = document.createElement(ARTIFACT_ID);
            artifactIdJunit.appendChild(document.createTextNode("junit"));
            dependencyJunit.appendChild(artifactIdJunit);

            Element versionJunit = document.createElement(VERSION);
            versionJunit.appendChild(document.createTextNode("4.12"));
            dependencyJunit.appendChild(versionJunit);

            dependencies.appendChild(dependencyJunit);
            project.appendChild(dependencies);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(project);

            StreamResult result = new StreamResult(new FileWriter(pomFileName));
            transformer.transform(source, result);

        } catch (TransformerException | IOException | ParserConfigurationException e) {
            org.apache.log4j.BasicConfigurator.configure();
            logger.error(e);
        }

    }
}
