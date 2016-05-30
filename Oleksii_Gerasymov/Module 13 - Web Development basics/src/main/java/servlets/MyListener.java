package servlets;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
            ServletRequest servletRequest = servletRequestEvent.getServletRequest();
            System.out.println("ServletRequest destroyed. " + " Remote IP=" + servletRequest.getRemoteAddr());
        }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
            ServletRequest servletRequest = servletRequestEvent.getServletRequest();
            System.out.println("ServletRequest initialized. Remote IP="+servletRequest.getRemoteAddr());
        }
}
