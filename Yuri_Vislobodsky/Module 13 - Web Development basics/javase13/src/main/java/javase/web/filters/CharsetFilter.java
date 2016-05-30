package javase.web.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import java.io.IOException;

/**
 * Charset Filter to set charset encoding for servlets requests and responses
 * Created by Yury Vislobodsky on 22.05.2016.
 */
public class CharsetFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(CharsetFilter.class);
    private String charset;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        charset = filterConfig.getInitParameter("default_charset");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding(charset);
        response.setCharacterEncoding(charset);
        logger.info(String.format("Charset %s is set", charset));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // this method is empty for complete implementation
    }
}
