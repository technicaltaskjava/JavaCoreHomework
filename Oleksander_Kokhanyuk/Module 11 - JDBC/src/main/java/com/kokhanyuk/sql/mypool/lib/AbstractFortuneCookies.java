package com.kokhanyuk.sql.mypool.lib;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * AbstractFortuneCookies
 * 
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public interface AbstractFortuneCookies {

    Connection findById(Connection conn, String tableName, int id, String columnName);

    Connection addData(Connection conn, String tableName, int id, String columnName, String data) throws SQLException;

    Connection removeData(Connection conn, String tableName, int id);
}
