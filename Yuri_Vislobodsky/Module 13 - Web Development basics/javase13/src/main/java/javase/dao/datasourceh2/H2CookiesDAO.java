package javase.dao.datasourceh2;

import javase.dao.datasource.CookiesDAO;
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
    public ResultSet selectAll() throws SQLException {
        String sql = "SELECT * FROM COOKIES";
        closeResultSet();
        stmt = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return stmt.executeQuery();
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
    public void closeResultSet() throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }
}
