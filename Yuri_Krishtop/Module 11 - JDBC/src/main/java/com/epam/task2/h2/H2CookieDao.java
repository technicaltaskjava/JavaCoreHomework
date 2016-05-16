package com.epam.task2.h2;

import com.epam.task2.AbstractJDBCDao;
import com.epam.task2.GenericDao;
import com.epam.task2.entities.Cookie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yuriy Krishtop on 09.05.2016.
 */
public class H2CookieDao extends AbstractJDBCDao<Cookie> implements GenericDao<Cookie> {

    public H2CookieDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Cookies (cookie) VALUES (?);";
    }

    @Override
    public void create(Cookie cookie) {
        persist(cookie);
    }


    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Cookies WHERE id= ?;";
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

    @Override
    public String getSelectQuery() {
        return "SELECT id, cookie FROM Cookies";
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement statement, Cookie object) throws SQLException {
        statement.setString(1, object.getPrediction());
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Cookies SET cookie= ? WHERE id= ?;";
    }

    @Override
    public void prepareStatementForUpdate(PreparedStatement statement, Cookie object) throws SQLException {
        statement.setInt(2, object.getId());
        statement.setString(1, object.getPrediction());
    }

}