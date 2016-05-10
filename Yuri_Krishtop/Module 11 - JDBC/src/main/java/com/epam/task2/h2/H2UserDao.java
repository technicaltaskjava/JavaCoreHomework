package com.epam.task2.h2;

import com.epam.task2.AbstractJDBCDao;
import com.epam.task2.GenericDao;
import com.epam.task2.entities.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yuriy Krishtop on 10.05.2016.
 */
public class H2UserDao extends AbstractJDBCDao<User> implements GenericDao<User> {

    public H2UserDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(User user) {
        persist(user);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM Users";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Users (login, name, surname, email, pass, DOB, regDate, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Users SET login = ?, name = ?, surname = ?, email = ?, pass = ?, DOB = ?, regDate = ?, phone = ?" +
                " WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Users WHERE id= ?;";
    }

    @Override
    public List<User> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<User> result = new LinkedList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            user.setPass(rs.getString("pass"));
            user.setPhone(rs.getString("phone"));
            user.setDob(rs.getDate("DOB"));
            user.setRegDate(rs.getDate("regDate"));
            result.add(user);
        }
        return result;
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement statement, User object) throws SQLException {
        statement.setString(1, object.getLogin());
        statement.setString(2, object.getName());
        statement.setString(3, object.getSurname());
        statement.setString(4, object.getEmail());
        statement.setString(5, object.getPass());
        statement.setDate(6, (Date) object.getDob());
        statement.setDate(7, (Date) object.getRegDate());
        statement.setString(8, object.getPhone());
    }

    @Override
    public void prepareStatementForUpdate(PreparedStatement statement, User object) throws SQLException {
        statement.setInt(9, object.getId());
        statement.setString(1, object.getLogin());
        statement.setString(2, object.getName());
        statement.setString(3, object.getSurname());
        statement.setString(4, object.getEmail());
        statement.setString(5, object.getPass());
        statement.setDate(6, (Date) object.getDob());
        statement.setDate(7, (Date) object.getRegDate());
        statement.setString(8, object.getPhone());
    }

}
