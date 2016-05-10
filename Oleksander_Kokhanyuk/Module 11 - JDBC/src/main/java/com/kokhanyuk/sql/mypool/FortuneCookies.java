package com.kokhanyuk.sql.mypool;

import com.kokhanyuk.sql.mypool.lib.AbstractFortuneCookies;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * FortuneCookies
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class FortuneCookies implements AbstractFortuneCookies {

    static Logger log = Logger.getLogger(FortuneCookies.class);

    @Override
    public Connection findById(Connection conn, String tableName, int id, String columnName) {
        PreparedStatement selectPreparedStatement = null;
        String selectQuery = "select * from " + tableName + " where id=?";
        try {
            conn.setAutoCommit(false);
            selectPreparedStatement = conn.prepareStatement(selectQuery);//NOSONAR
            selectPreparedStatement.setInt(1, id);
            ResultSet rs = selectPreparedStatement.executeQuery();
            while (rs.next()) {
                log.info("Id: " + rs.getInt("id") + " " + columnName + ": " + rs.getString(columnName));
            }
            selectPreparedStatement.close();
            conn.commit();
        } catch (SQLException e) {
            log.warn(e.getMessage(), e);
        }
        return conn;
    }

    @Override
    public Connection addData(Connection conn, String tableName, int id, String columnName, String data) {
        PreparedStatement insertPreparedStatement = null;
        String insertQuery = "insert into " + tableName + "(id," + columnName + ") values" + "(?,?)";
        try {
            conn.setAutoCommit(false);
            insertPreparedStatement = conn.prepareStatement(insertQuery);
            insertPreparedStatement.setInt(1, id);
            insertPreparedStatement.setString(2, data);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
        } catch (SQLException e) {
            log.warn(e.getMessage(), e);
        }
        log.info("data to table " + tableName + " id=" + id + " is added");
        return conn;
    }

    @Override
    public Connection removeData(Connection conn, String tableName, int id) {
        PreparedStatement removePreparedStatement = null;
        String removeQuery = "delete from " + tableName + " where id=?";
        try {
            conn.setAutoCommit(false);
            removePreparedStatement = conn.prepareStatement(removeQuery);
            removePreparedStatement.setInt(1, id);
            removePreparedStatement.executeUpdate();
            removePreparedStatement.close();
        } catch (SQLException e) {
            log.warn(e.getMessage(), e);
        }
        log.info("data from table " + tableName + " id=" + id + " is removed");
        return conn;
    }


}