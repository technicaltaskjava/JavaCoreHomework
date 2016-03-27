package com.epam.task2;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Olga Kramska on 17-Mar-16.
 */
public class LogRecord {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY : HH-mm");

    private Date date;
    private String message;

    public LogRecord(String message) {
        this.message = message;
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return dateFormat.format(date) + " — " + message + "\n";
    }
}
