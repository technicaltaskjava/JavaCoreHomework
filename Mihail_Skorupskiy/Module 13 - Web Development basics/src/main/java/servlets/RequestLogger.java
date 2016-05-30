package servlets;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestLogger implements ServletRequestListener {

    private static Logger logger = initLogger(Logger.getLogger("Request listener"));

    private static Logger initLogger(Logger logger){
        try {
            logger.addHandler(new FileHandler("logs/request-log.txt"));
        } catch (IOException e){
            logger.log(Level.WARNING, "Log file error", e);
        }
        return logger;
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest)servletRequestEvent.getServletRequest();
        if (request.getSession(false) != null){
            logger.log(Level.INFO, "Session ID = " + request.getSession().getId());
        } else {
            logger.log(Level.INFO, "No session.");
        }
        logger.log(Level.INFO, request.getRequestURL().toString());
        logger.log(Level.INFO, request.getCharacterEncoding());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        logger.log(Level.INFO, "Request destroyed");
    }
}
