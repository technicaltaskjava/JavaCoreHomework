package javase.t02.dao.datasourceh2;

import javase.t02.dao.datasource.MetadataDAO;
import javase.t02.dao.transfer.Metadata;
import java.sql.*;

/**
 * H2 Database DAO implementation for table METADATA
 * Created by Yury Vislobodsky on 07.05.2016.
 */
public class H2MetadataDAO implements MetadataDAO {
    private final Connection connection;
    private PreparedStatement stmt;

    public H2MetadataDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int getRecordCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM METADATA";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }

    @Override
    public int insert(Metadata metadata) throws SQLException {
        String sql = "INSERT INTO METADATA (ID, USERS_ID, COOKIES_ID, TIME_ADDED, ENABLED)" +
                " VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, metadata.getId());
            ps.setInt(2, metadata.getUsersId());
            ps.setInt(3, metadata.getCookiesId());
            ps.setTimestamp(4, metadata.getTimeAdded());
            ps.setBoolean(5, metadata.isEnabled());
            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(int metadataId) throws SQLException {
        String sql = "DELETE FROM METADATA WHERE ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, metadataId);
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(Metadata metadata) throws SQLException {
        String sql = "UPDATE METADATA SET USERS_ID = ?, COOKIES_ID = ?, TIME_ADDED = ?, " +
                " ENABLED = ? WHERE ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, metadata.getUsersId());
            ps.setInt(2, metadata.getCookiesId());
            ps.setTimestamp(3, metadata.getTimeAdded());
            ps.setBoolean(4, metadata.isEnabled());
            ps.setInt(5, metadata.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public ResultSet selectAll() throws SQLException {
        String sql = "SELECT * FROM METADATA";
        closeResultSet();
        stmt = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return stmt.executeQuery();
    }

    @Override
    public ResultSet selectByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM METADATA WHERE USERS_ID = ?";
        closeResultSet();
        stmt = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setInt(1, userId);
        return stmt.executeQuery();
    }

    @Override
    public void closeResultSet() throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }
}
