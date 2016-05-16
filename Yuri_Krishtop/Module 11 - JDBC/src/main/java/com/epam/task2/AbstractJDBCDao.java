package com.epam.task2;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yuriy Krishtop on 09.05.2016.
 */
public abstract class AbstractJDBCDao<T extends Idable> implements GenericDao<T> {

    private Connection connection;
    private static final Logger log = Logger.getLogger(AbstractJDBCDao.class);

    public AbstractJDBCDao(Connection connection) {
        this.connection = connection;
    }

    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;

    @Override
    public void persist(T object) {
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            statement.executeUpdate();

        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public T getById(int id) {
        List<T> list = null;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            log.error(e);
        }
        if (list == null || list.isEmpty()) {
            throw new NullPointerException("Record with id = " + id + " not found.");
        }
        return list.iterator().next();
    }

    @Override
    public void update(T object) {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, object);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void delete(T object) {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, object.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public List<T> getAll() {
        List<T> list = null;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            log.error(e);
        }
        return list;
    }

}