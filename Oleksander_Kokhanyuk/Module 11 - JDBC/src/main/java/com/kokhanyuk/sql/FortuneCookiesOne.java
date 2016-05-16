package com.kokhanyuk.sql;

import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Connection;


/**
 * FortuneCookiesOne
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class FortuneCookiesOne {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USER = "";
    private static final String DB_PASS = "";
    static Logger log = Logger.getLogger(FortuneCookiesOne.class);

    private FortuneCookiesOne() {
    }

    public static void main(String[] args) throws Exception {
        try {
            insertWithStatement();
            useWithPreparedStatement();

        } catch (SQLException e) {
            log.warn(e.getMessage(), e);
        }
    }

    private static void useWithPreparedStatement() throws SQLException {

        Connection connection = getDBConnection();
        PreparedStatement createPreparedStatement = null;
        PreparedStatement insertPreparedStatement = null;
        PreparedStatement selectPreparedStatement = null;
        PreparedStatement deletePreparedStatment = null;

        String createQuery = "create table gender(id int primary key, gender varchar(10))";
        String insertQuery = "insert into gender" + "(id, gender) values" + "(?,?)";
        String selectQuery = "select * from user where id=?";
        String deleteQuery = "drop table gender if exists";


        try {
            connection.setAutoCommit(false);

            deletePreparedStatment = connection.prepareStatement(deleteQuery);
            deletePreparedStatment.executeUpdate();
            deletePreparedStatment.close();

            createPreparedStatement = connection.prepareStatement(createQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();

            insertPreparedStatement = connection.prepareStatement(insertQuery);
            insertPreparedStatement.setInt(1, 1);
            insertPreparedStatement.setString(2, "Male");
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.setInt(1, 2);
            insertPreparedStatement.setString(2, "Female");
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();

            selectPreparedStatement = connection.prepareStatement(selectQuery);
            selectPreparedStatement.setInt(1, 6);
            ResultSet rs = selectPreparedStatement.executeQuery();
            log.info("H2 PreparedStatement");
            while (rs.next()) {
                log.info("Id " + rs.getInt("id") + " Name " + rs.getString("username"));
            }
            selectPreparedStatement.close();
            connection.commit();

            deletePreparedStatment = connection.prepareStatement(deleteQuery);
            deletePreparedStatment.executeUpdate();
            deletePreparedStatment.close();
        } catch (SQLException e) {
            log.warn(e.getMessage(), e);
        } finally {
            connection.close();
        }
    }

    private static void insertWithStatement() throws SQLException {
        Connection connection = getDBConnection();
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("INSERT INTO USER(ID, UserName, password, email, DOB) VALUES(11, 'Fesun', '783', 'yagnyukov@sd.ru', 1987)");
            stmt.execute("INSERT INTO USER(ID, UserName, password, email, DOB) VALUES(12, 'Kozlov', '2583', 'yagnyukov@sd.ru', 1987)");
            stmt.execute("INSERT INTO USER(ID, UserName, password, email, DOB) VALUES(13, 'Kramarenko', '2823', 'yagnyukov@sd.ru', 1987)");
            stmt.execute("UPDATE USER SET username='NoName' WHERE ID IN (1,3)");

            ResultSet rs = stmt.executeQuery("select * from user order by id;");
            log.info("H2 Statement");
            while (rs.next()) {
                log.info("Id " + rs.getInt("id") + " Name " + rs.getString("UserName") + " password " + rs.getString("password"));
            }
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            log.warn(e.getMessage(), e);
        } finally {
            connection.close();
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            log.warn(e.getMessage(),e);
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASS);
            return dbConnection;
        } catch (SQLException e) {
           log.warn(e.getMessage(),e);
        }
        return dbConnection;
    }
}
