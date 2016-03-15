package propertiesfilereader;

import propertiesfilereader.exceptions.PropertiesFileException;
import propertiesfilereader.exceptions.PropertiesFileNoKeyException;
import propertiesfilereader.exceptions.PropertiesFileNotFoundException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Alexey Ushakov
 */
public class PropertiesFileReader {

    private Properties properties;

    public PropertiesFileReader() {
        this.properties = null;
    }

    public void read(String pathToFile) throws IOException {
        Properties tempProperties = properties;
        properties = new Properties();

        try (FileInputStream inputStream = new FileInputStream(pathToFile)) {
            properties.load(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            properties = tempProperties;
            throw new PropertiesFileNotFoundException("Properties file not found [" + pathToFile + "]", e);
        }
    }

    public String[] getProperties() throws PropertiesFileNotFoundException {
        if (isLoaded()) {
            String[] propertiesList = new String[properties.size()];
            properties.stringPropertyNames().toArray(propertiesList);
            return propertiesList;
        } else {
            throw new PropertiesFileNotFoundException("Read properties file at first");
        }
    }

    public int size() {
        if (!isLoaded()) {
            return 0;
        }
        return properties.size();
    }

    public boolean isLoaded() {
        return properties != null;
    }

    public boolean isEmpty() {
        if (isLoaded() && properties.isEmpty()) {
            return true;
        }
        return false;
    }

    public String getProperty(String propertyName)
            throws PropertiesFileException {
        if (isLoaded() && !properties.isEmpty()) {
            String result = properties.getProperty(propertyName);
            if (result != null) {
                return result;
            } else {
                throw new PropertiesFileNoKeyException("Key (" + propertyName + ") not found");
            }
        } else {
            throw new PropertiesFileNotFoundException("Read properties file at first");
        }
    }
}
