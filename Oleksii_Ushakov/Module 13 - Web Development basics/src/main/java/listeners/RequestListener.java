package listeners;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

/**
 * @author augustprime
 */

@WebListener(value = "RequestListenerValue")
public class RequestListener implements ServletRequestListener, HttpSessionListener, ServletContextListener {

    private static final Logger logger = Logger.getLogger(RequestListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("User visit site");
        logger.info("User visit site");
    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest servletRequest = (HttpServletRequest) servletRequestEvent.getServletRequest();
        StringBuilder report = new StringBuilder("User download: ");
        report.append(servletRequest.getRequestURI());

        System.out.println(report);
        logger.info(report);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Server stop");
        logger.info("Server stop");
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("Created session with id " + httpSessionEvent.getSession().getId());
        logger.info("Created session with id " + httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("Destroyed session with id " + httpSessionEvent.getSession().getId());
        logger.info("Destroyed session with id " + httpSessionEvent.getSession().getId());
    }

    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        //NOP
    }
}
