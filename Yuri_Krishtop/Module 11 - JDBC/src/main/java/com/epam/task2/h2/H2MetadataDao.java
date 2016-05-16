package com.epam.task2.h2;

import com.epam.task2.AbstractJDBCDao;
import com.epam.task2.GenericDao;
import com.epam.task2.entities.Metadata;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yuriy Krishtop on 10.05.2016.
 */
public class H2MetadataDao extends AbstractJDBCDao<Metadata> implements GenericDao<Metadata> {

    public H2MetadataDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Metadata metadata) {
        persist(metadata);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM Metadata";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Metadata (user_id, cookie_id, time_added) VALUES (?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Metadata SET user_id= ?, cookie_id= ?, time_added= ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Metadata WHERE id= ?;";
    }

    @Override
    public List<Metadata> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<Metadata> result = new LinkedList<>();
        while (rs.next()) {
            Metadata metadata = new Metadata();
            metadata.setId(rs.getInt("id"));
            metadata.setUserId(rs.getInt("user_id"));
            metadata.setCookieId(rs.getInt("cookie_id"));
            metadata.setTimeAdded(rs.getDate("time_added"));
            result.add(metadata);
        }
        return result;
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement statement, Metadata object) throws SQLException {
        statement.setInt(1, object.getUserId());
        statement.setInt(2, object.getCookieId());
        statement.setDate(3, (Date) object.getTimeAdded());
    }

    @Override
    public void prepareStatementForUpdate(PreparedStatement statement, Metadata object) throws SQLException {
        statement.setInt(4, object.getId());
        statement.setInt(1, object.getUserId());
        statement.setInt(2, object.getCookieId());
        statement.setDate(3, (Date) object.getTimeAdded());
    }

}
