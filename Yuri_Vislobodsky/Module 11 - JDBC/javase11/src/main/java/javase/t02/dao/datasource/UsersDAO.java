package javase.t02.dao.datasource;

import javase.t02.dao.transfer.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Abstract interface for table USERS
 * Created by Yury Vislobodsky on 07.05.2016.
 */
public interface UsersDAO {
    public int insert(User user) throws SQLException;
    public int delete(int userId) throws SQLException;
    public int update(User user) throws SQLException;
    public int getRecordCount() throws SQLException;
    public ResultSet selectAll() throws SQLException;
    public ResultSet selectByName(String userName) throws SQLException;
    public void closeResultSet() throws SQLException;
}
