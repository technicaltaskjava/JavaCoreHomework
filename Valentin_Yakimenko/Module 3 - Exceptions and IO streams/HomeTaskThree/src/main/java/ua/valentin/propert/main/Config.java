package ua.valentin.propert.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by valentin.yakimenko on 15.03.16.
 */
public class Config {
    private static Config instance = null;
    private Properties properties = null;

    private Config(String nameOfProperties) throws PropertiesFileNotFoundException {
        properties = new Properties();
        try (FileInputStream fis =
                     new FileInputStream(new File(nameOfProperties))) {
            properties.load(fis);
        } catch (FileNotFoundException e) {
            throw new PropertiesFileNotFoundException
                    ("File: " + nameOfProperties + ", not found");
        } catch (IOException e) {
            //catch here
        }
    }

    public static Config getInstance(String nameOfProperties) throws PropertiesFileNotFoundException {
        if (instance == null) {
            instance = new Config(nameOfProperties);
        }
        return instance;
    }

    public String getProperty(String propKey) throws PropertyKeyNotFoundException {
        String propValue;
        if (properties.containsKey(propKey)) {
            propValue = (String) properties.get(propKey);
        } else {
            throw new PropertyKeyNotFoundException
                    ("Property with key: " + propKey + ", not found");
        }
        return propValue;
    }
 }
