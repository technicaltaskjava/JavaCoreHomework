package t02.model;

import t02.exception.PropertyException;
import t02.model.entity.Property;

public interface PropertiesService {
	void load(String fileName) throws PropertyException;

	void load(String fileName, String separator) throws PropertyException;

	void add(String key, String value) throws PropertyException;

	void add(Property property) throws PropertyException;

	void update(String key, String value) throws PropertyException;

	void update(Property property) throws PropertyException;

	void remove(String key) throws PropertyException;

	void remove(Property property) throws PropertyException;

	String getValueByKey(String key) throws PropertyException;

	Property[] getProperties();

	String getSeparator();
}
