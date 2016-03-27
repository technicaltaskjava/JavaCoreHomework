package com.kokhanyuk.javase04.crazylogger;

import com.kokhanyuk.javase03.actions.FileIO;

import java.util.Calendar;
import java.util.Formatter;

import static java.io.File.separator;

/**
 * CrazyLogger
 * This class allows you to record and store messages
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class CrazyLogger {
    public CrazyLogger() {
    }

    public CrazyLogger(String name) {
        when.format("%td-%tm-%tY:%tH-%tM-", cal, cal, cal, cal, cal);
        this.info("Process " + name + " is started");
    }

    public static StringBuilder log = new StringBuilder();
    Formatter when = new Formatter();
    Calendar cal = Calendar.getInstance();

    public void info(String message) {
        CrazyLogger.log.append(when + " INFO: " + message + ";\n");
    }

    public void err(String message, String errorReport) {
        log.append(when + " ERROR: " + message + ": " + errorReport + ";\n");
    }

    public void printCommadLine() {
        System.out.println(CrazyLogger.log.toString());
    }

    public void save(String fileName) {
        FileIO fio = new FileIO();
        fio.writeFile("Data" + separator + fileName, CrazyLogger.log.toString(), false);
    }
}
