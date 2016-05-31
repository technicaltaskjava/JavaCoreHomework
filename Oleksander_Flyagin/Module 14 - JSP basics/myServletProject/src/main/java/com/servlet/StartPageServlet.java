package com.servlet;




import com.data.cookie.Cookie;
import com.data.mydao.base.BaseContact;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;


public class StartPageServlet extends HttpServlet {
    private List cookies;
    private String prediction;



    public void createPrediction(List<Cookie> cookies) {
        Random random = new Random();
        int id = random.nextInt(cookies.size());
        prediction = cookies.get(id).getPrediction();



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();


        cookies = (List)((BaseContact)(servletContext.getAttribute("baseContact"))).getCookies();
        createPrediction(cookies);
        resp.setContentType("text/html");
        String info = "start \"" + this.getServletName() +  "\" show  prediction" + prediction;
        req.setAttribute("info",info);


        resp.getWriter().println("<!DOCTYPE html>\n" +
                "<html id=\"background\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Fortune Cookie</title>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"/css/style.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<header id=\"head\">\n" +
                "    <img src=\"/img/MyFortuneCookie.png\" alt=\"MyFortuneCookie\">\n" +
                "</header>\n" +
                "<div class=\"main\">\n" +
                "    <h3>Virtual Fortune Cookie</h3>\n" +
                "    <p class=\"simplyClick\">\n" +
                "        To open up your virtual fortune cookie, simply click the button below:\n" +
                "    </p>\n" +
                "    <div class=\"massage\"></div>\n" +
                "    <form action=\"StartPageServlet\" class=\"cookies\" method=\"get\">\n" +
                "        <input type=\"submit\" id=\"show\" name=\"button\" value=\"Open my cookie\"/>\n" +
                "    <p class=\"simplyClick\">\n" +
                prediction +
                "    </p>\n" +
                "    </form>\n" +
                "    <p class=\"line\"></p>\n" +
                "    <img src=\"img/cookie.png\" alt=\"Foodzia Fortune Cookie\" class=\"img\">\n" +
                "    <span class=\"sms\" >Enter to your account</span>\n" +
                "    <img src=\"img/cookie.png\" alt=\"Foodzia Fortune Cookie\" class=\"img\">\n" +
                " <form  action=\"home.html\" method=\"post\">\n" +
                "        <input id=\"btnEnter\" type=\"submit\" value=\"HOME\"/>\n" +
                "    </form>" +
                "</div>\n" +
                "</body>\n" +
                "</html>");


    }
}

