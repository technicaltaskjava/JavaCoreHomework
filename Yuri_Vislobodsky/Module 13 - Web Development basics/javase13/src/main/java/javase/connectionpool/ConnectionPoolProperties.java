package javase.connectionpool;

import java.util.ResourceBundle;

/**
 * Class for extracting parameters of DB connection
 * Created by Yury Vislobodsky on 05.05.2016.
 */
public class ConnectionPoolProperties {
    private static final String PROPERTIES_FILE = "db";
    private static final ConnectionPoolProperties instance = new ConnectionPoolProperties();
    private ResourceBundle properties = ResourceBundle.getBundle(PROPERTIES_FILE);

    private ConnectionPoolProperties () {}

    public static ConnectionPoolProperties getInstance() {
        return instance;
    }

    public String getProperty(String key) {
        return properties.getString(key);
    }

    public int getIntegerProperty(String key, int defaultValue) {
        try {
            return Integer.parseInt(properties.getString(key));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
