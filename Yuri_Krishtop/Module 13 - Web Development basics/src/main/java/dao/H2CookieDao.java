package dao;

import entities.Cookie;

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

    public H2CookieDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, cookie FROM Cookies";
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

}
