package com.epam.task2;

import com.epam.task2.exception.KeyNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Olga Kramska on 13-Mar-16.
 */
public class PropertiesReader {
    private static Properties properties;

    private PropertiesReader() {
    }

    public static void load(String fileName) {
        properties = new Properties();
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) throws KeyNotFoundException {
        if (properties.getProperty(key) != null) {
            return properties.getProperty(key);
        } else {
            throw new KeyNotFoundException("This key does not occur in the properties");
        }
    }
}
