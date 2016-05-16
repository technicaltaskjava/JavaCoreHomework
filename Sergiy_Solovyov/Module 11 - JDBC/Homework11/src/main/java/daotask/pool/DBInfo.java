package daotask.pool;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Sergey Solovyov
 */
public class DBInfo {

    private String driver;
    private String url;
    private String login;
    private String password;


    private DBInfo(){
        try{
            InputStream file = new FileInputStream(new File("src/main/resources/db.properties")) ;
            Properties props = new Properties();
            props.load(file);
            driver = props.getProperty("DRIVER");
            url = props.getProperty("DB_URL");
            login = props.getProperty("LOGIN");
            password = props.getProperty("PASSWORD");
        }
       catch(Exception e){
            System.out.println("error" + e);
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

