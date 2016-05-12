package com.epam.task1.repository;

import com.epam.task2.exc.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Olga Kramska on 09-May-16.
 */
public class MetaDataDBRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaDataDBRepository.class);

    private static final String INSERT_QUERY = "INSERT INTO MetaData VALUES (?, ?, NOW())";
    private static final String DELETE_QUERY = "DELETE FROM MetaData WHERE (COOKIE_ID, USER_ID)  = (?, ?)";

    private Connection connection;

    public MetaDataDBRepository(Connection connection) {
        this.connection = connection;
    }

    public void addMetaData(int cookieId, int userId) {
        careWithIds(INSERT_QUERY, cookieId, userId);
    }

    public void deleteMetaData(int cookieId, int userId) {
        careWithIds(DELETE_QUERY, cookieId, userId);
    }

    private void careWithIds(String query, int cookieId, int userId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, cookieId);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }
}
