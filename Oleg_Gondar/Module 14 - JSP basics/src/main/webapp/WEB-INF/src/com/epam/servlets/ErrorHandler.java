package com.epam.servlets;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ErrorHandler")
public class ErrorHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String LI = "</li>";
    static Logger logger = Logger.getLogger(ErrorHandler.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            processError(request, response);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            processError(request, response);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    private void processError(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {

        Throwable throwable = (Throwable) request
                .getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request
                .getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) request
                .getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }


        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.write("<html><head><title>Exception/Error Details</title></head><body>");
        if (statusCode != 500) {
            out.write("<h3>Error Details</h3>");
            out.write("<strong>Status Code</strong>:" + statusCode + "<br>");
            out.write("<strong>Requested URI</strong>:" + requestUri);
        } else {
            out.write("<h3>Exception Details</h3>");
            out.write("<ul><li>Servlet Name:" + servletName + LI);
            out.write("<li>Exception Name:" + throwable.getClass().getName() + LI);
            out.write("<li>Requested URI:" + requestUri + LI);
            out.write("<li>Exception Message:" + throwable.getMessage() + LI);
            out.write("</ul>");
        }

        out.write("<br><br>");
        out.write("<a href=\"index.html\">Login Page</a>");
        out.write("</body></html>");
    }
}