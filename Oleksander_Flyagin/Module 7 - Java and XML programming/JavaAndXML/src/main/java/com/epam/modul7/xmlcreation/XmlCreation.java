package com.epam.modul7.xmlcreation;


import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.FileWriter;
import java.io.IOException;

public class XmlCreation
    {
        private XmlCreation()
            {
            }

        public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException
            {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = factory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();
                Element projectL  = document.createElement("project");
                Attr attrProject  = document.createAttribute("xmlns");
                Attr attrProject2 = document.createAttribute("xsi");
                Attr attrProject3 = document.createAttribute("schemaLocation");
                attrProject2.setValue("http://www.w3.org/2001/XMLSchema-instance");
                attrProject3.setValue("http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd");
                attrProject.setValue("http://maven.apache.org/POM/4.0.0");
                document.appendChild(projectL);
                projectL.setAttributeNode(attrProject);
                projectL.setAttributeNS(attrProject2.getValue(),attrProject2.getName()+ ':'
                                                                     + attrProject3.getName(),attrProject2.getValue());


                Element modelVersionL = document.createElement("modelVersion");
                modelVersionL.appendChild(document.createTextNode("4.0.0"));
                projectL.appendChild(modelVersionL);

                Element groupIdL      = document.createElement("groupId");
                groupIdL.appendChild(document.createTextNode("com.epam"));
                projectL.appendChild(groupIdL);

                Element artifactIdL    = document.createElement("artifactId");
                artifactIdL.appendChild(document.createTextNode("modul7"));
                projectL.appendChild(artifactIdL);

                Element versionL       = document.createElement("version");
                versionL.appendChild(document.createTextNode("1.0-SNAPSHOT"));
                projectL.appendChild(versionL);

                Element dependenciesL1  = document.createElement("dependencies");
                projectL.appendChild(dependenciesL1);

                Element dependencieL1   = document.createElement("dependency");
                dependenciesL1.appendChild(dependencieL1);

                Element dep1GroupIdL    = document.createElement("groupId");
                dep1GroupIdL.appendChild(document.createTextNode("junit"));
                dependencieL1.appendChild(dep1GroupIdL);

                Element dep1ArtifactIdL = document.createElement("artifactId");
                dep1ArtifactIdL.appendChild(document.createTextNode("junit"));
                dependencieL1.appendChild(dep1ArtifactIdL);

                Element dep1VersionL   = document.createElement("version");
                dep1VersionL.appendChild(document.createTextNode("4.4"));
                dependencieL1.appendChild(dep1VersionL);

                Element dep1ScopeL     = document.createElement("scope");
                dep1ScopeL.appendChild(document.createTextNode("test"));
                dependencieL1.appendChild(dep1ScopeL);

                Element dependencieL2  = document.createElement("dependency");
                dependenciesL1.appendChild(dependencieL2);

                Element dep2GroupIdL   = document.createElement("groupId");
                dep2GroupIdL.appendChild(document.createTextNode("org.powermock"));
                dependencieL2.appendChild(dep2GroupIdL);

                Element dep2ArtifactIdL = document.createElement("artifactId");
                dep2ArtifactIdL.appendChild(document.createTextNode("powermock-reflect"));
                dependencieL2.appendChild(dep2ArtifactIdL);

                Element dep2VersionL   = document.createElement("version");
                dep2VersionL.appendChild(document.createTextNode("1.5"));
                dependencieL2.appendChild(dep2VersionL);

                Element dependencieL3  = document.createElement("dependency");
                dependenciesL1.appendChild(dependencieL3);

                Element dep3GroupIdL   = document.createElement("groupId");
                dep3GroupIdL.appendChild(document.createTextNode("javassist"));
                dependencieL3.appendChild(dep3GroupIdL);

                Element dep3ArtifactIdL = document.createElement("artifactId");
                dep3ArtifactIdL.appendChild(document.createTextNode("javassist"));
                dependencieL3.appendChild(dep3ArtifactIdL);

                Element dep3VersionL    = document.createElement("version");
                dep3VersionL.appendChild(document.createTextNode("3.13.0-GA"));
                dependencieL3.appendChild(dep3VersionL);

                Element dep3ScopeL       = document.createElement("scope");
                dep3ScopeL.appendChild(document.createTextNode("compile"));
                dependencieL3.appendChild(dep3ScopeL);

                Element buildL            = document.createElement("build");
                projectL.appendChild(buildL);

                Element plaginsL          = document.createElement("plugins");
                buildL.appendChild(plaginsL);

                Element pluginL            = document.createElement("plugin");
                plaginsL.appendChild(pluginL);

                Element plaginGroupIdL     = document.createElement("groupId");
                plaginGroupIdL.appendChild(document.createTextNode("org.apache.maven.plugins"));
                pluginL.appendChild(plaginGroupIdL);

                Element plaginArtifactIdL   = document.createElement("artifactId");
                plaginArtifactIdL.appendChild(document.createTextNode("maven-compiler-plugin"));
                pluginL.appendChild(plaginArtifactIdL);

                Element plaginConfigurationL = document.createElement("configuration");
                pluginL.appendChild(plaginConfigurationL);

                Element configurationSourceL  = document.createElement("source");
                configurationSourceL.appendChild(document.createTextNode("1.7"));
                plaginConfigurationL.appendChild(configurationSourceL);

                Element configurationTargetL  = document.createElement("target");
                configurationTargetL.appendChild(document.createTextNode("1.7"));
                plaginConfigurationL.appendChild(configurationTargetL);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(new FileWriter("src/main/resources/pom.xml"));
                transformer.transform(source, result);

            }
    }


