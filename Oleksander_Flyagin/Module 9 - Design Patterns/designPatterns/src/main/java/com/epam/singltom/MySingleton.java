package com.epam.singltom;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class MySingleton {
    private static volatile MySingleton instance = null;
    private Logger myLog = null;
    private final String serialnuber = "001003362658647";
    private List information = new ArrayList();
    private final String logInfo = "\r\nMY Singleton: " + MySingleton.this + " LOGGER NUBER =  " + serialnuber;


    private MySingleton() {
        myLog = Logger.getLogger("Loger");

    }

    public static MySingleton getInstance() {
        if (instance == null) {
            synchronized (MySingleton.class) {
                if (instance == null)
                    instance = new MySingleton();
            }
        }
        return instance;
    }

    public synchronized void getLog(List list, String name) {
        information.add(list);
        myLog.warning("START " + name);
        myLog.info(information.toString() + logInfo);

    }


}