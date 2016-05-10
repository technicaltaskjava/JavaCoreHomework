package com.epam.mydao.factory;
import com.epam.mydao.connection.ConnectionProperties;
import com.epam.mydao.exeptions.DAOConfigExcp;
import java.sql.Connection;
import java.sql.SQLException;


public abstract class DAOFactory {
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASS = "password";


    public static DAOFactory getInstance(String name) {
        if (name == null) {
            throw new DAOConfigExcp("Database name is null.");
        }
        ConnectionProperties properties = new ConnectionProperties(name);
        String url = properties.getProperty(PROPERTY_URL, true);
        String password = properties.getProperty(PROPERTY_PASS, false);
        String username = properties.getProperty(PROPERTY_USERNAME, password != null);
        return new DriverManagerDAOFactory(url, username, password);
    }

    public abstract Connection getConnection() throws SQLException;


}


