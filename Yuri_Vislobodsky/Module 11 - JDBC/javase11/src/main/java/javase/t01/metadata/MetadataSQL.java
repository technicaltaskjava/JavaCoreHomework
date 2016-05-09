package javase.t01.metadata;

import java.sql.*;

/**
 * Metadata class with methods of datasource handling by SQL methods
 * Created by Yury Vislobodsky on 06.05.2016.
 */
public class MetadataSQL extends MetadataBase {
    public MetadataSQL(Connection connection) {
        super(connection);
    }

    public void update(int userId, int cookiesId, boolean enabled) throws SQLException {
        String sql = "UPDATE METADATA SET COOKIES_ID = ?, ENABLED = ? WHERE USERS_ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cookiesId);
            ps.setBoolean(2, enabled);
            ps.setInt(3, userId);
            ps.executeUpdate();
        }
    }

    public void insert(int usersId, int cookiesId) throws SQLException {
        String sql = "INSERT INTO METADATA (USERS_ID, COOKIES_ID) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, usersId);
            ps.setInt(2, cookiesId);
            ps.executeUpdate();
        }
    }

    public void delete(int cookiesId) throws SQLException {
        String sql = "DELETE FROM METADATA WHERE COOKIES_ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cookiesId);
            ps.executeUpdate();
        }
    }
}
