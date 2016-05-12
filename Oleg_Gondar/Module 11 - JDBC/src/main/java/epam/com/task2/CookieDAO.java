package epam.com.task2;

import epam.com.task2.connectionpool.DataSource;
import org.apache.log4j.Logger;

import java.sql.*;

public class CookieDAO {

    private static final Logger logger = Logger.getLogger(String.valueOf(CookieDAO.class));
    private Connection connection;
    private Statement statement;

    public CookieDTO getData(CookieDTO cookieDTOParam) {
        CookieDTO cookieDTO = null;
        String sql = "select * from \"Fortune cookies\".COOKIES WHERE COOKIE_ID = " + cookieDTOParam.getCookieId();
        try {
            connection = DataSource.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                cookieDTO = new CookieDTO(resultSet.getString(2), resultSet.getInt(1));
            }
            resultSet.close();
            return cookieDTO;
        } catch (SQLException e) {
            log(e);
        } finally {
            DataSource.returnConnection(connection);
        }
        return cookieDTO;
    }

    public void insertData(CookieDTO cookieDTOParam) {
        String sql = "INSERT INTO \"Fortune cookies\".COOKIES (COOKIE_ID, COOOKIE)\n" +
                "VALUES \n" +
                "(?, ?)";
        try {
            connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(2, cookieDTOParam.getCookie());
            preparedStatement.setInt(1, cookieDTOParam.getCookieId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            log(e);
        } finally {
            DataSource.returnConnection(connection);
        }
    }

    public void updateData(CookieDTO cookieDTOParam) {
        String sql = "UPDATE \"Fortune cookies\".COOKIES SET COOOKIE = ? WHERE COOKIE_ID = ?";
        try {
            connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cookieDTOParam.getCookie());
            preparedStatement.setInt(2, cookieDTOParam.getCookieId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            log(e);
        } finally {
            DataSource.returnConnection(connection);
        }
    }

    public void deleteData(CookieDTO cookieDTOParam) {
        String sql = "delete from \"Fortune cookies\".COOKIES where COOKIE_ID = " + cookieDTOParam.getCookieId();
        try {
            connection = DataSource.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            log(e);
        } finally {
            DataSource.returnConnection(connection);
        }
    }

    private static void log(Exception e) {
        org.apache.log4j.BasicConfigurator.configure();
        logger.error(e);
    }

}
