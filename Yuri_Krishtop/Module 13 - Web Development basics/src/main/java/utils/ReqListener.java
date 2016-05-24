package utils;

import org.apache.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yuriy Krishtop on 24.05.2016.
 */

@WebListener
public class ReqListener  implements ServletRequestListener {

    private static final Logger log = Logger.getLogger(ReqListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent req) {
        HttpServletRequest request = (HttpServletRequest) req.getServletRequest();
        log.info("Request from " + request.getRequestURL() + " was destroyed.");
    }

    @Override
    public void requestInitialized(ServletRequestEvent req) {
        HttpServletRequest request = (HttpServletRequest) req.getServletRequest();
        log.info("Request from " + request.getRequestURL() + " was created.");
    }

}
