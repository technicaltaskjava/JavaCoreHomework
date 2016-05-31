package jdbc;

public class DbProperties {
    private static final DbProperties INSTANCE = new DbProperties();
    private String dbDriver;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    private DbProperties() {
        this.dbDriver = "org.h2.Driver";
        this.dbUrl = "jdbc:h2:~/test";
        this.dbUser = "sa";
        this.dbPassword = ""; //NOSONAR
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
}
