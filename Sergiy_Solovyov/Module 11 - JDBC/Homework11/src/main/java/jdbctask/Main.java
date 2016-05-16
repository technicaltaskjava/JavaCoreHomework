package jdbctask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import daotask.pool.ConnectionPool;

import java.sql.*;

/**
 * @author Sergey Solovyov
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private Main(){}

    public static void main(String[] args) throws SQLException {

        try (ConnectionPool connectionPool = new ConnectionPool(5);){

            Connection conn = connectionPool.retrieve();
            PreparedStatementOperations pso = new PreparedStatementOperations(conn);
            pso.printUsers("Init table:");

            System.out.println("Select by age: \n");
            pso.selectByAge(50, 99);

            System.out.println("Select by ID: \n");
            pso.selectById(7);

            pso.updateNameById(1, "TEST1");
            pso.updateNameById(2, "TEST2");
            pso.updateNameById(3, "TEST3");
            pso.updateNameById(4, "TEST4");
            pso.updateNameById(5, "TEST5");
            pso.printUsers("After updating NAMES: ");

            pso.insertUser("INSERT", "INSERT@INSERT.INSERT", "INSERT", 55);
            pso.printUsers("After insertion: ");

            ResultSetOperations rso = new ResultSetOperations(conn);
            rso.updateWithResultSet();
            pso.printUsers("After updating (AGE + 3): ");

            rso.insertWithResultSet("RSINSERT", "RSINSERT@RSINSERT.RSINSERT", "RSINSERT", 33);
            pso.printUsers("After ResultSet insertion: ");

            pso.printMetadata("Init table : ");

            pso.deleteMetadataById(1);
            pso.printMetadata("After  deleting(ID 1): ");

            rso.deleteMetadataWithResultSet(2);
            pso.printMetadata("After ResultSet deleting(record 2): ");

    } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
    }
}




}


