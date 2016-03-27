package com.epam.task2;

/**
 * Created by Olga Kramska on 17-Mar-16.
 */
public class Main {
    public static void main(String[] args) {
//        CrazyLogger logger = new CrazyLogger("Log.log");
        CrazyLogger logger = new CrazyLogger();
        logger.log("Hello!");
        logger.log("Homework 4");
        logger.log("Buy!");

        logger.search("H");
    }
}
