package model.parser;

import model.exception.XMLParseException;

import java.util.Map;

public interface Parser {
	Map<FormField, String> parse(final String xmlFileName) throws XMLParseException;
}
