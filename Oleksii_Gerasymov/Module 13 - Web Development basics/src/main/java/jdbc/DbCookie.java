package jdbc;

import java.sql.*;
import java.util.logging.Logger;

public class DbCookie {
    private static final String SELECT_QUERY = "select cookie from cookies order by rand() limit 1";

    private DbCookie() {
    }

    public static String getRandomCookie() {
        Logger log = Logger.getLogger(DbCookie.class.getName());
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String resultCookie = "";
        try {
            Class.forName(DbProperties.getINSTANCE().getDbDriver());
            connection = DriverManager.getConnection(DbProperties.getINSTANCE().getDbUrl(), //NOSONAR
                    DbProperties.getINSTANCE().getDbUser(), //NOSONAR
                    DbProperties.getINSTANCE().getDbPassword()); //NOSONAR

            statement = connection.createStatement(); //NOSONAR
            resultSet = statement.executeQuery(SELECT_QUERY); //NOSONAR
            while (resultSet.next()) {
                resultCookie = resultSet.getString(1);
            }
        }
        catch (ClassNotFoundException | SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
            catch (SQLException databaseException) {
                log.info(String.valueOf(databaseException));
            }
        }
        return resultCookie;
    }
}
