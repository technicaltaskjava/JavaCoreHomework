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
import java.io.IOException;

import static org.mockito.Mockito.*;

public class LoginControllerTest {
    private static final String USER = "Admin";
    private static final String PASS = "ff9830c42660c1dd1942844f8069b74a";

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Cookie cookie;

    @Before
    public void setUp() throws DaoException {
        MockitoAnnotations.initMocks(this);
        AbstractDaoTest.initialize();
        when(request.getParameter("username")).thenReturn(USER);
        when(request.getParameter("pass")).thenReturn(PASS);
    }

    @Test
    public void testDoPostValid() {
        new LoginController().doPost(request, response);
        verify(response, times(2)).addCookie(any(cookie.getClass()));
    }

    @Test
    public void testDoPostPassError() throws IOException {
        when(request.getParameter("pass")).thenReturn("");
        new LoginController().doPost(request, response);
        verify(response).sendError(anyInt());

    }

    @Test
    public void testDoPostUserError() throws IOException {
        when(request.getParameter("username")).thenReturn("");
        new LoginController().doPost(request, response);
        verify(response).sendError(anyInt());

    }
}