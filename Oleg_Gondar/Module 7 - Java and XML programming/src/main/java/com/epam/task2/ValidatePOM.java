package com.epam.task2;


import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Oleg on 11.04.2016.
 */
public class ValidatePOM {

    private static final String MAVEN_SCHEMA_URL = "https://maven.apache.org/xsd/maven-4.0.0.xsd";
    private static final Logger logger = Logger.getLogger(ValidatePOM.class);

    private ValidatePOM() {
    }

    public static boolean isValid(String fileName) {

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(new URL(MAVEN_SCHEMA_URL).openStream()));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(fileName)));
            return true;
        } catch (IOException | SAXException e) {
            org.apache.log4j.BasicConfigurator.configure();
            logger.error(e);
        }
        return false;

    }
}
