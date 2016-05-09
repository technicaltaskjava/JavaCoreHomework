package javase.t02.dao.datasource;

import javase.t02.dao.transfer.Cookie;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Abstract interface for table COOKIES
 * Created by Yury Vislobodsky on 07.05.2016.
 */
public interface CookiesDAO {
    public int insert(Cookie cookie) throws SQLException;
    public int delete(int cookieId) throws SQLException;
    public int update(Cookie cookie) throws SQLException;
    public int getRecordCount() throws SQLException;
    public ResultSet selectAll() throws SQLException;
    public ResultSet selectByMessage(String message) throws SQLException;
    public void closeResultSet() throws SQLException;
}