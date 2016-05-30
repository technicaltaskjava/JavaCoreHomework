package jdbc;

import java.sql.*;
import java.util.logging.Logger;

public class DbUser {
    private static Logger log = Logger.getLogger(DbUser.class.getName());
    private static Connection connection = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;

    private DbUser() {
    }

    public static boolean checkUser(String username) {
        int resultId = 0;
        try {
            Class.forName(DbProperties.getINSTANCE().getDbDriver());
            connection = DriverManager.getConnection(DbProperties.getINSTANCE().getDbUrl(), //NOSONAR
                    DbProperties.getINSTANCE().getDbUser(), //NOSONAR
                    DbProperties.getINSTANCE().getDbPassword()); //NOSONAR

            statement = connection.prepareStatement("select id from users where username = ?"); //NOSONAR
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultId = resultSet.getInt(1);
            }
            if (resultId > 0) {
                return true;
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
        return false;
    }

    public static boolean checkPassword(String username, String password) {
        String resultPassword = null;
        try {
            Class.forName(DbProperties.getINSTANCE().getDbDriver());
            connection = DriverManager.getConnection(DbProperties.getINSTANCE().getDbUrl(), //NOSONAR
                    DbProperties.getINSTANCE().getDbUser(), //NOSONAR
                    DbProperties.getINSTANCE().getDbPassword()); //NOSONAR

            statement = connection.prepareStatement("select password from users where username = ?"); //NOSONAR
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultPassword = resultSet.getString(1);
            }
            if ((resultPassword != null) && (resultPassword.equals(password))) {
                return true;
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
        return false;
    }

    public static void addUser(String username, String email, String password, String birthdate) {
        try {
            Class.forName(DbProperties.getINSTANCE().getDbDriver());
            connection = DriverManager.getConnection(DbProperties.getINSTANCE().getDbUrl(), //NOSONAR
                    DbProperties.getINSTANCE().getDbUser(), //NOSONAR
                    DbProperties.getINSTANCE().getDbPassword()); //NOSONAR

            statement = connection.prepareStatement("insert into users (username, email, password, dateofbirth) values" +
                        "(?, ?, ?, ?);"); //NOSONAR
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setDate(4, Date.valueOf(birthdate));
            statement.executeUpdate();

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
    }
}
