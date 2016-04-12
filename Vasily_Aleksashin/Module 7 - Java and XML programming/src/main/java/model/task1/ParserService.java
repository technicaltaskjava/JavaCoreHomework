package model.task1;

import exception.XMLParseException;
import model.task1.enums.ParserType;
import model.task1.impl.DomParserImpl;
import model.task1.impl.SaxParserImpl;
import model.task1.impl.StaxParserImpl;
import utility.Constant;

import java.util.*;

public class ParserService {
	private final Parser parser;
	private Map<String, ArrayList<String>> persons = new HashMap<>();

	private ParserService(final Parser parser) {
		this.parser = parser;
	}

	public static ParserService getInstance(final ParserType type) {
		switch (type) {
			case SAX:
				return new ParserService(new SaxParserImpl());
			case STAX:
				return new ParserService(new StaxParserImpl());
			case DOM:
			default:
				return new ParserService(new DomParserImpl());
		}
	}

	public void loadXML(final String xmlFileName) throws XMLParseException {
		persons = parser.parse(xmlFileName);
	}

	public Set<String> getPersons() {
		return persons.keySet();
	}

	public List<String> getPhrase(final String persona) {
		return persons.get(persona);
	}

	public int getCount(final String persona) {
		final ArrayList<String> phrases = persons.get(persona);
		return phrases != null ? phrases.size() : -1;
	}

	public int gatAverage(final String persona) {
		int sum = 0;
		final List<String> phrases = getPhrase(persona);
		if (phrases != null) {
			int count = phrases.size();
			for (String phrase : phrases) {
				sum += Constant.PATTERN_SPACE.split(phrase).length;
			}
			return sum / count;
		} else {
			return -1;
		}
	}

}
