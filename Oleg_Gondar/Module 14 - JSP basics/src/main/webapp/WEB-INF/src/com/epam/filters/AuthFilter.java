package com.epam.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Oleg on 24.05.2016.
 */
@WebFilter("/AuthenticationFilter")
public class AuthFilter implements Filter {

    private Logger logger = Logger.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        //
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        logger.info("Requested Resource::" + uri);

        HttpSession session = req.getSession(false);

        if (session == null && (uri.endsWith("GetCookiesPage") || uri.endsWith("GetCookies") || uri.endsWith("tableOfCookies.jsp") || uri.endsWith("editCookie.jsp"))) {
            logger.error("Unauthorized access request");
            res.sendRedirect("index.html");
        } else {

            chain.doFilter(request, response);
        }


    }

    @Override
    public void destroy() {
        //
    }

}
