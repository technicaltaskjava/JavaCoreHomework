package dao;

import entities.Cookie;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yuriy Krishtop on 20.05.2016.
 */
public class H2CookieDao extends AbstractJDBCDao<Cookie> implements GenericDao<Cookie> {

    private static final Logger log = Logger.getLogger(H2CookieDao.class);

    public H2CookieDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, cookie FROM Cookies";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Cookies ";
    }

    @Override
    public String getTableName() {
        return "Cookies";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO Cookies (cookie) VALUES (?);";
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement statement, Cookie object) throws SQLException {
        statement.setString(1, object.getPrediction());
    }

    public String getUpdateQuery() {
        return "UPDATE Cookies SET cookie= ? WHERE id= ?;";
    }

    public void prepareStatementForUpdate(PreparedStatement statement, Cookie object) throws SQLException {
        statement.setInt(2, object.getId());
        statement.setString(1, object.getPrediction());
    }

    public void update(Cookie cookie) {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, cookie);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public List<Cookie> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<Cookie> result = new LinkedList<>();
        while (rs.next()) {
            Cookie cookie = new Cookie();
            cookie.setId(rs.getInt("id"));
            cookie.setPrediction(rs.getString("cookie"));
            result.add(cookie);
        }
        return result;
    }

    public int getIdLastCookie() {
        String sql = "SELECT * FROM Cookies ORDER BY id DESC LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getInt(1));
            return resultSet.getInt(1);
        } catch (SQLException e) {
            log.error(e);
            return 0;
        }
    }

}
