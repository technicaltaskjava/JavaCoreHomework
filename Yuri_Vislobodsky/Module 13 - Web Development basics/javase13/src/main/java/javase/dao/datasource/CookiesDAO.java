package javase.dao.datasource;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Abstract interface for table COOKIES
 * Created by Yury Vislobodsky on 07.05.2016.
 */
public interface CookiesDAO {
    ResultSet selectAll() throws SQLException;
    ResultSet getRandomRecord() throws SQLException;
    void closeResultSet() throws SQLException;
}