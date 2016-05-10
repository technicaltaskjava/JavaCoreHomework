package com.epam.task1;

import com.epam.model.User;
import com.epam.task1.repository.UserDBRepository;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by Olga Kramska on 06-May-16.
 */
public class UserDBRepositoryTest {

    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String SCHEMA_FILE = "h2.sql";
    private static final String DATASET = "set.xml";

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
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(DATASET);
        IDataSet ids = new FlatXmlDataSetBuilder().build(in);
        IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(ids);
        databaseTester.onSetup();
    }

    @Test
    public void testGetUser() throws SQLException {
        UserDBRepository userRepository = new UserDBRepository(dataSource().getConnection());
        User user = userRepository.getUser(1);
        assertNotNull(user);
        assertEquals("John", user.getUsername());
    }

    @Test
    public void testUpdateUser() throws SQLException {
        final String name = "Joe";
        UserDBRepository userRepository = new UserDBRepository(dataSource().getConnection());
        User user = userRepository.getUser(1);
        user.setUsername(name);
        userRepository.updateUser(user);
        assertEquals(name, userRepository.getUser(1).getUsername());
    }

    private static DataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(JDBC_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }
}
