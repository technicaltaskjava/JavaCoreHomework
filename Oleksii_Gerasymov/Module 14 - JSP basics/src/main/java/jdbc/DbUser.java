package jdbc;

import java.sql.*;
import java.util.logging.Logger;

public class DbUser {
    private static Logger log = Logger.getLogger(DbUser.class.getName());
    private static Connection connection = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;

    private static final String SELECT_ID = "select id from users where username = ?";
    private static final String SELECT_PASS = "select password from users where username = ?";
    private static final String ADD_USER = "insert into users (username, email, password, dateofbirth) values (?, ?, ?, ?);";

    private DbUser() {
    }

    public static void makeConnection() {
        try {
            Class.forName(DbProperties.getINSTANCE().getDbDriver());
            connection = DriverManager.getConnection(DbProperties.getINSTANCE().getDbUrl(),
                    DbProperties.getINSTANCE().getDbUser(),
                    DbProperties.getINSTANCE().getDbPassword());
        }
        catch (ClassNotFoundException | SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
    }

    public static void closeResources() {
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

    public static int getUserId(String username) {
        makeConnection();
        try {
            statement = connection.prepareStatement(SELECT_ID); //NOSONAR
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        finally {
            closeResources();
        }
        return 0;
    }

    public static boolean checkUser(String username) {
        int resultId = 0;
        makeConnection();
        try {
            statement = connection.prepareStatement(SELECT_ID); //NOSONAR
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultId = resultSet.getInt(1);
            }
            if (resultId > 0) {
                return true;
            }
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        finally {
            closeResources();
        }
        return false;
    }

    public static boolean checkPassword(String username, String password) {
        String resultPassword = null;
        makeConnection();
        try {
            statement = connection.prepareStatement(SELECT_PASS); //NOSONAR
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultPassword = resultSet.getString(1);
            }
            if ((resultPassword != null) && (resultPassword.equals(password))) {
                return true;
            }
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        finally {
            closeResources();
        }
        return false;
    }

    public static void addUser(String username, String email, String password, String birthdate) {
        makeConnection();
        try {
            statement = connection.prepareStatement(ADD_USER); //NOSONAR
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setDate(4, Date.valueOf(birthdate));
            statement.executeUpdate();
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        finally {
            closeResources();
        }
    }
}
