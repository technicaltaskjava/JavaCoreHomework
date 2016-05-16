package javase.t01.metadata;

import java.sql.*;

/**
 * Metadata class with methods of datasource handling by ResultSet methods
 * Created by Yury Vislobodsky on 06.05.2016.
 */
public class MetadataRS extends MetadataBase {
    public MetadataRS(Connection connection) {
        super(connection);
    }

    public void update(int userId, int cookiesId, boolean enabled) throws SQLException {
        String sql = "SELECT ID, COOKIES_ID, ENABLED FROM METADATA WHERE USERS_ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_UPDATABLE)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rs.updateInt(2, cookiesId);
                rs.updateBoolean(3, enabled);
                rs.updateRow();
            }
        }
    }

    public void insert(int usersId, int cookiesId) throws SQLException {
        String sql = "SELECT ID, USERS_ID, COOKIES_ID FROM METADATA";
        try (Statement stmt = connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(sql)) {
            rs.moveToInsertRow();
            rs.updateInt(2, usersId);
            rs.updateInt(3, cookiesId);
            rs.insertRow();
            rs.beforeFirst();
        }
    }

    public void delete(int cookiesId) throws SQLException {
        String sql = "SELECT ID FROM METADATA WHERE COOKIES_ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_UPDATABLE)) {
            ps.setInt(1, cookiesId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rs.deleteRow();
            }
        }
    }
}
