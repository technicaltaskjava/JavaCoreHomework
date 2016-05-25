package dao;

import dao.substance.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDao {


    private Connection connection;

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    public UserDao(final Connection connection) {
        this.connection = connection;
    }


    public List<User> selectAll(String tableName) {
        LinkedList<User> users = new LinkedList<>();

        String select = String.format("SELECT * FROM %s ORDER BY id", tableName);
        try (PreparedStatement statement = connection.prepareStatement(select);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = takeUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }

        return users;
    }


    public User selectEmail(String email) {

        User user = null;
        String select = String.format("SELECT * FROM USERS WHERE EMAIL ='%s'", email);
        try (PreparedStatement statement = connection.prepareStatement(select);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                user = takeUser(resultSet);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }

        return user;
    }

    private User takeUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLastName(resultSet.getString("lastname"));
        user.setFirtsName(resultSet.getString("firstname"));
        user.setEmail(resultSet.getString("email"));
        user.setYearBirth(resultSet.getString("YEAROFBIRTH"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }


    public int insert(User user) {
        int rows = -1;
        String insert = "INSERT INTO USERS (LASTNAME, FIRSTNAME, EMAIL, YEAROFBIRTH, PASSWORD) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insert)) {
            statement.setString(1, user.getLastName());
            statement.setString(2, user.getFirtsName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getYearBirth());
            statement.setString(5, user.getPassword());

            rows = statement.executeUpdate();

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return rows;
    }


    public int delete() {
        int rows = -1;

        String field = "DELETE from USERS WHERE ID >=?";
        try (PreparedStatement statement = connection.prepareStatement(field)) {
            statement.setInt(1, 34);
            rows = statement.executeUpdate();
            System.out.println("Delete count = " + rows);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return rows;
    }


}
