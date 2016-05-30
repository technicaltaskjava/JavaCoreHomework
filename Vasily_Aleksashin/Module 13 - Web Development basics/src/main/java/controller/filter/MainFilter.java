package controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebFilter(urlPatterns = {"/*"})
public class MainFilter implements Filter {
    private static final String ROOT = "/mycookie/";
    private static final String VIEW_HTML_LOGIN = "/mycookie/view/html/login";
    private static final String VIEW_HTML_REGISTRATION = "/mycookie/view/html/registration";
    private static final String USER_LOGOUT = "/mycookie/userlogout";

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        // Do nothing
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        setEncoding(req, resp);
        RequestDispatcher requestDispatcher = getRequestDispatcher(req);
        if (requestDispatcher != null) {
            requestDispatcher.forward(req, resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
        // Do nothing
    }

    private RequestDispatcher getRequestDispatcher(final HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        if (requestURI.equals(ROOT)) {
            return req.getRequestDispatcher("/cookie");
        }
        if (requestURI.equals(VIEW_HTML_LOGIN)) {
            return req.getRequestDispatcher("/login");
        }
        if (requestURI.equals(USER_LOGOUT)) {
            return req.getRequestDispatcher("/logout");
        }
        if (requestURI.equals(VIEW_HTML_REGISTRATION)) {
            return req.getRequestDispatcher("/registration");
        }
        return null;
    }

    private void setEncoding(final HttpServletRequest req, final HttpServletResponse resp) throws UnsupportedEncodingException {
        ServletContext context = req.getServletContext();
        String encoding = context.getInitParameter("encoding");
        req.setCharacterEncoding(encoding);
        resp.setCharacterEncoding(encoding);
    }
}
