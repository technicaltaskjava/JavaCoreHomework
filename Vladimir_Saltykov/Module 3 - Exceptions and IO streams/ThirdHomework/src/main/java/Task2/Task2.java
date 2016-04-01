package Task2;

import java.io.*;


public class Task2 {


    public static void main(String[] args) {

        FileInputStream fis;
        Property property = new Property();

        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);


            System.out.println(Property.getProperty("host"));
            System.out.println(Property.getProperty("name"));
            System.out.println(Property.getProperty("age"));



        } catch (IOException e) {
            System.err.println("Error 404! File not found!");
        }

    }

}
