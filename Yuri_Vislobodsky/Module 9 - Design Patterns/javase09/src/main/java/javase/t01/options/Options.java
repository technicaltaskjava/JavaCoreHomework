package javase.t01.options;

import java.io.*;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Options class with Singleton pattern for storing different properties
 * Created by Yury Vislobodsky on 22.04.2016.
 */
public class Options {
    private static Options instance;
    private Properties properties = new Properties();
    private static Logger logger = LoggerFactory.getLogger(Options.class);

    private Options () {}

    public static Options getInstance() {
        if (instance == null) {
            instance = new Options();
            logger.info("Option instance created");
        }
        return instance;
    }

    public void loadFromFile(String fileName) {
        try (FileInputStream fis = new FileInputStream(new File(fileName))) {
            properties.load(fis);
        }
        catch (FileNotFoundException e) {
            logger.error("File not found: " + fileName, e);
        } catch (IOException e) {
            logger.error("Error when reading from file: " + fileName, e);
        }
    }

    public void saveToFile(String fileName) {
        try (FileOutputStream fos = new FileOutputStream(new File(fileName))) {
            properties.store(fos, "options");
        }
        catch (IOException e) {
            logger.error("Error when saving to file: " + fileName, e);
        }
    }

    public String getOption(String key, String defaultValue) {
        String value = defaultValue;
        if (properties.containsKey(key)) {
            value = (String) properties.get(key);
        }
        return value;
    }

    public void setOption(String key, String value) {
        properties.setProperty(key, value);
    }
}