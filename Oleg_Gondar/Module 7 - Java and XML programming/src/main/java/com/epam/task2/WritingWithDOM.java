package com.epam.task2;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

    private static final Logger logger = Logger.getLogger(WritingWithDOM.class);

    private WritingWithDOM() {
    }

    public static void performWrite(String pomFileName) {

        String artifactId = "artifactId";
        String groupId = "groupId";
        String version = "version";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element project = document.createElement("project");
            project.setAttribute("xmlns", "http://maven.apache.org/POM/4.0.0");
            project.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            project.setAttribute("xsi:schemaLocation", "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd");

            Element modelVersion = document.createElement("modelVersion");
            modelVersion.appendChild(document.createTextNode("4.0.0"));
            project.appendChild(modelVersion);

            Element artifactIdMaven = document.createElement(artifactId);
            artifactIdMaven.appendChild(document.createTextNode(artifactId));
            project.appendChild(artifactIdMaven);

            Element groupIdMaven = document.createElement(groupId);
            groupIdMaven.appendChild(document.createTextNode(groupId));
            project.appendChild(groupIdMaven);

            Element versionMaven = document.createElement(version);
            versionMaven.appendChild(document.createTextNode("1.0-SNAPSHOT"));
            project.appendChild(versionMaven);

            Element build = document.createElement("build");
            Element plugins = document.createElement("plugins");
            Element plugin = document.createElement("plugin");

            Element groupIdMavenPlugin = document.createElement(groupId);
            groupIdMavenPlugin.appendChild(document.createTextNode("org.apache.maven.plugins"));
            plugin.appendChild(groupIdMavenPlugin);

            Element artifactIdMavenPlugin = document.createElement(artifactId);
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

            Element groupIdJsoup = document.createElement(groupId);
            groupIdJsoup.appendChild(document.createTextNode("org.jsoup"));
            dependencyJsoup.appendChild(groupIdJsoup);

            Element artifactIdJsoup = document.createElement(artifactId);
            artifactIdJsoup.appendChild(document.createTextNode("jsoup"));
            dependencyJsoup.appendChild(artifactIdJsoup);

            Element versionJsoup = document.createElement(version);
            versionJsoup.appendChild(document.createTextNode("1.8.3"));
            dependencyJsoup.appendChild(versionJsoup);

            dependencies.appendChild(dependencyJsoup);

            Element dependencyJunit = document.createElement("dependency");

            Element groupIdJunit = document.createElement(groupId);
            groupIdJunit.appendChild(document.createTextNode("junit"));
            dependencyJunit.appendChild(groupIdJunit);

            Element artifactIdJunit = document.createElement(artifactId);
            artifactIdJunit.appendChild(document.createTextNode("junit"));
            dependencyJunit.appendChild(artifactIdJunit);

            Element versionJunit = document.createElement(version);
            versionJunit.appendChild(document.createTextNode("4.12"));
            dependencyJunit.appendChild(versionJunit);

            dependencies.appendChild(dependencyJunit);
            project.appendChild(dependencies);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(project);

            StreamResult result = new StreamResult(new FileWriter(pomFileName));
            transformer.transform(source, result);

        } catch (TransformerException | IOException | ParserConfigurationException e) {
            logger.error(e);
        }

    }
}
