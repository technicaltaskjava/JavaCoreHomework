package javase.dao.datasourceh2;

import javase.dao.datasource.UsersDAO;
import javase.dao.transfer.User;
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
    public int insert(User user) throws SQLException {
        String sql = "INSERT INTO USERS (USERNAME, EMAIL, PASSWORD, YEAROFBIRTH)" +
                " VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getYearOfBirth());
            return ps.executeUpdate();
        }
    }

    @Override
    public User getUser(String email, String password) throws SQLException {
        String sql = "SELECT ID, USERNAME, EMAIL, PASSWORD, YEAROFBIRTH FROM USERS" +
                " WHERE UPPER(EMAIL) = UPPER(?)";
        if (password != null) {
            sql = sql + " AND PASSWORD = ?";
        }
        closeResultSet();
        stmt = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, email);
        if (password != null) {
            stmt.setString(2, password);
        }
        ResultSet rs = stmt.executeQuery();
        User user = null;
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt(1));
            user.setUserName(rs.getString(2));
            user.setEmail(rs.getString(3));
            user.setPassword(rs.getString(4));
            user.setYearOfBirth(rs.getInt(5));
        }
        return user;
    }

    @Override
    public User getUser(String email) throws SQLException {
        return getUser(email, null);
    }

    @Override
    public void closeResultSet() throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }
}
