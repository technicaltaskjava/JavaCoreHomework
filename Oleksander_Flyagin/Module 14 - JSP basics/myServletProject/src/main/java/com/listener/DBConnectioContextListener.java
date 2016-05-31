package com.listener;
import com.data.mydao.base.BaseContact;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;


@WebListener
public class DBConnectioContextListener implements ServletContextListener{
    Logger logger = Logger.getLogger("");
    BaseContact baseContact;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        try {
            Class.forName("org.h2.Driver");

        } catch (ClassNotFoundException e) {
           logger.warning(e.getMessage());
        }
        logger.info("DB Connection to server");
        servletContext.setAttribute("baseContact",new BaseContact());


    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        logger.info("DB DisConnect");

    }
}
