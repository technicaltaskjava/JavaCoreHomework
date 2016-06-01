package com.epam.listeners;

import org.apache.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by Oleg on 5/25/2016.
 */
public class RequestListener implements ServletRequestListener {

    static Logger logger = Logger.getLogger(RequestListener.class);

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        logger.info(request.getRequestURI() + " was created.");
        Enumeration headerNames = request.getHeaderNames();
        logger.info(request.getRequestURI() + " headers.");
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            logger.info("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
        }
        logger.info(request.getRequestURI() + " end of request headers.");
        logger.info(request.getRequestURI() + " params.");
        Enumeration params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = (String) params.nextElement();
            logger.info("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
        }
        logger.info(request.getRequestURI() + " end of request params.");

    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        logger.info(request.getRequestURI() + " was destroyed.");
    }
}
