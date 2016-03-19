package com.epam.task02properties;

import java.io.*;
import java.util.*;

public  class FileProperties {
    String fileName;

   public FileProperties(String fileName) {
        this.fileName = fileName;
    }

    public void showProperties() {
        Properties properties = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                System.out.println("File " + fileName + " didn`t find.");
                return;
            }
            properties.load(is);
            Enumeration<?> e = properties.propertyNames();
            if (!e.hasMoreElements()) {
                System.out.println("No keys in file");
                return;
            }
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = properties.getProperty(key);
                System.out.println("Key : " + key + ", Value : " + value);
            }
            is.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

}
