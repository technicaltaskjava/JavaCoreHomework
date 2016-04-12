package homework.task2;

import homework.Constant;
import org.w3c.dom.Attr;
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
import java.io.File;

public class WriteXMLFile {

    private WriteXMLFile() {
    }

    public static void main(String[] args) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();


            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(Constant.PROJECT);
            add(doc, rootElement);

            addPlugins(doc, rootElement);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");


            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Constant.PATHNAME));

            transformer.transform(source, result);
            System.out.println("File saved!");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);


        } catch (ParserConfigurationException | TransformerException e) {//NOSONAR
            e.getStackTrace();
        }
    }

    private static void addPlugins(Document doc, Element rootElement) {
        Element build = doc.createElement(Constant.BUILD);
        rootElement.appendChild(build);

        Element plugins = doc.createElement(Constant.PLUGINS);
        build.appendChild(plugins);

        Element plugin = doc.createElement(Constant.PLUGIN);
        plugins.appendChild(plugin);

        addDependency(doc, plugin, "org.apache.maven.plugins", "maven-compiler-plugin", "3.5.1");

        Element configuration = doc.createElement(Constant.CONFIGURATION);
        plugin.appendChild(configuration);

        Element source = doc.createElement(Constant.SOURCE);
        source.appendChild(doc.createTextNode(Constant.JAVA_VERSION));
        configuration.appendChild(source);

        Element target = doc.createElement(Constant.TARGET);
        target.appendChild(doc.createTextNode(Constant.JAVA_VERSION));
        configuration.appendChild(target);
    }

    private static void add(Document doc, Element rootElement) {
        Attr attr = doc.createAttribute("xmlns");
        attr.setValue("http://maven.apache.org/POM/4.0.0");
        Attr attr1 = doc.createAttribute("xmlns:xsi");
        attr1.setValue("http://www.w3.org/2001/XMLSchema-instance");
        Attr attr2 = doc.createAttribute("xsi:schemaLocation");
        attr2.setValue("http://maven.apache.org/xsd/maven-4.0.0.xsd");
        rootElement.setAttributeNode(attr);
        rootElement.setAttributeNode(attr1);
        rootElement.setAttributeNode(attr2);
        doc.appendChild(rootElement);

        Element modelVersion = doc.createElement(Constant.MODEL_VERSION);
        modelVersion.appendChild(doc.createTextNode("4.0.0"));
        rootElement.appendChild(modelVersion);

        addDependency(doc, rootElement, "javase07", "javase07", "1.0-SNAPSHOT");

        Element packaging = doc.createElement(Constant.PACKAGING);
        packaging.appendChild(doc.createTextNode("jar"));
        rootElement.appendChild(packaging);

        Element dependencies = doc.createElement(Constant.DEPENDENCIES);
        rootElement.appendChild(dependencies);

        Element dependency = doc.createElement(Constant.DEPENDENCY);
        dependencies.appendChild(dependency);
        addDependency(doc, dependency, "junit", "junit", "4.12");
        dependency = doc.createElement(Constant.DEPENDENCY);
        dependencies.appendChild(dependency);
        addDependency(doc, dependency, "org.apache.maven.shared", "maven-dependency-analyzer", "1.6");
        dependency = doc.createElement(Constant.DEPENDENCY);
        dependencies.appendChild(dependency);
        addDependency(doc, dependency, "org.specs2", "specs2-core_2.11", "3.7.3-scalacheck-1.12");
    }

    private static void addDependency(Document doc, Element dependency, String groupId, String artifactId, String version) {
        Element groupIdElement = doc.createElement(Constant.GROUP_ID);
        groupIdElement.appendChild(doc.createTextNode(groupId));
        dependency.appendChild(groupIdElement);


        Element artifactIdElement = doc.createElement(Constant.ARTIFACT_ID);
        artifactIdElement.appendChild(doc.createTextNode(artifactId));
        dependency.appendChild(artifactIdElement);


        Element versionElement = doc.createElement(Constant.VERSION);
        versionElement.appendChild(doc.createTextNode(version));
        dependency.appendChild(versionElement);
    }

}


