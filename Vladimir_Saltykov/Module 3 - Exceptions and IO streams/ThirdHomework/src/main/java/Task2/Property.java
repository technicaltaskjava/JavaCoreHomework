package Task2;


import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;



public class Property {
    FileInputStream fis;
    public  void load(FileInputStream fileName) {

        try {
             fis = new FileInputStream("src/main/resources/config.properties");

        }catch(IOException e){
            e.printStackTrace();
        }

    }






    public static String getProperty(String key){
        Properties property = new Properties();
        if (property.getProperty(key) != null) {
            return property.getProperty(key);
        }
        return property.getProperty(key);
    }
}

