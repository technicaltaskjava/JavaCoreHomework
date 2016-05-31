package com.epam.util;

import com.epam.exc.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by Olga Kramska on 30-May-16.
 */
public class ConnectionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);
    private static final String DATASOURCE_CONTEXT = "java:comp/env/jdbc/H2";

    private DataSource dataSource;

    private ConnectionManager() {
        init();
    }

    private void init() {
        try {
            Context initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
        } catch (NamingException ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new DataAccessException(ex.getMessage());
        }
    }

    private static class ConnectionManagerHolder {
        public static final ConnectionManager INSTANCE = new ConnectionManager();

        private ConnectionManagerHolder() {
        }
    }

    public static ConnectionManager getInstance() {
        return ConnectionManagerHolder.INSTANCE;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
