package com.epam.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;


/**
 * Created by Oleg on 24.05.2016.
 */
public class CharserFilter implements Filter {

    private String encoding;
    private ServletContext servletContext;

    static Logger logger = Logger.getLogger(CharserFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("characterEncoding");
        servletContext = filterConfig.getServletContext();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        logger.info("Charset was set");
        chain.doFilter(request, response);
    }

    public void destroy() {
    }


}
