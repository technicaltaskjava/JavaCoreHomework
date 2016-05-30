package javase.web.listeners;

import javase.connectionpool.ConnectionPoolException;
import javase.constants.Constants;
import javase.dao.factory.DAOFactory;
import javase.dao.factoryh2.H2DAOFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Context Listener for DAO Factory initialization
 * Created by Yury Vislobodsky on 22.05.2016.
 */
public class ContextListener implements ServletContextListener {
    private static Logger logger = LoggerFactory.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent requestEvent) {
        ServletContext context = requestEvent.getServletContext();
        context.setAttribute(Constants.DAOFACTORY, new H2DAOFactory());
        logger.info("daoFactory is initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent requestEvent) {
        ServletContext context = requestEvent.getServletContext();
        DAOFactory daoFactory = (DAOFactory)context.getAttribute(Constants.DAOFACTORY);
        try {
            daoFactory.closeFactory();
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPoolException when closing daoFactory", e);
        }
    }
}
