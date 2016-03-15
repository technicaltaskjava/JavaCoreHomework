package com.epam.task2;

/**
 * Created by Olga Kramska on 13-Mar-16.
 */
public class MainTask2 {
    public static void main(String[] args) throws Exception {
        String fileName = "config.properties";
        PropertiesReader.load(fileName);
        System.out.println(PropertiesReader.getProperty("company1"));
        System.out.println(PropertiesReader.getProperty("prop"));
    }
}
