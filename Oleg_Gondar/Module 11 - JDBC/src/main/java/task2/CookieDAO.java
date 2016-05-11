package task2;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oleg on 11.05.2016.
 */
public class CookieDAO {

    private Connection connection;
    private Statement statement;

    public Cookie getCookie(int cookie_Id) throws SQLException, ClassNotFoundException {
        Cookie cookie = null;
        String sql = "select * from \"Fortune cookies\".COOKIES WHERE COOKIE_ID = " + cookie_Id;
        connection = DataSource.getConnection();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            cookie = new Cookie(resultSet.getString(2),resultSet.getInt(1));
        }
        resultSet.close();
        return cookie;
    }

    public void insertCookie(String text, int cookie_Id) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO \"Fortune cookies\".COOKIES (COOKIE_ID, COOOKIE)\n" +
                "VALUES \n" +
                "(?, ?)";
        connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(2, text);
        preparedStatement.setInt(1, cookie_Id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void updateCookie(String text, int cookie_Id) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE \"Fortune cookies\".COOKIES SET COOOKIE = ? WHERE COOKIE_ID = ?";
        connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, text);
        preparedStatement.setInt(2, cookie_Id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteCookie(int cookie_Id) throws SQLException, ClassNotFoundException {
        String sql = "delete from \"Fortune cookies\".COOKIES where COOKIE_ID = " + cookie_Id;
        connection = DataSource.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate(sql);

    }

}
