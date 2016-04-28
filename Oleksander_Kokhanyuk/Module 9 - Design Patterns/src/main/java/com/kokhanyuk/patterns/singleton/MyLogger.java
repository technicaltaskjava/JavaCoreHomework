package com.kokhanyuk.patterns.singleton;

import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Formatter;

/**
 * MyLogger
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MyLogger {

    Formatter when = new Formatter();
    Calendar cal = Calendar.getInstance();
    private static Logger logger = Logger.getLogger(MyLogger.class);
    private static StringBuilder log = new StringBuilder();
    private static final MyLogger INSTANCE = new MyLogger();

    private MyLogger() {
        when.format("%td-%tm-%tY:%tH-%tM -", cal, cal, cal, cal, cal);
    }

    public static MyLogger getLogger() {
        return INSTANCE;
    }

    public void info(String message) {
        MyLogger.log.append(when + " INFO: " + message + ";\n");
    }

    public void printCommadLine() {
        logger.info(MyLogger.log.toString());
    }
}
