package dao.accessor;

import dao.entity.Cookie;
import dao.exeption.AddCookieException;
import dao.exeption.CookiesException;
import dao.exeption.NoSuchCookieException;
import dao.exeption.UpdateCookieException;
import dao.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Ushakov
 */
public class CookiesAccessor {
    private ConnectionPool connectionPool;
    private String whereCondition = "";

    public CookiesAccessor(ConnectionPool pool) {
        this.connectionPool = pool;
    }

    public int size() {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM Cookies")) {

            if (resultSet.next()) {
                return resultSet.getInt("COUNT(*)");
            } else {
                return 0;
            }

        } catch (SQLException e) {
            throw new CookiesException(e);
        }

    }

    public Cookie getCookieByID(int id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cookies WHERE id = ?")) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Cookie cookie = new Cookie(resultSet.getInt("id"));
                cookie.setDivination(resultSet.getString("cookie"));
                cookie.setExpirationDate(resultSet.getDate("expiration_date"));
                cookie.setPrice(resultSet.getDouble("price"));

                return cookie;
            } else {
                throw new NoSuchCookieException("No such cookie where id = " + id);
            }

        } catch (SQLException e) {
            throw new NoSuchCookieException("No such cookie where id = " + id, e);
        }
    }

    public List<Cookie> getCookieList() {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Cookies " + whereCondition)) {

            whereCondition = "";

            List<Cookie> cookieList = new ArrayList<>();

            while (resultSet.next()) {
                Cookie cookie = new Cookie(resultSet.getInt("id"));
                cookie.setDivination(resultSet.getString("cookie"));
                cookie.setExpirationDate(resultSet.getDate("expiration_date"));
                cookie.setPrice(resultSet.getDouble("price"));

                cookieList.add(cookie);
            }

            return cookieList;
        } catch (SQLException e) {
            throw new NoSuchCookieException("No cookie in the table", e);
        }
    }

    public List<Cookie> getCookieByDivination(String cookie) {
        whereCondition = "where cookie = \'" + cookie + "\'";
        return getCookieList();
    }

    public List<Cookie> getCookieByExpirationDate(Date expirationDate) {
        whereCondition = "where expiration_date = \'" + expirationDate + "\'";
        return getCookieList();
    }

    public List<Cookie> getCookieByPrice(Double price) {
        whereCondition = "where price = \'" + price + "\'";
        return getCookieList();
    }

    public void updateCookie(Cookie cookie) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE Cookies SET cookie=?, expiration_date=?, price=? where id=?")) {

            statement.setString(1, cookie.getDivination());
            statement.setDate(2, cookie.getExpirationDate());
            statement.setDouble(3, cookie.getPrice());
            statement.setInt(4, cookie.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new UpdateCookieException(e.getMessage(), e);
        }
    }

    public void addCookie(Cookie cookie) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Cookies VALUES(?, ?, ?, ?)")) {

            statement.setInt(1, cookie.getId());
            statement.setString(2, cookie.getDivination());
            statement.setDate(3, cookie.getExpirationDate());
            statement.setDouble(4, cookie.getPrice());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new AddCookieException(e.getMessage(), e);
        }
    }

    public void deleteCookie(Cookie cookie) {
        deleteCookieByID(cookie.getId());
    }

    public void deleteCookieByID(int id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Cookies WHERE id=?")
        ) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new NoSuchCookieException(e.getMessage(), e);
        }
    }

    public void deleteCookieByDivination(String divination) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Cookies WHERE cookie=?")
        ) {

            statement.setString(1, divination);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new NoSuchCookieException(e.getMessage(), e);
        }
    }

    public void close() {
        connectionPool.close();
    }
}
