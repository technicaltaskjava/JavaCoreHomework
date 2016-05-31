package controller.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebListener
public class LoggerListener implements ServletRequestListener {
	private static final Logger logger = LoggerFactory.getLogger(LoggerListener.class);

	@Override
	public void requestDestroyed(final ServletRequestEvent event) {
		// Do nothing
	}

	@Override
	public void requestInitialized(final ServletRequestEvent event) {
		HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
		Map<String, String[]> parameterMap = request.getParameterMap();
		StringBuilder builder = new StringBuilder("Request details:");
		builder.append("\n\tMethods: ")
				.append(request.getMethod())
				.append("\n\tURL: ")
				.append(request.getServletPath())
				.append("\n\tParameters: ")
				.append(getParameterMap(parameterMap))
				.append("'\n\tRemote User: '");
		String remoteUser = null;
		Cookie[] requestCookies = request.getCookies();
		if (requestCookies != null) {
			List<Cookie> cookies = Arrays.asList(requestCookies);
			for (Cookie cookie : cookies) {
				if ("name".equals(cookie.getName())) {
					remoteUser = cookie.getValue();
				}
			}
		}
		if (remoteUser != null) {
			builder.append(remoteUser)
					.append("'\n\tSessionID=")
					.append(request.getSession().getId());
		} else {
			builder.append("unauthorized user'");
		}
		logger.info(builder.toString());
	}

	private String getParameterMap(final Map<String, String[]> parameterMap) {
		if (parameterMap.isEmpty()) {
			return "{}";
		}
		Set<String> paramKeys = parameterMap.keySet();
		StringBuilder builder = new StringBuilder("{");
		for (String key : paramKeys) {
			builder.append(key).append("=")
					.append(Arrays.asList(parameterMap.get(key)))
					.append(", ");
		}
		builder.delete(builder.length() - 2, builder.length() - 1);
		builder.append("}");

		return builder.toString();
	}
}
