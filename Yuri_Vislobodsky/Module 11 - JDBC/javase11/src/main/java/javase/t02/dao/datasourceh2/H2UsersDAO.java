package javase.t02.dao.datasourceh2;

import javase.t02.dao.datasource.UsersDAO;
import javase.t02.dao.transfer.User;
import java.sql.*;

/**
 * H2 Database DAO implementation for table USERS
 * Created by Yury Vislobodsky on 07.05.2016.
 */
public class H2UsersDAO implements UsersDAO {
    private final Connection connection;
    private PreparedStatement stmt;

    public H2UsersDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int getRecordCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM USERS";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }

    @Override
    public int insert(User user) throws SQLException {
        String sql = "INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD, YEAROFBIRTH)" +
                " VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, user.getId());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getYearOfBirth());
            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(int userId) throws SQLException {
        String sql = "DELETE FROM USERS WHERE ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(User user) throws SQLException {
        String sql = "UPDATE USERS SET USERNAME = ?, EMAIL = ?, PASSWORD = ?, " +
                " YEAROFBIRTH = ? WHERE ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getYearOfBirth());
            ps.setInt(5, user.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public ResultSet selectAll() throws SQLException {
        String sql = "SELECT * FROM USERS";
        closeResultSet();
        stmt = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return stmt.executeQuery();
    }

    @Override
    public ResultSet selectByName(String userName) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE USERNAME LIKE ?";
        closeResultSet();
        stmt = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, "%" + userName + "%");
        return stmt.executeQuery();
    }

    @Override
    public void closeResultSet() throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }
}
