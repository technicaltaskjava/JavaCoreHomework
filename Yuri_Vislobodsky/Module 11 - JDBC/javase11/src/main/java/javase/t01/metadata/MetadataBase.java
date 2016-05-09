package javase.t01.metadata;

import javase.common.viewresultset.ViewResultSet;
import java.sql.*;

/**
 * Metadata base class with common methods of datasource handling
 * Created by Yury Vislobodsky on 06.05.2016.
 */
public abstract class MetadataBase {
    protected final Connection connection;

    public MetadataBase(Connection connection) {
        this.connection = connection;
    }

    public void dropTable() throws SQLException {
        executeSQL("DROP TABLE IF EXISTS METADATA");
    }

    public void createTable() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.addBatch("CREATE TABLE METADATA (" +
                    "ID INT NOT NULL AUTO_INCREMENT," +
                    "USERS_ID INT," +
                    "COOKIES_ID INT," +
                    "TIME_ADDED TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                    "ENABLED BOOL DEFAULT TRUE" +
                    ")");
            stmt.addBatch("ALTER TABLE METADATA ADD CONSTRAINT PK_METADATA PRIMARY KEY (ID)");
            stmt.addBatch("ALTER TABLE METADATA ADD CONSTRAINT FK_METADATA_1 " +
                    "FOREIGN KEY (USERS_ID) REFERENCES USERS (ID)");
            stmt.addBatch("ALTER TABLE METADATA ADD CONSTRAINT FK_METADATA_2 " +
                    "FOREIGN KEY (COOKIES_ID) REFERENCES COOKIES (ID)");
            stmt.addBatch("CREATE INDEX METADATA_IDX1 ON METADATA (TIME_ADDED)");
            stmt.executeBatch();
        }
    }

    public void viewTable() throws SQLException {
        viewSQL("select * from METADATA");
    }

    public void viewSQL(String sql) throws SQLException {
        try (Statement stmt = connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery(sql)) {
            ViewResultSet.view(rs);
        }
    }

    private void executeSQL(String sql) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    public abstract void update(int userId, int cookiesId, boolean enabled) throws SQLException;

    public abstract void insert(int usersId, int cookiesId) throws SQLException;

    public abstract void delete(int cookiesId) throws SQLException;
}
