package model.task2;

import exception.ParameterIsNullException;
import exception.XMLParseException;
import model.task2.entity.DependElement;
import model.task2.entity.Pom;
import org.junit.Test;

public class WritePomXmlFileTest {
	@Test
	public void testWrite() throws XMLParseException {
		try {
			Pom pom = new Pom();
			pom.setRootDependElement(new DependElement("aleksashin", "javase07", "1"));
			pom.addDependElement("org.slf4j", "slf4j-api", "1.7.20");
			pom.addDependElement("ch.qos.logback", "logback-core", "1.1.7");
			pom.addDependElement("ch.qos.logback", "logback-classic", "1.1.7");
			pom.addDependElement("junit", "junit", "4.12");
			pom.addPluginElement("org.apache.maven.plugins", "maven-compiler-plugin", "3.5.1");
			pom.addPluginElement("org.sonarsource.scanner.maven", "sonar-maven-plugin", "3.0.1");

			WritePomXmlFile writePomXmlFile = new WritePomXmlFile(pom);
			writePomXmlFile.write();
		} catch (ParameterIsNullException e) {
			throw new XMLParseException(e.getMessage(), e);
		}
	}
	
}