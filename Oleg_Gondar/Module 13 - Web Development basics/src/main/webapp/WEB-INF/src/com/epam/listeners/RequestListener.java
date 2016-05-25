package com.epam.listeners;

import org.apache.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Oleg on 5/25/2016.
 */
public class RequestListener implements ServletRequestListener {

    static Logger logger = Logger.getLogger(RequestListener.class);

    public RequestListener() {
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        logger.info(request.getContextPath() + " was destroed.");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        logger.info(request.getContextPath() + " was crated.");
    }
}
