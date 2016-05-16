package javase.t02.dao.datasourceh2;

import javase.t02.dao.datasource.CookiesDAO;
import javase.t02.dao.transfer.Cookie;

import java.sql.*;

/**
 * H2 Database DAO implementation for table COOKIES
 * Created by Yury Vislobodsky on 07.05.2016.
 */
public class H2CookiesDAO implements CookiesDAO {
    private final Connection connection;
    private PreparedStatement stmt;

    public H2CookiesDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int getRecordCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM COOKIES";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }

    @Override
    public int insert(Cookie cookie) throws SQLException {
        String sql = "INSERT INTO COOKIES (ID, COOKIE, MESSAGE, EXPIRED)" +
                " VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cookie.getId());
            ps.setString(2, cookie.getCookieName());
            ps.setString(3, cookie.getMessage());
            ps.setDate(4, cookie.getExpired());
            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(int cookieId) throws SQLException {
        String sql = "DELETE FROM COOKIES WHERE ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cookieId);
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(Cookie cookie) throws SQLException {
        String sql = "UPDATE COOKIES SET COOKIE = ?, MESSAGE = ?," +
                "  EXPIRED = ? WHERE ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cookie.getCookieName());
            ps.setString(2, cookie.getMessage());
            ps.setDate(3, cookie.getExpired());
            ps.setInt(4, cookie.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public ResultSet selectAll() throws SQLException {
        String sql = "SELECT * FROM COOKIES";
        closeResultSet();
        stmt = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return stmt.executeQuery();
    }

    @Override
    public ResultSet selectByMessage(String message) throws SQLException {
        String sql = "SELECT * FROM COOKIES WHERE MESSAGE LIKE ?";
        closeResultSet();
        stmt = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, "%" + message + "%");
        return stmt.executeQuery();
    }

    @Override
    public void closeResultSet() throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }
}
