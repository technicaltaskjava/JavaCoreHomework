package model.task2;

import exception.XMLParseException;
import model.task2.entity.DependElement;
import model.task2.entity.Pom;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import utility.Constant;

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
import java.util.Map;
import java.util.Set;

public class WritePomXmlFile {
	private final Pom pom;

	public WritePomXmlFile(final Pom pom) {
		this.pom = pom;
	}

	public void write() throws XMLParseException {
		try {
			Document document = getDocument();
			getFile(document);
		} catch (ParserConfigurationException | TransformerException e) {
			throw new XMLParseException(e.getMessage(), e);
		}
	}

	private void getFile(final Document document) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(Constant.PATHNAME));
		transformer.transform(source, result);
	}

	private Document getDocument() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();

		Element rootElement = document.createElement(Constant.ROOT_ELEMENT);
		document.appendChild(rootElement);
		setAttr(rootElement);

		Element modelVersion = document.createElement(Constant.MODEL_VERSION);
		modelVersion.appendChild(document.createTextNode(Constant.MODEL_VERSION_DATA));
		rootElement.appendChild(modelVersion);

		addElement(document, rootElement, pom.getRootDependElement());

		Element packaging = document.createElement(Constant.PACKAGING);
		packaging.appendChild(document.createTextNode(Constant.JAR));
		rootElement.appendChild(packaging);

		Element dependencies = document.createElement(Constant.DEPENDENCIES);
		rootElement.appendChild(dependencies);

		addSubElement(pom.getDependElements(), document, dependencies, pom.getSubElement().get(Constant.DEPENDENCIES));

		Element build = document.createElement(Constant.BUILD);
		rootElement.appendChild(build);

		Element plugins = document.createElement(pom.getSubElement().get(Constant.BUILD));
		build.appendChild(plugins);

		addSubElement(pom.getPluginElements(), document, plugins, pom.getSubElement().get(Constant.PLUGINS));

		return document;
	}

	private void addSubElement(final Set<DependElement> dependElementList, final Document document, final Element rootElement, final String dependency) {
		for (DependElement entry : dependElementList) {
			Element element = document.createElement(dependency);
			rootElement.appendChild(element);
			addElement(document, element, entry);
		}
	}

	private void addElement(final Document document, final Element rootElement, final DependElement dependElement) {
		Element groupId = document.createElement(Constant.GROUP_ID);
		groupId.appendChild(document.createTextNode(dependElement.getGroupId()));
		rootElement.appendChild(groupId);

		Element artifactId = document.createElement(Constant.ARTIFACT_ID);
		artifactId.appendChild(document.createTextNode(dependElement.getArtifactId()));
		rootElement.appendChild(artifactId);

		Element version = document.createElement(Constant.VERSION);
		version.appendChild(document.createTextNode(dependElement.getVersion()));
		rootElement.appendChild(version);
	}

	private void setAttr(final Element rootElement) {
		final Map<String, String> attributes = pom.getAttributes();
		final Set<String> keySet = attributes.keySet();
		for (String key : keySet) {
			rootElement.setAttribute(key, attributes.get(key));
		}
	}
}
