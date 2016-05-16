package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 10.05.2016.
 */
public class Configuration {

    private final String userName ;
    private final String pas ;
    private final String url;
    private final String drv;
    private final Integer connections;

    public Configuration(){
        userName = "sa";
        pas = "";
        url = "jdbc:h2:~/test";
        drv = "org.h2.Driver";
        connections = 5;
    }

    public static Configuration getInstance(){
        return new Configuration();
    }

    public String getDrv() {
        return drv;
    }

    public String getUserName() {
        return userName;
    }

    public String getPas() {
        return pas;
    }

    public String getUrl() {
        return url;
    }

    public Integer getConnections() {
        return connections;
    }
}
