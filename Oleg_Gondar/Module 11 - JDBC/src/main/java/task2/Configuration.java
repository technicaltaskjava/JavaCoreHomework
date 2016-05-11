package task2;

public class Configuration {

    public String DB_USER_NAME ;

    public String DB_PASSWORD ;

    public String DB_URL;

    public String DB_DRIVER;

    public Integer DB_MAX_CONNECTIONS;

    public Configuration(){
        init();
    }

    private static Configuration configuration = new Configuration();

    public static Configuration getInstance(){
        return configuration;
    }

    private void init(){
        DB_USER_NAME = "sa";
        DB_PASSWORD = "";
        DB_URL = "jdbc:h2:~/test";
        DB_DRIVER = "org.h2.Driver";
        DB_MAX_CONNECTIONS = 5;
    }

}