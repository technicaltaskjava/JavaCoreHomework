package com.epam.task1.repository;

import com.epam.task1.DBConnectionManager;
import com.epam.task2.exc.DataAccessException;

import java.sql.Statement;

/**
 * Created by Olga Kramska on 09-May-16.
 */
public class TableDB {

    private TableDB() {
    }

    public static void dropTable(String tableName) {
        String query = "DROP TABLE " + tableName;
        try (Statement statement = DBConnectionManager.getConnection().createStatement()) {
            statement.executeUpdate(query);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
