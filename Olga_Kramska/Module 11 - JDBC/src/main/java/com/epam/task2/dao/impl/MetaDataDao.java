package com.epam.task2.dao.impl;

import com.epam.model.MetaData;
import com.epam.task2.dao.RepositoryDao;
import com.epam.task2.exc.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Kramska on 10-May-16.
 */
public class MetaDataDao implements RepositoryDao<MetaData, Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaDataDao.class);

    private static final String INSERT_QUERY = "INSERT INTO MetaData (USER_ID, COOKIE_ID, TIME_ADDED) VALUES (?, ?, NOW())";
    private static final String SELECT_QUERY = "SELECT * FROM MetaData";
    private static final String SELECT_QUERY_BY_ID = "SELECT * FROM MetaData WHERE ID = ?";
    private static final String UPDATE_QUERY = "UPDATE MetaData SET USER_ID = ?, COOKIE_ID = ? WHERE ID = ?";
    private static final String DELETE_QUERY = "DELETE FROM MetaData WHERE ID = ?";

    private Connection connection;

    public MetaDataDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(MetaData metaData) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, metaData.getUserId());
            preparedStatement.setInt(2, metaData.getCookieId());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    metaData.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public MetaData get(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new MetaData(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getDate(4));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
        return null;
    }

    @Override
    public List<MetaData> getAll() {
        List<MetaData> metaDatas = new ArrayList<>();
        try (Statement preparedStatement = connection.createStatement();
             ResultSet resultSet = preparedStatement.executeQuery(SELECT_QUERY)) {
            while (resultSet.next()) {
                metaDatas.add(new MetaData(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getDate(4)));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
        return metaDatas;
    }

    @Override
    public void update(MetaData metaData) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setInt(1, metaData.getUserId());
            preparedStatement.setInt(2, metaData.getCookieId());
            preparedStatement.setInt(3, metaData.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }
}
