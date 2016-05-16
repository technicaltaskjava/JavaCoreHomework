package jdbctask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import static jdbctask.Constant.*;

/**
 * @author Sergey Solovyov
 */
public class PreparedStatementOperations {

    private Connection connection;
    private static final Logger LOGGER = LoggerFactory.getLogger(PreparedStatementOperations.class);

    public PreparedStatementOperations(Connection connection) {
        this.connection = connection;
    }

    public  void selectById(int id) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID);){

            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                System.out.println("userid : " + resultSet.getInt(ID));
                System.out.println("username : " +resultSet.getString(USERNAME));
                System.out.println("email : " +resultSet.getString(EMAIL));
                System.out.println("age : " +resultSet.getString(AGE));
            }
            System.out.println(SEPARATOR);
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    public void selectByAge(int fromAge, int toAge) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_AGE);){

            ps.setInt(1, fromAge);
            ps.setInt(2, toAge);
            ResultSet resultSet = ps.executeQuery();
            printUserResultSet(resultSet);
            System.out.println(SEPARATOR);
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    public void updateNameById(int id, String name) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);){

            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Updating...");
            System.out.println(SEPARATOR);
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    public void insertUser(String userName, String email, String password, int age) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER);){

            ps.setString(1, userName);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setInt(4, age);
            ps.executeUpdate();
            System.out.println(SEPARATOR);
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    public void deleteMetadataById(int id) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(DELETE_METADATA_BY_ID);){

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }

    }
    public static void printUserResultSet(ResultSet resultSet) {

        try {
            System.out.println("ID |  USERNAME |   EMAIL  |    AGE\n");
            while (resultSet.next()) {

                System.out.print(resultSet.getInt(ID) + " | ");
                System.out.print(resultSet.getString(USERNAME) + " | ");
                System.out.print(resultSet.getString(EMAIL) + " | ");
                System.out.print(resultSet.getString(AGE) + " | \n");
            }
            System.out.println(SEPARATOR);

        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }
    public static void printMetadataResultSet(ResultSet resultSet) {

        try {
            System.out.println("ID |  COOKIE_ID |   USER_ID  |    TIME_ADDED\n");
            while (resultSet.next()) {

                System.out.print(resultSet.getInt(ID) + " | ");
                System.out.print(resultSet.getInt(COOKIE_ID) + " | ");
                System.out.print(resultSet.getInt(USER_ID) + " | ");
                System.out.print(resultSet.getString(TIME_ADDED) + " | \n");
            }
            System.out.println(SEPARATOR);

        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    public  void printUsers(String message) throws SQLException {
        System.out.println(message + "\n");

        try (Statement st = connection.createStatement();
             ResultSet resultSet = st.executeQuery(SELECT_ALL_USERS)){
            PreparedStatementOperations.printUserResultSet(resultSet);
            System.out.println(SEPARATOR);
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    public  void printMetadata(String message) throws SQLException {
        System.out.println(message + "\n");

        try (Statement st = connection.createStatement();
             ResultSet resultSet = st.executeQuery(SELECT_ALL_METADATA)){
            PreparedStatementOperations.printMetadataResultSet(resultSet);
            System.out.println(SEPARATOR);
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

}
