package utils;

import org.apache.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

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
        HttpServletRequest servletRequest = (HttpServletRequest) req.getServletRequest();
                StringBuilder logBuilder = new StringBuilder(servletRequest.getRequestURL().toString());
                logBuilder.append("; request method ").append(servletRequest.getMethod());
                Map<String, String[]> parameterMap = servletRequest.getParameterMap();
                if (!parameterMap.isEmpty()) {
                        logBuilder.append("; params: ");
                        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                                logBuilder.append(" key = ").append(entry.getKey());
                                logBuilder.append(" value = ").append(Arrays.asList(entry.getValue()));
                            }
                    }
        log.info(logBuilder);
    }

}
