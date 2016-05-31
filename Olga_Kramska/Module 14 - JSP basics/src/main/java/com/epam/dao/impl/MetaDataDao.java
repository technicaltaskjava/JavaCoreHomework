package com.epam.dao.impl;

import com.epam.dao.AbstractMetaDataDao;
import com.epam.exc.DataAccessException;
import com.epam.model.MetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Olga Kramska on 10-May-16.
 */
public class MetaDataDao implements AbstractMetaDataDao<MetaData, Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaDataDao.class);

    private static final String INSERT_QUERY = "INSERT INTO MetaData (USER_ID, COOKIE_ID, TIME_ADDED) VALUES (?, ?, NOW())";
    private static final String SELECT_QUERY = "SELECT * FROM MetaData";
    private static final String SELECT_QUERY_BY_ID = "SELECT * FROM MetaData WHERE ID = ?";
    private static final String SELECT_QUERY_BY_USER_ID = "SELECT * FROM MetaData WHERE USER_ID = ?";
    private static final String SELECT_QUERY_BY_COOKIE_ID = "SELECT * FROM MetaData WHERE COOKIE_ID = ?";
    private static final String UPDATE_QUERY = "UPDATE MetaData SET USER_ID = ?, COOKIE_ID = ? WHERE ID = ?";
    private static final String DELETE_QUERY = "DELETE FROM MetaData WHERE ID = ?";

    private DataSource source;

    public MetaDataDao(DataSource source) {
        this.source = source;
    }

    @Override
    public void add(MetaData metaData) {
        try (PreparedStatement preparedStatement = source.getConnection().
                prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
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
        try (PreparedStatement preparedStatement = source.getConnection().prepareStatement(SELECT_QUERY_BY_ID)) {
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
        try (Statement preparedStatement = source.getConnection().createStatement();
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
        try (PreparedStatement preparedStatement = source.getConnection().prepareStatement(UPDATE_QUERY)) {
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
        try (PreparedStatement preparedStatement = source.getConnection().prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public List<MetaData> getByUser(int userId) {
        List<MetaData> metaDatas = new ArrayList<>();
        try (PreparedStatement preparedStatement = source.getConnection().prepareStatement(SELECT_QUERY_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    metaDatas.add(new MetaData(resultSet.getInt(1), resultSet.getInt(2),
                                    resultSet.getInt(3), new Date(resultSet.getTimestamp(4).getTime())));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
        return metaDatas;
    }

    @Override
    public List<MetaData> getByCookie(int cookieId) {
        List<MetaData> metaDatas = new ArrayList<>();
        try (PreparedStatement preparedStatement = source.getConnection().prepareStatement(SELECT_QUERY_BY_COOKIE_ID)) {
            preparedStatement.setInt(1, cookieId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    metaDatas.add(new MetaData(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getDate(4)));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
        return metaDatas;
    }
}
