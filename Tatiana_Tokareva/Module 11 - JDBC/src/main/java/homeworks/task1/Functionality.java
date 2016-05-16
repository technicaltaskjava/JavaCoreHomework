package homeworks.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class Functionality {

	private static final Logger log = LoggerFactory.getLogger(Functionality.class);
	private Connection connection;



	public Functionality(final Connection connection) {
		this.connection = connection;

	}



	public void selectAll(String tableName) {
		String select = String.format("SELECT * FROM %s ORDER BY ID", tableName);
		try (PreparedStatement statement = connection.prepareStatement(select);
		     ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("ID") + " " + resultSet.getString("lastname") + " " +
						resultSet.getString("firstname") + " " + resultSet.getString("email") + " " + resultSet.getString("password") + " " +
						resultSet.getInt("YEAROFBIRTH"));
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void selectSpecificInformation() {
		String field = "SELECT FIRSTNAME, EMAIL FROM USERS WHERE FIRSTNAME LIKE 'T%'";
		try (PreparedStatement statement = connection.prepareStatement(field);
		     ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				System.out.println(resultSet.getString("FIRSTNAME") + " " + resultSet.getString("EMAIL"));
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	public int insert() {
		int rows = -1;
		String insert = "INSERT INTO USERS (LASTNAME, FIRSTNAME, EMAIL, PASSWORD) VALUES (?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(insert)) {
			statement.setString(1, "Yurash");
			statement.setString(2, "Yurik");
			statement.setString(3, "misterY@mail.com");
			statement.setString(4, "853254dsf");

			rows = statement.executeUpdate();
			System.out.println("Insert count = " + rows);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return rows;
	}


	public void update() {
		String update = "UPDATE USERS SET YEAROFBIRTH=? WHERE FIRSTNAME=?";
		try (PreparedStatement statement = connection.prepareStatement(update)) {
			statement.setInt(1, 1988);
			statement.setString(2, "Yurik");
			int rows = statement.executeUpdate();
			System.out.println("Update count = " + rows);

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}

	}

	public int delete() {
		String field = "DELETE from USERS WHERE ID >=?";

		int rows = -1;
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
