package epam.com.task2.user;

import epam.com.task2.connectionpool.DataSource;
import org.apache.log4j.Logger;

import java.sql.*;

public class UserDAO {

    private static final Logger logger = Logger.getLogger(String.valueOf(UserDAO.class));
    private Connection connection;
    private Statement statement;

    public UserDTO getData(UserDTO userDTOParam) {
        UserDTO userDTO = null;
        String sql = "select * from \"Fortune cookies\".USERS WHERE USER_ID = " + userDTOParam.getUserId();
        try {
            connection = DataSource.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                userDTO = new UserDTO(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(3), resultSet.getString(2));
                userDTO.setUserLastName(resultSet.getString(5));
                userDTO.setUserFirstName(resultSet.getString(6));
            }
            resultSet.close();
            return userDTO;
        } catch (SQLException e) {
            log(e);
        } finally {
            DataSource.returnConnection(connection);
        }
        return userDTO;
    }

    public void insertData(UserDTO userDTOParam) {
        String sql = "INSERT INTO \"Fortune cookies\".USERS (USER_ID, USEREMAIL, USERNAME, USERPASSWORD, LASTNAME, FIRSTNAME)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?, ?, ?)";
        try {
            connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userDTOParam.getUserId());
            preparedStatement.setString(2, userDTOParam.getUserEmail());
            preparedStatement.setString(3, userDTOParam.getUserName());
            preparedStatement.setString(4, userDTOParam.getUserName());
            preparedStatement.setString(5, userDTOParam.getUserLastName());
            preparedStatement.setString(6, userDTOParam.getUserFirstName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            log(e);
        } finally {
            DataSource.returnConnection(connection);
        }
    }

    public void updateData(UserDTO userDTOParam) {
        String sql = "UPDATE \"Fortune cookies\".USERS SET LASTNAME = ? WHERE USER_ID = ?";
        try {
            connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userDTOParam.getUserLastName());
            preparedStatement.setInt(2, userDTOParam.getUserId());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            log(e);
        } finally {
            DataSource.returnConnection(connection);
        }
    }

    public void deleteData(UserDTO userDTOparam) {
        String sql = "delete from \"Fortune cookies\".USERS where USERNAME = "
                + userDTOparam.getUserName();
        try {
            connection = DataSource.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
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
