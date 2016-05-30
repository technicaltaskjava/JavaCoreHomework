package filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * @author Sergey Solovyov
 */
public class CharsetFilter implements Filter {

    private String encoding;
    private String contentType;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("characterEncoding");
        contentType = filterConfig.getInitParameter("contentType");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if( encoding == null )
            encoding = "UTF-8";

        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);

        if( contentType == null )
            contentType = "text/html";

        response.setContentType(contentType);

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //Do nothing
    }
}
