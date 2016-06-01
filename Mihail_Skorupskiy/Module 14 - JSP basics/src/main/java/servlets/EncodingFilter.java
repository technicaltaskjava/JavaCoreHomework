package servlets;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //nothing to see here
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String encoding = servletRequest.getCharacterEncoding();
        if (encoding == null) {
            encoding = "UTF-8";
            servletRequest.setCharacterEncoding(encoding);
        }
        servletResponse.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        //nothing to see here either
    }
}
