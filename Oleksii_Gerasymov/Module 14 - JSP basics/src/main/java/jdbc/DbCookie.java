package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DbCookie {
    private static final String SELECT_QUERY = "select cookie from cookies where active=true order by rand() limit 1";
    private static final String SELECT_ALL = "select * from cookies order by id";
    private static final String SELECT_MAX_ID = "select max(id) from cookies";
    private static final String DELETE_COOKIE = "delete from cookies where id in (?)";
    private static final String DELETE_METADATA = "delete from metadata where cookie_id=? and user_id=?";
    private static final String ADD_COOKIE = "insert into cookies (cookie, active) values (?, ?)";
    private static final String ADD_METADATA = "insert into metadata (cookie_id, user_id, time_added) VALUES" +
            "(?, ?, NOW())";
    private static final String EDIT_COOKIE = "update cookies set cookie=?, active=? where id=?";

    private static Logger log = Logger.getLogger(DbCookie.class.getName());
    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    private DbCookie() {
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
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
    }

    public static String getRandomCookie() {
        String resultCookie = "";
        makeConnection();
        try {
            statement = connection.createStatement(); //NOSONAR
            resultSet = statement.executeQuery(SELECT_QUERY); //NOSONAR
            while (resultSet.next()) {
                resultCookie = resultSet.getString(1);
            }
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        finally {
            closeResources();
        }
        return resultCookie;
    }

    public static List<Cookie> getAllCookies() {
        ArrayList<Cookie> cookieList = new ArrayList<>();
        makeConnection();
        try {
            statement = connection.createStatement(); //NOSONAR
            resultSet = statement.executeQuery(SELECT_ALL); //NOSONAR
            while (resultSet.next()) {
                Cookie currentCookie=new Cookie();
                currentCookie.setId(resultSet.getInt(1));
                currentCookie.setCookieName(resultSet.getString(2));
                currentCookie.setActive(resultSet.getBoolean(3));
                cookieList.add(currentCookie);
            }
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        finally {
         closeResources();
        }
        return cookieList;
    }

    public static void deleteCookie(int id, int userId) {
        makeConnection();
        try {
            preparedStatement = connection.prepareStatement(DELETE_METADATA); //NOSONAR
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(DELETE_COOKIE); //NOSONAR
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        finally {
            closeResources();
        }
    }

    public static void addCookie(String cookie, String active, int userId) {
        makeConnection();
        try {
            int cookieId = 0;
            preparedStatement = connection.prepareStatement(ADD_COOKIE); //NOSONAR
            preparedStatement.setString(1, cookie);
            preparedStatement.setBoolean(2, Boolean.parseBoolean(active));
            preparedStatement.executeUpdate();

            statement = connection.createStatement(); //NOSONAR
            resultSet = statement.executeQuery(SELECT_MAX_ID); //NOSONAR
            while (resultSet.next()) {
                cookieId = resultSet.getInt(1);
            }

            preparedStatement = connection.prepareStatement(ADD_METADATA); //NOSONAR
            preparedStatement.setInt(1, cookieId);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        finally {
            closeResources();
        }
    }

    public static void editCookie(int cookieId, String cookieName, String active) {
        makeConnection();
        try {
            preparedStatement = connection.prepareStatement(EDIT_COOKIE);//NOSONAR
            preparedStatement.setString(1, cookieName);
            preparedStatement.setBoolean(2, Boolean.parseBoolean(active));
            preparedStatement.setInt(3, cookieId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        finally {
            closeResources();
        }
    }
}
