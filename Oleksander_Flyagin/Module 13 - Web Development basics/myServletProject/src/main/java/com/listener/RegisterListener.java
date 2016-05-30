package com.listener;


import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.*;



@WebListener
public class RegisterListener implements ServletRequestListener {
    Logger logger = Logger.getLogger("");


    public RegisterListener() {

    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();

        logger.info( "Cross-over to" + request.getRequestURI() + " page");




    }

    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();

        if(request.getAttribute("info")!=null) {
            logger.info( "Sho info :" + request.getAttribute("info"));

        }
        logger.info( "Request from " + request.getMethod() + " was destroyed.");


    }


}
