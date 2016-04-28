package singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Sergey Solovyov
 */
public class DBInfo {

    private String port;
    private String database;
    private String user;
    private String password;
    private String driverClassName;

    private DBInfo(){
        try{
            InputStream file = new FileInputStream(new File("src/main/resources/db.properties")) ;
            Properties props = new Properties();
            props.load(file);
            port = props.getProperty("PORT");
            database = props.getProperty("DATABASE");
            user = props.getProperty("USER");
            password = props.getProperty("PASSWORD");
            driverClassName = props.getProperty("DRIVER_NAME");
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

    @Override
    public String toString() {
        return "DBInfo{" +
                "database='" + database + '\'' +
                ", port='" + port + '\'' +
                ", user='" + user + '\'' +
                ", password=" + password + '\'' +
                ", jdbc='" + driverClassName + '\'' +
                '}';
    }

}

