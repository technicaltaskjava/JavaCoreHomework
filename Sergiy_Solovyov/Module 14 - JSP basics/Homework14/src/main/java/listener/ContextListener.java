package listener;

import daolayer.dao.factory.DAOFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Sergey Solovyov
 */
public class ContextListener implements ServletContextListener {

    public static final String DAO_FACTORY = "DAOFactory";
    private ServletContext context;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
         context = servletContextEvent.getServletContext();
         context.setAttribute(DAO_FACTORY, DAOFactory.getDAOFactory(1));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DAOFactory daoFactory = (DAOFactory)context.getAttribute(DAO_FACTORY);
        daoFactory.destroyPool();
    }
}
