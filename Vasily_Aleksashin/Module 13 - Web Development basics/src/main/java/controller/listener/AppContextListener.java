package controller.listener;

import dao.DaoFactory;
import dao.impl.DaoFactoryImpl;
import exception.ConfigurationLoadException;
import exception.DaoException;
import model.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(AppContextListener.class);

    private DaoFactory factory = null;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        try {
            boolean initDatabase = Boolean.parseBoolean(context.getInitParameter("init"));
            String configFile = context.getInitParameter("config");
            Configuration conf = Configuration.getInstance(context);
            conf.load(configFile);
            logger.info("Configuration uploaded successfully");
            factory = DaoFactoryImpl.getInstance();
            int availableConnectionsCount = factory.getAvailableConnectionsCount();
            logger.info(String.format("DAO factory is initialized successfully with available connections count '%s'", availableConnectionsCount));
            if (initDatabase) {
                factory.initDatabase(context);
                logger.info("Database is filled with initial data successfully");
            }
        } catch (DaoException | ConfigurationLoadException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (factory != null) {
            factory.closeConnectionPool();
            logger.info("Database connection pool closed successfully");
        }
    }
}
