package javase.dao.datasourceh2;

import javase.dao.datasource.CookiesDAO;
import javase.dao.transfer.Cookie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public ResultSet getRandomRecord() throws SQLException {
        String sql = "SELECT ID, COOKIE, MESSAGE FROM COOKIES ORDER BY RAND() LIMIT 1";
        closeResultSet();
        stmt = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return stmt.executeQuery();
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
    public void closeResultSet() throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }

    @Override
    public List<Cookie> selectByLimit(int offset, int count) throws SQLException {
        String sql = String.format("SELECT ID, COOKIE, MESSAGE FROM COOKIES ORDER BY ID LIMIT %d, %d",
                offset, count);
        closeResultSet();
        stmt = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery();
        List<Cookie> list = new ArrayList<>();
        Cookie cookie;
        while (rs.next()) {
            cookie = new Cookie();
            cookie.setId(rs.getInt(1));
            cookie.setCookie(rs.getString(2));
            cookie.setMessage(rs.getString(3));
            list.add(cookie);
        }
        rs.close();
        return list;
    }

    @Override
    public int insert(Cookie cookie) throws SQLException {
        String sql = "INSERT INTO COOKIES (COOKIE, MESSAGE)" +
                " VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cookie.getCookieName());
            ps.setString(2, cookie.getMessage());
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
        String sql = "UPDATE COOKIES SET COOKIE = ?, MESSAGE = ?" +
                " WHERE ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cookie.getCookieName());
            ps.setString(2, cookie.getMessage());
            ps.setInt(3, cookie.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
