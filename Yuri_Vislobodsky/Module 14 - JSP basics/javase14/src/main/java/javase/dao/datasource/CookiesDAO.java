package javase.dao.datasource;

import javase.dao.transfer.Cookie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Abstract interface for table COOKIES
 * Created by Yury Vislobodsky on 07.05.2016.
 */
public interface CookiesDAO {
    ResultSet getRandomRecord() throws SQLException;
    int getRecordCount() throws SQLException;
    void closeResultSet() throws SQLException;
    List<Cookie> selectByLimit(int offset, int count) throws SQLException;
    int insert(Cookie cookie) throws SQLException;
    int delete(int cookieId) throws SQLException;
    int update(Cookie cookie) throws SQLException;
    Connection getConnection();
}