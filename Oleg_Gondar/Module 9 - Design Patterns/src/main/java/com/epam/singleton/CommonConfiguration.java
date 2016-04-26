package com.epam.singleton;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


/**
 * Created by o.gondar on 26.04.2016.
 */
public class CommonConfiguration {

    private static CommonConfiguration config = null;
    private static final Logger logger = Logger.getLogger(CommonConfiguration.class);

    private Properties props = null;

    private CommonConfiguration() {
        props = new Properties();
        try {
            FileInputStream fis = new FileInputStream(
                    new File("properties.txt"));
            props.load(fis);
        } catch (Exception e) {
            org.apache.log4j.BasicConfigurator.configure();
            logger.error("file not loaded", e);
        }
    }

    public static CommonConfiguration getInstance() {
        if (config == null) {
            config = new CommonConfiguration();
        }
        return config;
    }

    public String getProperty(String key) {
        String value = null;
        if (props.containsKey(key))
            value = (String) props.get(key);
        else {
            org.apache.log4j.BasicConfigurator.configure();
            logger.info("key not founded");
        }
        return value;
    }

    public void setProperty(String key, String value) {
        props.setProperty(key, value);
    }

}


