package com.data.mydao.connection;



import com.data.mydao.exeptions.DAOConfigExcp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionProperties {
    private static final String PROPERTIES_FILE = "dao.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

        if (propertiesFile == null) {
            throw new DAOConfigExcp(
                    "Properties file '" + PROPERTIES_FILE + "' is missing in classpath.");
        }
        try {
            PROPERTIES.load(propertiesFile);
        } catch (IOException e) {
            throw new DAOConfigExcp(
                    "Cannot load properties file '" + PROPERTIES_FILE + "'.", e);
        }
    }

    private String specificKey;
    public ConnectionProperties(String specificKey){
        this.specificKey = specificKey;
    }


    public String getProperty(String key, boolean mandatory){
        String fullKey = specificKey + "." + key;
        String property = PROPERTIES.getProperty(fullKey);
        if (property == null || property.trim().length() == 0) {
            if (mandatory) {
                throw new DAOConfigExcp("Required property '" + fullKey + "'"
                        + " is missing in properties file '" + PROPERTIES_FILE + "'.");
            } else {
                property = null;
            }
        }
        return property;
    }

}
