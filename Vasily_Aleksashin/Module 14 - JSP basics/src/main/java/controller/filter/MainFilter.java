package controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@WebFilter("/*")
public class MainFilter implements Filter {
	private final Map<String, String> urlFilter = new HashMap<>();

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		urlFilter.put("/", "/cookie");
		urlFilter.put("/view/html/login", "/login");
		urlFilter.put("/view/html/registration", "/registration");
		urlFilter.put("/userlogout", "/logout");
		urlFilter.put("/form_admin", "/service/cookie");
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

	void setFilterUrl(final String key, final String value) {
		urlFilter.put(key, value);
	}

	private RequestDispatcher getRequestDispatcher(final HttpServletRequest req) {
		String contextPath = req.getContextPath();
		String requestURI = req.getRequestURI();
		String targetUrl = requestURI.substring(contextPath.length());
		if (urlFilter.containsKey(targetUrl)) {
			return req.getRequestDispatcher(urlFilter.get(targetUrl));
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
