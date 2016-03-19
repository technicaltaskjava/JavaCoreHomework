package com.epam.javase03.task02;

import com.epam.javase03.task02.Exception.KeyUnknownException;

import java.io.*;
import java.util.Properties;

public class PropertiesReader {
    private static final String fileName = "src/my.properties";
    private static String dirSeparator = System.getProperty("file.separator");
    private static Properties properties = new Properties();

    public static void main(String[] args) throws KeyUnknownException {
        File currentDir = new File(".");
        BufferedReader reader = null;
        try {
            String filePath = currentDir.getCanonicalPath() + dirSeparator + fileName;
            reader = new BufferedReader(new FileReader(filePath));
            String string;
            String[] result;
            String key, value;
            int counter = 0;
            while ((string = reader.readLine()) != null) {
                counter++;
                result = string.split("[ =]+");
                key = result[0];
                value = result[1];
                System.out.println("Key: " + key + " " + "Value: " + value);
                if (key.equals("")) {
                    throw new KeyUnknownException("Key is not specified in file " + fileName + " string #" + counter + ".");
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
