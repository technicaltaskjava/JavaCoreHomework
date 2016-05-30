package dao.impl;

import dao.DaoFactory;
import exception.ConfigurationLoadException;
import exception.DaoException;
import model.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AbstractDaoTest { //NOSONAR This is an abstract class and it does not need a private constructor
    private static final Logger logger = LoggerFactory.getLogger(AbstractDaoTest.class);
    private static final String CONFIGURATION_TEST_PROPERTIES = "configuration_test.properties";
    private static final String DB_INITIALIZATION_SQL = "db_initialization.sql";
    static DaoFactory factory;
    static Configuration configuration;

    public static void initialize() throws DaoException {
        try {
            URL resourceProperties = ClassLoader.getSystemResource(CONFIGURATION_TEST_PROPERTIES);
            ServletContext contextMock = mock(ServletContext.class);
            when(contextMock.getResource(CONFIGURATION_TEST_PROPERTIES)).thenReturn(resourceProperties);
            URL resourceDatabase = ClassLoader.getSystemResource(DB_INITIALIZATION_SQL);
            when(contextMock.getResource(DB_INITIALIZATION_SQL)).thenReturn(resourceDatabase);
            configuration = Configuration.getInstance(contextMock);
            configuration.load("configuration_test.properties");
            factory = DaoFactoryImpl.getInstance();
            factory.initDatabase(contextMock);
        } catch (ConfigurationLoadException | MalformedURLException e) {
            logger.error(e.getMessage(), e);
            fail();
        }
    }
}
