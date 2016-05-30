package com.kokhanyuk.servlets.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class CharsetFilter implements Filter {

    private String encoding;
    private ServletContext context;

    public void destroy() {
        throw new UnsupportedOperationException();
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        response.setContentType("text/html");
        context.log("Charset was set.");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        encoding = fConfig.getInitParameter("characterEncoding");
        context = fConfig.getServletContext();
    }
}
