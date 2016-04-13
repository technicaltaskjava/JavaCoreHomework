package model.task1;

import exception.XMLParseException;

import java.util.ArrayList;
import java.util.Map;

public interface Parser { //NOSONAR Sonar wants to use a the annotation @FunctionalInterface of Java 8
	Map<String, ArrayList<String>> parse(final String xmlFileName) throws XMLParseException;
}
