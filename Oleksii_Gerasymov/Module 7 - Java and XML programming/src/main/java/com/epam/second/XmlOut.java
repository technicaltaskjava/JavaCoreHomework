package com.epam.second;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class XmlOut {
    private static Logger classLog = Logger.getLogger("xmlOut_log");

    private XmlOut() {
    }

    public static void runXmlOut() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            document = createDocument(document);

            OutputFormat format = new OutputFormat(document);
            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(3);
            XMLSerializer serializer = new XMLSerializer(new FileWriter("target/pom.xml"), format);
            serializer.serialize(document);
        }
        catch (ParserConfigurationException | IOException parsingException) {
            classLog.info(String.valueOf(parsingException));
        }
    }

    private static Document createDocument(Document document) {

        String groupIdElement = "groupId";
        String artifactIdElement = "artifactId";
        Element project = document.createElement("project");
        project.setAttribute("xmlns", "http://maven.apache.org/POM/4.0.0");
        project.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        project.setAttribute("xsi:schemaLocation", "http://maven.apache.org/xsd/maven-4.0.0.xsd");

        Element modelVersion = document.createElement("modelVersion");
        modelVersion.setTextContent("4.0.0");
        project.appendChild(modelVersion);

        Element groupId = document.createElement(groupIdElement);
        groupId.setTextContent("com.epam");
        project.appendChild(groupId);

        Element artifactId = document.createElement(artifactIdElement);
        artifactId.setTextContent("seven-homework");
        project.appendChild(artifactId);

        Element version = document.createElement("version");
        version.setTextContent("1.0");
        project.appendChild(version);

        Element properties = document.createElement("properties");
        Element sourceEncoding = document.createElement("project.build.sourceEncoding");
        sourceEncoding.setTextContent("Cp1251");
        properties.appendChild(sourceEncoding);
        project.appendChild(properties);

        Element build = document.createElement("build");
        project.appendChild(build);
        Element plugins = document.createElement("plugins");
        build.appendChild(plugins);

        Element plugin1 = document.createElement("plugin");
        plugins.appendChild(plugin1);
        Element groupId1 = document.createElement(groupIdElement);
        groupId1.setTextContent("org.apache.maven.plugins");
        plugin1.appendChild(groupId1);

        Element artifactId1 = document.createElement(artifactIdElement);
        artifactId1.setTextContent("maven-compiler-plugin");
        plugin1.appendChild(artifactId1);
        Element configuration1 = document.createElement("configuration");
        plugin1.appendChild(configuration1);
        Element source1 = document.createElement("source");
        source1.setTextContent("1.7");
        configuration1.appendChild(source1);
        Element target1 = document.createElement("target");
        target1.setTextContent("1.7");
        configuration1.appendChild(target1);

        Element plugin2 = document.createElement("plugin");
        plugins.appendChild(plugin2);
        Element groupId2 = document.createElement(groupIdElement);
        groupId2.setTextContent("org.apache.maven.plugins");
        plugin2.appendChild(groupId2);

        Element artifactId2 = document.createElement(artifactIdElement);
        artifactId2.setTextContent("maven-jar-plugin");
        plugin2.appendChild(artifactId2);
        Element configuration2 = document.createElement("configuration");
        plugin2.appendChild(configuration2);
        Element archive = document.createElement("archive");
        configuration2.appendChild(archive);
        Element manifest = document.createElement("manifest");
        archive.appendChild(manifest);

        Element addClasspath = document.createElement("addClasspath");
        addClasspath.setTextContent("true");
        manifest.appendChild(addClasspath);
        Element mainClass = document.createElement("mainClass");
        mainClass.setTextContent("com.epam.RunXML");
        manifest.appendChild(mainClass);

        String[][] depArray = {{"junit", "junit", "4.8.2"}, {"xerces", "xercesImpl", "2.11.0"},
                {"org.hibernate","hibernate-annotations","3.3.0.ga"}};
        Element dependencies = document.createElement("dependencies");
        project.appendChild(dependencies);
        for(int index = 0; index <= 2; index++) {
            Element dependency = document.createElement("dependency");
            dependencies.appendChild(dependency);
            Element groupIdd = document.createElement(groupIdElement);
            groupIdd.setTextContent(depArray[index][0]);
            dependency.appendChild(groupIdd);
            Element artifactIdd = document.createElement(artifactIdElement);
            artifactIdd.setTextContent(depArray[index][1]);
            dependency.appendChild(artifactIdd);
            Element versiond = document.createElement("version");
            versiond.setTextContent(depArray[index][2]);
            dependency.appendChild(versiond);
        }

        document.appendChild(project);
        return document;
    }
}