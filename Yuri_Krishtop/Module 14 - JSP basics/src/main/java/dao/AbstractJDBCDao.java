package dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yuriy Krishtop on 20.05.2016.
 */
public abstract class AbstractJDBCDao<T extends IdAble> implements GenericDao<T> {

    protected Connection connection;
    private static final Logger log = Logger.getLogger(AbstractJDBCDao.class);

    public AbstractJDBCDao(Connection connection) {
        this.connection = connection;
    }

    public abstract String getSelectQuery();
    public abstract String getDeleteQuery();
    public abstract String getTableName();
    public abstract String getInsertQuery();


    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;

    @Override
    public void insert(T object) {
        String sql = getInsertQuery();
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
    public void deleteById(int id) {
        String sql = getDeleteQuery();
        sql += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
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

    @Override
    public int getCountElements() {
        String sql = "SELECT COUNT(id) FROM ";
        sql += getTableName();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            log.error(e);
            return 0;
        }
    }


}
