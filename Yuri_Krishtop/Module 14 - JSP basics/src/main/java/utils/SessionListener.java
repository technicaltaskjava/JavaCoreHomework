package utils;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Yuriy Krishtop on 24.05.2016.
 */

@WebListener
public class SessionListener implements HttpSessionListener {

    private static final Logger log = Logger.getLogger(ReqListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        log.info("Session Created:: ID="+sessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        log.info("Session Destroyed:: ID="+sessionEvent.getSession().getId());
    }

}
