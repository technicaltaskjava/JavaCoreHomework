package com.epam.task2;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import static org.junit.Assert.assertTrue;

/**
 * Created by Olga Kramska on 12-Apr-16.
 */
public class PomCreateTest {

    @Test
    public void testCreatePom() throws IOException, JAXBException, SAXException {
        PomCreator pomCreator = new PomCreator();
        pomCreator.addDependency("junit", "junit", "4.12");
        pomCreator.addDependency("org.apache.maven", "maven-embedder", "2.0");
        pomCreator.addDependency("com.google.code.gson", "gson", "2.3.1");
        pomCreator.addPlugin("org.sonarsource.scanner.maven", "sonar-maven-plugin", "3.0.1");

        File file = new File(System.getProperty("user.home") + "/pom.xml");
        OutputStream out = new FileOutputStream(file);
        pomCreator.create(out);
        assertTrue(file.exists());

        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        URL urlSchema = new URL("https://maven.apache.org/xsd/maven-4.0.0.xsd");
        Schema schema = factory.newSchema(urlSchema);
        Validator validator = schema.newValidator();
        Source source = new StreamSource(file);
        validator.validate(source);
    }
}
