package javase03.t02.properties;

import javase03.common.exception.MyPropertyKeyNotFoundException;

/**
 * Properties file class 
 * @author Yury.Vislobodsky
 *
 */
public class Properties {
	private Property[] properties;
	
	public Properties(String text) throws Exception {
		if (text == null) {
			return;
		}
		String[] lines = text.split("\r\n");
		String[] lexems; 
		for (String line : lines) {
			if ("!#".indexOf(line.trim().charAt(0)) != -1) {
				continue;
			}
			lexems = line.split("=");
			if (lexems.length >= 2) {
				addProperty(lexems[0].trim(), lexems[1].trim());
			}
		}
	}
	
	public String getPropertyValue(String propertyName) throws Exception {
		if (properties != null) {
			for (Property property : properties) {
				if (property.getName().equals(propertyName)) {
					return property.getValue();
				}
			}
		}
		throw new MyPropertyKeyNotFoundException(propertyName); 
	}
	
	public int length() {
		return (properties != null) ? properties.length : 0;
	}
	
	private void addProperty(String propertyName, String propertyValue) {
		if (properties != null) {
			for (Property property : properties) {
				if (property.getName().equals(propertyName)) {
					property.setValue(propertyValue);
					return;
				}
			}
		}
		Property[] newProperties = new Property[length()+1];
		for (int index = 0; index < length(); index++) {
			newProperties[index] = properties[index];
		}
		newProperties[length()] = new Property(propertyName, propertyValue);
		properties = newProperties;
	}
}
