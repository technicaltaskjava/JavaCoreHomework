package t02.model;

import t02.exception.ParameterNullException;
import t02.exception.PropertyNotFoundException;
import t02.model.entity.Property;

public interface Properties {
	Property[] getProperties();

	String getValue(String key) throws ParameterNullException, PropertyNotFoundException;

	void add(Property property) throws ParameterNullException;

	void remove(Property property) throws ParameterNullException, PropertyNotFoundException;

	void update(Property property) throws ParameterNullException, PropertyNotFoundException;
}
