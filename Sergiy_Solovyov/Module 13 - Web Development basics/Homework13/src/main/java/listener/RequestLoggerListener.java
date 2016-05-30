package listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


/**
 * @author Sergey Solovyov
 */
public class RequestLoggerListener implements ServletRequestListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggerListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
      //Do nothing
    }

    @Override
    public void requestInitialized(ServletRequestEvent event) {
         HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
         StringBuilder builder = new StringBuilder("Request info:\n");

        builder.append("\nMethods: ")
                .append(request.getMethod())
                .append("\nURL: ")
                .append(request.getServletPath())
                .append("\nSession ID: ")
                .append(request.getSession().getId())
                .append("\nHeaders:\n");

       Enumeration names = request.getHeaderNames();
        while (names.hasMoreElements()){
            String header = (String) names.nextElement();
            builder.append(header + " - " + request.getHeader(header) + "\n");
        }


        LOGGER.info(builder.toString());

    }
}
