package dao.accessor;

import dao.entity.Metadata;
import dao.exeption.*;
import dao.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Ushakov
 */
public class MetadataAccessor implements AutoCloseable {
    private ConnectionPool connectionPool;
    private String whereCondition = "";

    public MetadataAccessor(ConnectionPool pool) {
        this.connectionPool = pool;
    }

    public int size() {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM Metadata")) {

            if (resultSet.next()) {
                return resultSet.getInt("COUNT(*)");
            } else {
                return 0;
            }

        } catch (SQLException e) {
            throw new MetadataException(e);
        }

    }

    public List<Metadata> getMetadataList() {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Metadata " + whereCondition)) {

            whereCondition = "";

            List<Metadata> metadataList = new ArrayList<>();

            while (resultSet.next()) {
                Metadata metadata = new Metadata(resultSet.getInt("cookie_id"), resultSet.getInt("user_id"));
                metadata.setTimeAdded(resultSet.getDate("time_added"));

                metadataList.add(metadata);
            }

            return metadataList;
        } catch (SQLException e) {
            throw new NoSuchMetadataException("No metadata in the table", e);
        }
    }

    public List<Metadata> getMetadataByCookieID(int cookieID) {
        whereCondition = "where cookie_id = \'" + cookieID + "\'";
        return getMetadataList();
    }

    public List<Metadata> getMetadataByUserID(int userID) {
        whereCondition = "where user_id = \'" + userID + "\'";
        return getMetadataList();
    }

    public List<Metadata> getMetadataByTimeAdded(String timeAdded) {
        whereCondition = "where time_added = \'" + timeAdded + "\'";
        return getMetadataList();
    }

    public void addMetadata(Metadata metadata) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Metadata VALUES(?, ?, ?)")) {

            statement.setInt(1, metadata.getCookieID());
            statement.setInt(2, metadata.getUserID());
            statement.setDate(3, metadata.getTimeAdded());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new AddMetadataException("Can`t update metadata with id [" + metadata.getCookieID() + ":" + metadata.getUserID() + "]", e);
        }
    }

    public void deleteMetadata(Metadata metadata) {
        deleteMetadata(metadata.getCookieID(), metadata.getUserID());
    }

    public void deleteMetadata(int cookieID, int userID) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Metadata WHERE cookie_id=? AND user_id=?")
        ) {

            statement.setInt(1, cookieID);
            statement.setInt(2, userID);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new NoSuchUserException("No such metadata in the table", e);
        }
    }

    @Override
    public void close() {
        connectionPool.close();
    }
}
