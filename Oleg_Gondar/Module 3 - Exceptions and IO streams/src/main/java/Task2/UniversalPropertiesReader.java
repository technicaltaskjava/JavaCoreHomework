package Task2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Oleg on 14.03.2016.
 */
public class UniversalPropertiesReader {

    public static void main(String[] args) {
        // String path = WorkWithConsoleInput.enterPath();
        UniversalPropertiesReader universalPropertiesReader = new UniversalPropertiesReader();
        universalPropertiesReader.setProperty("1", "test1");
        universalPropertiesReader.createProperties("test.txt");

        try {
            universalPropertiesReader.loadProperties("test.txt");
        } catch (PropertyFileNotFound propertyFileNotFound) {
            propertyFileNotFound.printStackTrace();
        }

        System.out.println(universalPropertiesReader.getPropertyValue("1"));
        System.out.println(universalPropertiesReader.getPropertyValue("5"));

        try {
            universalPropertiesReader.loadProperties("c:/wrongPath!!!!");
        } catch (PropertyFileNotFound propertyFileNotFound) {
            propertyFileNotFound.printStackTrace();
        }


    }

    private Properties properties;

    UniversalPropertiesReader() {
        properties = new Properties();
    }

    public void setProperty(String key, String vaalue) {
        properties.setProperty(key, vaalue);
    }

    public void createProperties(String path) {

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            System.out.println("Properties file not created");
        }
        try {
            if (fileOutputStream != null) {
                properties.store(fileOutputStream, "test");
            }
        } catch (IOException e) {

            e.printStackTrace();
        }


    }


    public void loadProperties(String path) throws PropertyFileNotFound {

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new PropertyFileNotFound("File not found", path);
        }
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Property file not loaded " + e.getMessage());
        }
        try {
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public String getPropertyValue(String key) {
        try {
            return throwExceptionWithoutKeyFound(key);

        } catch (KeyNotFoundInPropertyFileException e) {
            System.out.println("Key " + e.notFindedKey + " not found");
        }
        return null;
    }

    private String throwExceptionWithoutKeyFound(String key) throws KeyNotFoundInPropertyFileException {
        if (properties.getProperty(key) == null) {
            throw new KeyNotFoundInPropertyFileException(key);
        }
        return properties.getProperty(key);
    }

}

