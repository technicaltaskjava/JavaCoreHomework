package com.epam;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public interface CreateCookies {

    public static void operateDatabase(String sqlFile) {
        File currentFile = new File(sqlFile);
        Logger log = Logger.getLogger(CreateCookies.class.getName());
        Statement statement = null;
        Connection connection = null;

        try (
              BufferedReader currentFileReader = new BufferedReader(new FileReader(currentFile));
            ) {
            Class.forName(DbProperties.getINSTANCE().getDbDriver());
            connection = DriverManager.getConnection(DbProperties.getINSTANCE().getDbUrl(),
                        DbProperties.getINSTANCE().getDbUser(),
                        DbProperties.getINSTANCE().getDbPassword());
            statement = connection.createStatement();

            String currentLine = currentFileReader.readLine();
            while (currentLine != null) {
                statement.addBatch(currentLine);
                currentLine = currentFileReader.readLine();
            }
            statement.executeBatch();
        }
        catch (IOException | ClassNotFoundException | SQLException createException) {
            log.info(String.valueOf(createException));
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
            catch (NullPointerException | SQLException createException) {
                log.info(String.valueOf(createException));
            }
        }
    }
}
