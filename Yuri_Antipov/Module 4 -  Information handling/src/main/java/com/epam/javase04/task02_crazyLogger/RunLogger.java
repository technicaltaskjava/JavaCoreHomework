package com.epam.javase04.task02_crazyLogger;

public class RunLogger {
    public static void main(String[] args) {
        CrazyLogger logger = new CrazyLogger();
        logger.addLog("Create new instance of Calendar");
        logger.addLog("Run program.");
        logger.addLog("Do something.");

        logger.viewLogs();

        logger.findLog("");
        logger.findLog("some");
        logger.findLog("22-03");
    }
}
