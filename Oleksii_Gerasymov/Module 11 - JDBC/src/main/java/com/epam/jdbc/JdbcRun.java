package com.epam.jdbc;

import com.epam.DbProperties;

import java.sql.*;
import java.util.logging.Logger;

public class JdbcRun {
    private static final String PREPARED_QUERY = "SELECT username, email FROM users WHERE id in (?,?)";
    private static final String UPDATE_QUERY = "UPDATE cookies SET active = true WHERE id > 2";
    private static final String CHOOSE_RECORD_QUERY = "SELECT * FROM cookies WHERE id in (5, 10)";
    private static final String INSERT_QUERY = "INSERT INTO metadata VALUES (1, 4, NOW())";
    private static final String DELETE_RECORD_QUERY = "DELETE FROM metadata WHERE cookie_id = 7";
    private static final String SELECT_QUERY = "SELECT * FROM metadata";

    private JdbcRun() {
    }

    public static void jdbcRun() {
        Logger log = Logger.getLogger(JdbcRun.class.getName());
        Connection connnection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName(DbProperties.getINSTANCE().getDbDriver());
            connnection = DriverManager.getConnection(DbProperties.getINSTANCE().getDbUrl(),
                    DbProperties.getINSTANCE().getDbUser(),
                    DbProperties.getINSTANCE().getDbPassword());

            preparedStatement = connnection.prepareStatement(PREPARED_QUERY);
            preparedStatement.setInt(1,3);
            preparedStatement.setInt(2,1);
            resultSet = preparedStatement.executeQuery();
            System.out.println("Users and emails where id is 1 or 3: ");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2));
            }

            statement = connnection.createStatement();
            statement.executeUpdate(UPDATE_QUERY);
            resultSet = statement.executeQuery(CHOOSE_RECORD_QUERY);
            System.out.println("Cookies with id 5 and 10 and active is true as a result of update: ");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2) + " | " + resultSet.getString(3));
            }

            statement.executeUpdate(INSERT_QUERY);
            statement.executeUpdate(DELETE_RECORD_QUERY);
            resultSet = statement.executeQuery(SELECT_QUERY);
            System.out.println("Table Metadata after inserting and deleting: ");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2) + " | " + resultSet.getString(3));
            }

        }
        catch (ClassNotFoundException | SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        finally {
            try {
            if (resultSet != null) {
                resultSet.close();
                }
            if (statement != null) {
                statement.close();
                }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connnection != null) {
                connnection.close();
                }
            }
            catch (SQLException databaseException) {
                log.info(String.valueOf(databaseException));
            }
        }
    }
}
