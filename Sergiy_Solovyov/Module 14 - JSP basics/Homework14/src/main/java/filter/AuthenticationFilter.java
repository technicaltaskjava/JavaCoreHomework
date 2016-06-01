package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Lammi on 30.05.2016.
 */
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String nick = null;
        if (session != null)
         nick = (String) session.getAttribute("nick");

        if(nick == null){
            System.out.println("sendRedirect");
            res.sendRedirect("/login");
        }else{
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
