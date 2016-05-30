package javase.web.listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Request listener to log request and response events
 * Created by Yury Vislobodsky on 22.05.2016.
 */
public class RequestListener implements ServletRequestListener {
    private static Logger logger = LoggerFactory.getLogger(RequestListener.class);

    @Override
    public void requestInitialized(ServletRequestEvent requestEvent) {
        HttpServletRequest request = (HttpServletRequest)requestEvent.getServletRequest();
        logger.info(String.format("Request from %s was created (URI = %s)",
                request.getRemoteHost(), request.getRequestURI()));
    }

    @Override
    public void requestDestroyed(ServletRequestEvent requestEvent) {
        HttpServletRequest request = (HttpServletRequest)requestEvent.getServletRequest();
        logger.info(String.format("Request from %s was destroyed (URI = %s)",
                request.getRemoteHost(), request.getRequestURI()));
    }
}
