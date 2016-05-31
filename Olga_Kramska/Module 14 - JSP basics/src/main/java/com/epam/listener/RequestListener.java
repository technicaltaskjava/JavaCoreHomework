package com.epam.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Olga Kramska on 23-May-16.
 */
@WebListener
public class RequestListener implements ServletRequestListener {
    private static final Logger logger = Logger.getLogger(RequestListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest servletRequest = (HttpServletRequest) servletRequestEvent.getServletRequest();
        logger.info(servletRequest.getRequestURL().toString());
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest servletRequest = (HttpServletRequest) servletRequestEvent.getServletRequest();
        StringBuilder logBuilder = new StringBuilder(servletRequest.getRequestURL().toString());
        logBuilder.append("; request method ").append(servletRequest.getMethod());
        Map<String, String[]> parameterMap = servletRequest.getParameterMap();
        if (!parameterMap.isEmpty()) {
            logBuilder.append("; params: ");
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                logBuilder.append("key = ").append(entry.getKey());
                logBuilder.append(" value = ").append(Arrays.asList(entry.getValue()));
            }
        }
        logger.info(logBuilder);
    }
}
