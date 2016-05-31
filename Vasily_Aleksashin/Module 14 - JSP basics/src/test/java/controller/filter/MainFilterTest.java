package controller.filter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class MainFilterTest {
	@Mock
	RequestDispatcher dispatcher;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private FilterChain chain;
	@Mock
	private ServletContext context;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(request.getServletContext()).thenReturn(context);
		when(request.getRequestURI()).thenReturn("/mycookie/view/css/main.css");
		when(request.getContextPath()).thenReturn("/mycookie");
		when(context.getInitParameter("encoding")).thenReturn("utf-8");
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	}

	@Test
	public void testDoFilterRoot() throws ServletException {
		when(request.getRequestURI()).thenReturn("/mycookie/");
		when(request.getRequestDispatcher("/cookie")).thenReturn(dispatcher);
		try {
			MainFilter mainFilter = new MainFilter();
			mainFilter.setFilterUrl("/", "/cookie");
			mainFilter.doFilter(request, response, chain);
			verify(dispatcher).forward(request, response);
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}

	@Test
	public void testDoFilterOther() throws ServletException {
		try {
			new MainFilter().doFilter(request, response, chain);
			verify(chain, times(1)).doFilter(request, response);
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}

	@Test
	public void testSetRequestEncoding() throws ServletException {
		try {
			new MainFilter().doFilter(request, response, chain);
			verify(request).setCharacterEncoding(anyString());
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}

	@Test
	public void testSetResponseEncoding() throws ServletException {
		try {
			new MainFilter().doFilter(request, response, chain);
			verify(response).setCharacterEncoding(anyString());
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}
}