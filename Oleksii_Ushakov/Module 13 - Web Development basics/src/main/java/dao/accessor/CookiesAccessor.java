package dao.accessor;

import dao.entity.FortuneCookie;
import dao.exeption.AddCookieException;
import dao.exeption.CookiesException;
import dao.exeption.NoSuchCookieException;
import dao.exeption.UpdateCookieException;
import dao.pool.ConnectionPool;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexey Ushakov
 */
public class CookiesAccessor implements AutoCloseable {
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

    public FortuneCookie getCookieByID(int id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cookies WHERE id = ?")) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                FortuneCookie fortuneCookie = new FortuneCookie(resultSet.getInt("id"));
                fortuneCookie.setPredication(resultSet.getString("predication"));
                fortuneCookie.setExpirationDate(resultSet.getDate("expiration_date"));
                fortuneCookie.setPrice(resultSet.getDouble("price"));

                return fortuneCookie;
            } else {
                throw new NoSuchCookieException("No such cookie where id = " + id);
            }

        } catch (SQLException e) {
            throw new NoSuchCookieException("No such cookie where id = " + id, e);
        }
    }

    public List<FortuneCookie> getCookieList() {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Cookies " + whereCondition)) {

            whereCondition = "";

            List<FortuneCookie> fortuneCookieList = new LinkedList<>();

            while (resultSet.next()) {
                FortuneCookie fortuneCookie = new FortuneCookie(resultSet.getInt("id"));
                fortuneCookie.setPredication(resultSet.getString("predication"));
                fortuneCookie.setExpirationDate(resultSet.getDate("expiration_date"));
                fortuneCookie.setPrice(resultSet.getDouble("price"));

                fortuneCookieList.add(fortuneCookie);
            }

            return fortuneCookieList;
        } catch (SQLException e) {
            throw new NoSuchCookieException("No cookie in the table", e);
        }
    }

    public List<FortuneCookie> getCookieByPredication(String predication) {
        whereCondition = "where predication = \'" + predication + "\'";
        return getCookieList();
    }

    public List<FortuneCookie> getCookieByExpirationDate(Date expirationDate) {
        whereCondition = "where expiration_date = \'" + expirationDate + "\'";
        return getCookieList();
    }

    public List<FortuneCookie> getCookieByPrice(Double price) {
        whereCondition = "where price = \'" + price + "\'";
        return getCookieList();
    }

    public void updateCookie(FortuneCookie fortuneCookie) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE Cookies SET predication=?, expiration_date=?, price=? where id=?")) {

            statement.setString(1, fortuneCookie.getPredication());
            statement.setDate(2, fortuneCookie.getExpirationDate());
            statement.setDouble(3, fortuneCookie.getPrice());
            statement.setInt(4, fortuneCookie.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new UpdateCookieException(e.getMessage(), e);
        }
    }

    public void addCookie(FortuneCookie fortuneCookie) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Cookies VALUES(?, ?, ?, ?)")) {

            statement.setInt(1, fortuneCookie.getId());
            statement.setString(2, fortuneCookie.getPredication());
            statement.setDate(3, fortuneCookie.getExpirationDate());
            statement.setDouble(4, fortuneCookie.getPrice());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new AddCookieException(e.getMessage(), e);
        }
    }

    public void deleteCookie(FortuneCookie fortuneCookie) {
        deleteCookieByID(fortuneCookie.getId());
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

    public void deleteCookieByPredication(String predication) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Cookies WHERE predication=?")
        ) {

            statement.setString(1, predication);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new NoSuchCookieException(e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        connectionPool.close();
    }
}
