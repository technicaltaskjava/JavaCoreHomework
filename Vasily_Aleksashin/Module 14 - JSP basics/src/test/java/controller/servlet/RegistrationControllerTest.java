package controller.servlet;

import dao.impl.AbstractDaoTest;
import exception.DaoException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class RegistrationControllerTest {

	private static final String USER = "User";
	private static final String MAIL = "user@mail.com";
	private static final String PASS = "ff9830c42660c1dd1942844f8069b74a";
	private static final String AGE = "2";

	@Mock
	private Cookie cookie;

	@Mock
	private HttpServletResponse response;

	@Mock
	private HttpServletRequest request;

	@Before
	public void setUp() throws DaoException {
		MockitoAnnotations.initMocks(this);
		AbstractDaoTest.initialize();
		when(request.getParameter("userName")).thenReturn(USER);
		when(request.getParameter("email")).thenReturn(MAIL);
		when(request.getParameter("password")).thenReturn(PASS);
		when(request.getParameter("firstName")).thenReturn(USER);
		when(request.getParameter("lastName")).thenReturn(USER);
		when(request.getParameter("age")).thenReturn(AGE);
	}

	@Test
	public void testDoPostValid() {
		new RegistrationController().doPost(request, response);
		verify(response, times(2)).addCookie(any(cookie.getClass()));
	}
}