package task2.propreader;

import messages.Message;
import task2.exceptions.KeyNotFoundException;
import task2.exceptions.NoPropertyFileException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 10.03.2016
 */
public class PropertiesReader {
    private Message m = new Message();

    public HashMap<String, String> readProperties(String propFileName) {

        HashMap<String, String> propMap = null;
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);){

            Properties properties = new Properties();

            if (inputStream != null) {
                properties.load(inputStream);
                Enumeration e = properties.propertyNames();
                while (e.hasMoreElements()){
                    System.out.println(e.nextElement());
                }
                m.message("These are all keys in file: " + propFileName);
            } else {
                throw new NoPropertyFileException("property file \"" + propFileName + "\" not found in the classpath");
            }

            Set<String> keys = properties.stringPropertyNames();

            propMap = new HashMap<String, String>();
            for (String k: keys)
                propMap.put(k, properties.getProperty(k));

        } catch (NoPropertyFileException e) {
            m.warn(e.getMessage());
        }
        catch (IOException e) {
            m.warn("Exception: " + e);
        }
        return propMap;
    }
    public String getValue(HashMap<String, String> props, String key) throws KeyNotFoundException {
        String value = null;
        if (props.containsKey(key))value = props.get(key);
        else throw new KeyNotFoundException("Key: \"" + key + "\" not found\"");
        return   key + " = " + value;
    }


}

