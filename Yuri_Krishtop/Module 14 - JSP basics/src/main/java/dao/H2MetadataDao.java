package dao;

import entities.Metadata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yuriy Krishtop on 01.06.2016.
 */
public class H2MetadataDao extends AbstractJDBCDao<Metadata> implements GenericDao<Metadata> {

    public H2MetadataDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, cookie FROM Metadata";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Metadata ";
    }

    @Override
    public String getTableName() {
        return "Metadata";
    }

    @Override
    public String getInsertQuery() {
            return "INSERT INTO Metadata (user_id, cookie_id, time_added) VALUES (?, ?, CURRENT_TIMESTAMP);";
    }

    @Override
    protected List<Metadata> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<Metadata> result = new LinkedList<>();
        while (rs.next()) {
            Metadata metadata = new Metadata();
            metadata.setId(rs.getInt("id"));
            metadata.setCookieId(rs.getInt("cookie_id"));
            metadata.setCookieId(rs.getInt("user_id"));
            metadata.setTimeAdded(rs.getTimestamp("cookie"));
            result.add(metadata);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Metadata object) throws SQLException {
        statement.setInt(1, object.getUserId());
        statement.setInt(2, object.getCookieId());
    }

}
