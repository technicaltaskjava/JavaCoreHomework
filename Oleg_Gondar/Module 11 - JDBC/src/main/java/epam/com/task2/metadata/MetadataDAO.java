package epam.com.task2.metadata;

import epam.com.task2.connectionpool.DataSource;
import org.apache.log4j.Logger;

import java.sql.*;

public class MetadataDAO {

    private static final Logger logger = Logger.getLogger(String.valueOf(MetadataDAO.class));
    private Connection connection;
    private Statement statement;

    public MetadataDTO getData(MetadataDTO metadataDTOParam) {
        MetadataDTO metadataDTO = null;
        String sql = "select * from \"Fortune cookies\".METADATA WHERE USER_ID = " + metadataDTOParam.getUserId();
        try {
            connection = DataSource.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                metadataDTO = new MetadataDTO(resultSet.getInt(1), resultSet.getInt(2), resultSet.getTimestamp(3));
            }
            resultSet.close();
            return metadataDTO;
        } catch (SQLException e) {
            log(e);
        } finally {
            DataSource.returnConnection(connection);
        }
        return metadataDTO;
    }

    public void insertData(MetadataDTO metadataDTOParam) {
        String sql = "INSERT INTO \"Fortune cookies\".METADATA (USER_ID, COOKIE_ID, TIME_ADDED)\n" +
                "VALUES \n" +
                "(?, ?, ?)";
        try {
            connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, metadataDTOParam.getUserId());
            preparedStatement.setInt(2, metadataDTOParam.getCookieId());
            preparedStatement.setTimestamp(3, metadataDTOParam.getTimestamp());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            log(e);
        } finally {
            DataSource.returnConnection(connection);
        }
    }

    public void updateData(MetadataDTO metadataDTOParam) {
        String sql = "UPDATE \"Fortune cookies\".METADATA SET COOKIE_ID = ? WHERE USER_ID = ?";
        try {
            connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, metadataDTOParam.getCookieId());
            preparedStatement.setInt(2, metadataDTOParam.getUserId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            log(e);
        } finally {
            DataSource.returnConnection(connection);
        }
    }

    public void deleteData(MetadataDTO metadataDTOParam) {
        try {
            String sql = "delete from \"Fortune cookies\".METADATA where COOKIE_ID = "
                    + metadataDTOParam.getCookieId() + " AND USER_ID = " + metadataDTOParam.getUserId();
            connection = DataSource.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            log(e);
        } finally {
            DataSource.returnConnection(connection);
        }
    }

    private static void log(Exception e) {
        org.apache.log4j.BasicConfigurator.configure();
        logger.error(e);
    }

}
