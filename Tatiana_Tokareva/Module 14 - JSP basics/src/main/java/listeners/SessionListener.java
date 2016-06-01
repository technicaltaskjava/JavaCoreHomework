package listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Logger log = LoggerFactory.getLogger(SessionListener.class);
    private int sessionCount;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

        sessionCount++;
        log.info("Session created: ID=" + httpSessionEvent.getSession().getId());
        log.info("Total sessions: " + sessionCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.info("Session destroyed:ID=" + httpSessionEvent.getSession().getId());
        log.info("Total session destroyed: " + sessionCount);
        sessionCount--;
    }
}
