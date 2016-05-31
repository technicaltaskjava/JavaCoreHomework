package listeners;


import dao.ConnectionFactory;
import dao.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(ContextListener.class);
    Factory factory;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext context = servletContextEvent.getServletContext();

        String url = context.getInitParameter("dbURL");
        String user = context.getInitParameter("dbUser");
        String dbPassword = context.getInitParameter("dbPassword");
        factory = new ConnectionFactory(url, user, dbPassword);
        context.setAttribute("DBManager", factory);
//        try {
//            Connection connection = factory.getConnection();

//            log.info("Database connection initialized");
//        } catch (InterruptedException e) {
//            log.error(e.getMessage(), e);
//        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        factory.closePool();
        log.info("connection close");

    }
}
