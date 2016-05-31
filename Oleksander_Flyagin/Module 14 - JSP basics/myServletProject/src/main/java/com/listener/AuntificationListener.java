package com.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.logging.Logger;


@WebListener
public class AuntificationListener implements HttpSessionListener {
    Logger logger = Logger.getLogger("");

    public void sessionCreated(HttpSessionEvent sessionEvent) {
    	logger.info("Session Created:: ID="+sessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        logger.info("Session Destroyed:: ID="+sessionEvent.getSession().getId());
    }
	
}

