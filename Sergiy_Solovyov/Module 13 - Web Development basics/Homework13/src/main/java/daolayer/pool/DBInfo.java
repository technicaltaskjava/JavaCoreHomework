package daolayer.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;

/**
 * @author Sergey Solovyov
 */
public class DBInfo {

    private String driver;
    private String url;
    private String login;
    private String password;
    private static final Logger LOGGER = LoggerFactory.getLogger(DBInfo.class);

    private DBInfo(){
        try{
            Properties props = new Properties();
            props.load(DBInfo.class.getClassLoader().getResourceAsStream("../classes/db.properties"));
            driver = props.getProperty("DRIVER");
            url = props.getProperty("DB_URL");
            login = props.getProperty("LOGIN");
            password = props.getProperty("PASSWORD");
        }
        catch(Exception e){
            LOGGER.info(e.getMessage(), e);
        }
    }

    private static class SingletonHelper{
        private static final DBInfo INSTANCE = new DBInfo();
        private SingletonHelper(){}
    }

    public static DBInfo getInstance(){
        return SingletonHelper.INSTANCE;
    }

    public String getDriver() {
        return driver;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }
}
