package com.epam.task2.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Olga Kramska on 09-May-16.
 */
public class PropertiesReader {
    private static Properties properties = new Properties();

    private PropertiesReader() {
    }

    public static void load(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classloader.getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException("Can not read file: " + fileName);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
