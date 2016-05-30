package model.conf;

import exception.ConfigurationLoadException;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.Properties;

public class Configuration {
    private static final Configuration instance = new Configuration();
    private static ServletContext context = null;
    private Properties properties = null;

    private Configuration() {
    }

    public static Configuration getInstance() {
        return instance;
    }

    public static Configuration getInstance(final ServletContext ctx) {
        context = ctx;
        return instance;
    }

    public void load(final String fileName) throws ConfigurationLoadException {
        URI configurationFilePath;
        try {
            URL resource = context.getResource(fileName);
            configurationFilePath = resource.toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new ConfigurationLoadException(e);
        }
        properties = new Properties();
        try (FileInputStream stream = new FileInputStream(new File(configurationFilePath))) {
            properties.load(stream);
        } catch (IOException e) {
            throw new ConfigurationLoadException(e);
        }
    }

    public synchronized String getProperty(final String key) throws InvalidKeyException {
        String value;
        if (properties.containsKey(key)) {
            value = properties.getProperty(key);
        } else {
            throw new InvalidKeyException(String.format("Kay '%s' not found", key));
        }
        return value;
    }
}