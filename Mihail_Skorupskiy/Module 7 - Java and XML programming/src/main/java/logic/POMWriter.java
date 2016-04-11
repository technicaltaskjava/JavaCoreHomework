package logic;

import elements.pom.Dependency;
import elements.pom.Plugin;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class POMWriter {
    //These constants make no sense, but Sonar demanded them.
    private static final String GROUP_ID = "groupId";
    private static final String ARTIFACT_ID = "artifactId";
    private static final String VERSION = "version";

    private Logger logger = Logger.getLogger("Writer");
    private Document document;
    private Element root;

    public void createPom(String groupId, String artifactId) throws ParserConfigurationException{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        document = db.newDocument();
        generateProject(groupId, artifactId);
    }

    private void generateProject(String groupId, String artifactId){
        root = document.createElement("project");
        root.setAttribute("xmlns", "http://maven.apache.org/POM/4.0.0");
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xsi:schemaLocation", "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd");
        Element modelVersion = document.createElement("modelVersion");
        modelVersion.setTextContent("4.0.0");
        root.appendChild(modelVersion);
        Element group = document.createElement(GROUP_ID);
        group.setTextContent(groupId);
        root.appendChild(group);
        Element artifact = document.createElement(ARTIFACT_ID);
        artifact.setTextContent(artifactId);
        root.appendChild(artifact);
        Element version = document.createElement(VERSION);
        version.setTextContent("1.0-SNAPSHOT");
        root.appendChild(version);
    }

    public void createBuild(Plugin... inputPlugins){
        Element build = document.createElement("build");
        Element plugins = document.createElement("plugins");
        for (Plugin plugin : inputPlugins) {
            plugins.appendChild(createPlugin(plugin));
        }
        build.appendChild(plugins);
        root.appendChild(build);
    }

    private Element createPlugin(Plugin plugin){
        Element temp = document.createElement("plugin");
        Element tempGroupId = document.createElement(GROUP_ID);
        tempGroupId.setTextContent(plugin.getGroupId());
        temp.appendChild(tempGroupId);
        Element tempArtifactId = document.createElement(ARTIFACT_ID);
        tempArtifactId.setTextContent(plugin.getArtifactId());
        temp.appendChild(tempArtifactId);
        Element tempVersion = document.createElement(VERSION);
        tempVersion.setTextContent(plugin.getVersion());
        temp.appendChild(tempVersion);
        if (plugin.isConfigurable()) {
            createConfiguration(plugin, temp);
        }
        return temp;
    }

    private void createConfiguration(Plugin plugin, Element tempPlugin){
        Element configuration = document.createElement("configuration");
        Element source = document.createElement("source");
        Element target = document.createElement("target");
        source.setTextContent(plugin.getSource());
        target.setTextContent(plugin.getTarget());
        configuration.appendChild(source);
        configuration.appendChild(target);
        tempPlugin.appendChild(configuration);
    }

    public void createDependencies(Dependency... inputDependencies){
        Element dependencies = document.createElement("dependencies");
        for (Dependency dependency : inputDependencies) {
            dependencies.appendChild(createDependency(dependency));
        }
        root.appendChild(dependencies);
    }

    private Element createDependency(Dependency dependency){
        Element temp = document.createElement("dependency");
        Element tempGroupId = document.createElement(GROUP_ID);
        tempGroupId.setTextContent(dependency.getGroupId());
        temp.appendChild(tempGroupId);
        Element tempArtifactId = document.createElement(ARTIFACT_ID);
        tempArtifactId.setTextContent(dependency.getArtifactId());
        temp.appendChild(tempArtifactId);
        Element tempVersion = document.createElement(VERSION);
        tempVersion.setTextContent(dependency.getVersion());
        temp.appendChild(tempVersion);
        if (dependency.hasScope()) {
            Element tempScope = document.createElement("scope");
            tempScope.setTextContent(dependency.getScope());
            temp.appendChild(tempScope);
        }
        return temp;
    }

    public void write() throws TransformerException{
        document.appendChild(root);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty("indent", "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(document);
        try (FileWriter writer = new FileWriter("src/main/resources/pom/pom.xml")) {
            StreamResult result = new StreamResult(writer);
            transformer.transform(source,result);
        } catch (IOException e){
            logger.log(Level.WARNING, "Write error", e);
        }
    }
}
