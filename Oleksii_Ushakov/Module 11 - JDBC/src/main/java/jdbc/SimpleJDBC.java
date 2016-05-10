package jdbc;

import java.io.PrintStream;
import java.sql.*;
import java.util.Random;

/**
 * @author Alexey Ushakov
 */
public class SimpleJDBC {
    private static final PrintStream out = System.out;//NOSONAR

    private static final String INSERT_QUERY = "INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (?, ?, CURRENT_DATE);";
    private static final String UPDATE_QUERY = "UPDATE Cookies SET expiration_date = CURRENT_DATE+2 WHERE id = ?";
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS Temp (id INT IDENTITY(1,1));";
    private static final String SELECT_DEFINITE_QUERY = "SELECT * FROM Cookies WHERE id = 3";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Cookies";
    private static final String DELETE_TABLE_QUERY = "DROP TABLE Temp;";

    private SimpleJDBC() {
    }

    public static void selectAll(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {

            while (resultSet.next()) {
                out.println("ID " + resultSet.getInt("id"));
                out.println(" " + resultSet.getString("cookie"));
                out.println(" " + resultSet.getDate("expiration_date"));
            }
        }
    }

    public static void update(Connection connection, int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, id);
            int changedLines = statement.executeUpdate();
            out.println("On update changed " + changedLines + " lines");
        }
    }

    public static void selectDefinite(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_DEFINITE_QUERY)) {

            while (resultSet.next()) {
                out.println("ID " + resultSet.getInt("id"));
                out.println(" " + resultSet.getString("cookie"));
                out.println(" " + resultSet.getDate("expiration_date"));
            }
        }
    }

    public static void insert(Connection connection, int cookieID, int userID) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setInt(1, cookieID);
            statement.setInt(2, userID);

            int changedLines = statement.executeUpdate();

            out.println("On insert add " + changedLines + " lines");
        }
    }

    public static void deleteTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate(CREATE_TABLE_QUERY);
            out.println("Create table 'Temp'");

            statement.executeUpdate(DELETE_TABLE_QUERY);
            out.println("Delete table 'Temp'");
        }
    }

    public static void main(String[] args) throws SQLException {
        Random random = new Random();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")) {
            selectAll(connection);
            update(connection, 3);
            selectDefinite(connection);
            insert(connection, 1 + random.nextInt(10), 1 + random.nextInt(10));
            deleteTable(connection);
        }
    }

}
