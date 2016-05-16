package com.epam;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class DbProperties {
    private static final DbProperties INSTANCE = new DbProperties();
    private static final String DB_PROPERTIES = "src/main/resources/db.properties";
    private static Logger log = Logger.getLogger(DbProperties.class.getName());
    private String dbDriver;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private int dbPoolSize;

    private DbProperties() {
        FileInputStream propertiesInputStream;
        Properties currentProperties = new Properties();
        try {
            propertiesInputStream = new FileInputStream(DB_PROPERTIES);
            currentProperties.load(propertiesInputStream);

            this.dbDriver = currentProperties.getProperty("db.driver");
            this.dbUrl = currentProperties.getProperty("db.url");
            this.dbUser = currentProperties.getProperty("db.user");
            this.dbPassword = currentProperties.getProperty("db.password");
            this.dbPoolSize = Integer.parseInt(currentProperties.getProperty("db.poolsize"));
        }
        catch (IOException propertiesException) {
            log.info(String.valueOf(propertiesException));
        }
    }

    public static DbProperties getINSTANCE() {
        return INSTANCE;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public int getDbPoolSize() {
        return dbPoolSize;
    }
}
