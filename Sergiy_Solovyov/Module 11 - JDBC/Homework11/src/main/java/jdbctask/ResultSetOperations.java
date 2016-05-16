package jdbctask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static jdbctask.Constant.*;

/**
 * @author Sergey Solovyov
 */
public class ResultSetOperations {

    private Connection connection;
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultSetOperations.class);

    public ResultSetOperations(Connection connection) {
        this.connection = connection;
    }

    public void updateWithResultSet(){

        try (Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);ResultSet rs = statement.executeQuery(SELECT_ALL_USERS)){

            rs.beforeFirst();

            while(rs.next()){
                int newAge = rs.getInt(AGE) + 3;
                rs.updateInt(AGE, newAge );
                rs.updateRow();
            }

        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }


    }
    public void insertWithResultSet(String userName, String email, String password, int age)  {

        try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE); ResultSet rs = statement.executeQuery(SELECT_ALL_USERS);) {

            rs.beforeFirst();
            rs.moveToInsertRow();
            rs.updateString(USERNAME , userName);
            rs.updateString(EMAIL, email);
            rs.updateString(PASSW, password);
            rs.updateInt(AGE, age);
            rs.insertRow();

        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }
    public void deleteMetadataWithResultSet(int recordNumber)  {

        try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE); ResultSet rs = statement.executeQuery(SELECT_ALL_METADATA);) {

            rs.absolute(recordNumber);
            rs.deleteRow();

        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }
}
