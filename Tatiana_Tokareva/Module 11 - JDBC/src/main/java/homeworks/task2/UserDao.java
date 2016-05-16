package homeworks.task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDao implements DaoManager {

	User user;
	private Connection connection;

	private static final Logger log = LoggerFactory.getLogger(UserDao.class);

	public UserDao(final Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<User> selectAll(String tableName) {
		LinkedList<User> users = new LinkedList<>();

		String select = String.format("SELECT * FROM %s ORDER BY ID", tableName);
		try (PreparedStatement statement = connection.prepareStatement(select);
		     ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				users.add(new User(resultSet.getInt("id"), resultSet.getString("lastname"), resultSet.getString("firstname"),
						resultSet.getString("email"), resultSet.getString("password")));
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}

		return users;
	}

	@Override
	public int insert(User user) {
		int rows = -1;
		String insert = "INSERT INTO USERS (LASTNAME, FIRSTNAME, EMAIL, PASSWORD) VALUES (?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(insert)) {
			statement.setString(1, user.getLastName());
			statement.setString(2, user.getFirtsName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());

			rows = statement.executeUpdate();

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return rows;
	}

	@Override
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
