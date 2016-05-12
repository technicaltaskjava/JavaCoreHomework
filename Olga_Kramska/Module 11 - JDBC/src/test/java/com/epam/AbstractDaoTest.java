package com.epam;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
/**
 * Created by Olga Kramska on 10-May-16.
 */

public abstract class AbstractDaoTest {

    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String SCHEMA_FILE = "h2.sql";
    private static final String DATA_SET = "set.xml";

    @BeforeClass
    public static void createSchema() throws Exception {
        Class.forName(JDBC_DRIVER);
        try (Connection conn = dataSource().getConnection();
             InputStreamReader in = new InputStreamReader(
                     Thread.currentThread().getContextClassLoader().getResourceAsStream(SCHEMA_FILE))) {
            RunScript.execute(conn, in);
        }
    }

    @Before
    public void loadTestData() throws Exception {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(DATA_SET);
        IDataSet ids = new FlatXmlDataSetBuilder().build(in);
        IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(ids);
        databaseTester.onSetup();
    }

    protected static DataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(JDBC_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }
}
