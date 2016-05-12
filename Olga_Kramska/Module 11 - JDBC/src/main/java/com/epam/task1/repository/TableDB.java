package com.epam.task1.repository;

import com.epam.task1.DBConnectionManager;
import com.epam.task2.exc.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Olga Kramska on 09-May-16.
 */
public class TableDB {
    private static final Logger LOGGER = LoggerFactory.getLogger(TableDB.class);

    private TableDB() {
    }

    public static void dropTable(String tableName) {
        String query = "DROP TABLE " + tableName;
        try (Connection connection = DBConnectionManager.getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }
}
