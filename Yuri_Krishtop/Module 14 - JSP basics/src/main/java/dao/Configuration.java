package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Yuriy Krishtop on 20.05.2016.
 */
public class Configuration {
    private String userName ;
    private String pas ;
    private String url;
    private String drv;
    private Integer connections;

    public Configuration() throws IOException {
        Properties props = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("dbConfig.properties");
        props.load(input);
        userName = String.valueOf(props.getProperty("user"));
        pas = String.valueOf(props.getProperty("pas"));
        url = String.valueOf(props.getProperty("url"));
        drv = String.valueOf(props.getProperty("drv"));
        connections = Integer.valueOf(props.getProperty("connections"));
    }

    public static Configuration getInstance() throws IOException {
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
