package com.kokhanyuk.servlets.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */

public class RequestListener  implements ServletRequestListener {

    private static Logger log = Logger.getLogger("Listener");

    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        log.info("Request from " + request.getContextPath() + " was destroyed.");
    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        log.info("Request from " + request.getContextPath() + "  was created.");
    }
}
