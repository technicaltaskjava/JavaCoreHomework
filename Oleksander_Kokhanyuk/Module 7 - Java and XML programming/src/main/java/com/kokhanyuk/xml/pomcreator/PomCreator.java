package com.kokhanyuk.xml.pomcreator;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


/**
 * PomCreator
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class PomCreator {
    static Logger log = Logger.getLogger(PomCreator.class);

    public void pomCreating() {
        String stGroupId = "groupId";
        String stArtifactId = "artifactId";
        String stVersion = "version";
        String stDependency="dependency";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            Document doc = factory.newDocumentBuilder().newDocument();

            Element root = doc.createElement("project");
            root.setAttribute("xmlns", "http://maven.apache.org/POM/4.0.0");
            doc.appendChild(root);

            Element modelVersion = doc.createElement("modelVersion");
            modelVersion.appendChild(doc.createTextNode("4.0.0"));
            root.appendChild(modelVersion);

            Element groupId = doc.createElement(stGroupId);
            groupId.appendChild(doc.createTextNode("com.kokhanyuk"));
            root.appendChild(groupId);

            Element artifactId = doc.createElement(stArtifactId);
            artifactId.appendChild(doc.createTextNode("xml"));
            root.appendChild(artifactId);

            Element version = doc.createElement(stVersion);
            version.appendChild(doc.createTextNode("1.0-SNAPSHOT"));
            root.appendChild(version);

            Element build = doc.createElement("build");
            root.appendChild(build);

            Element resources = doc.createElement("resources");
            build.appendChild(resources);

            Element resource = doc.createElement("resource");
            resources.appendChild(resource);

            Element directory = doc.createElement("directory");
            directory.appendChild(doc.createTextNode("src\\main\\resources"));
            resource.appendChild(directory);

            Element plugins = doc.createElement("plugins");
            build.appendChild(plugins);

            Element plugin = doc.createElement("plugin");
            plugins.appendChild(plugin);

            Element groupId1 = doc.createElement(stGroupId);
            groupId1.appendChild(doc.createTextNode("org.apache.maven.plugins"));
            plugin.appendChild(groupId1);

            Element artifactId1 = doc.createElement(stArtifactId);
            artifactId1.appendChild(doc.createTextNode("maven-compiler-plugin"));
            plugin.appendChild(artifactId1);

            Element configuration = doc.createElement("configuration");
            plugin.appendChild(configuration);

            Element source = doc.createElement("source");
            source.appendChild(doc.createTextNode("1.7"));
            configuration.appendChild(source);

            Element target = doc.createElement("target");
            target.appendChild(doc.createTextNode("1.7"));
            configuration.appendChild(target);

            Element dependencies = doc.createElement("dependencies");
            root.appendChild(dependencies);

            Element dependency = doc.createElement(stDependency);
            dependencies.appendChild(dependency);

            Element groupId2 = doc.createElement(stGroupId);
            groupId2.appendChild(doc.createTextNode("log4j"));
            dependency.appendChild(groupId2);

            Element artifactId2 = doc.createElement(stArtifactId);
            artifactId2.appendChild(doc.createTextNode("log4j"));
            dependency.appendChild(artifactId2);

            Element version1 = doc.createElement(stVersion);
            version1.appendChild(doc.createTextNode("1.2.17"));
            dependency.appendChild(version1);

            Element dependency1 = doc.createElement(stDependency);
            dependencies.appendChild(dependency1);

            Element groupId3 = doc.createElement(stGroupId);
            groupId3.appendChild(doc.createTextNode("junit"));
            dependency1.appendChild(groupId3);

            Element artifactId3 = doc.createElement(stArtifactId);
            artifactId3.appendChild(doc.createTextNode("junit"));
            dependency1.appendChild(artifactId3);

            Element version2 = doc.createElement(stVersion);
            version2.appendChild(doc.createTextNode("4.12"));
            dependency1.appendChild(version2);

            Element dependency2 = doc.createElement(stDependency);
            dependencies.appendChild(dependency2);

            Element groupId4 = doc.createElement(stGroupId);
            groupId4.appendChild(doc.createTextNode("org.w3c.css"));
            dependency2.appendChild(groupId4);

            Element artifactId4 = doc.createElement(stArtifactId);
            artifactId4.appendChild(doc.createTextNode("sac"));
            dependency2.appendChild(artifactId4);

            Element version3 = doc.createElement(stVersion);
            version3.appendChild(doc.createTextNode("1.3"));
            dependency2.appendChild(version3);


            File file = new File("pom.xml");

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(file));
        } catch (ParserConfigurationException | TransformerException e) {
            log.warn(e.getMessage(), e);
        }
        log.info("pom.xml is overwriting.");
    }
}
