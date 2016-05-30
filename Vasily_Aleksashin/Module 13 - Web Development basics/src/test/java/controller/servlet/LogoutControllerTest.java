package controller.servlet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class LogoutControllerTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private Cookie cookie;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoPostInvalidate() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        new LogoutController().doPost(request, response);
        verify(session).invalidate();
    }

    @Test
    public void testDoPostAddCookie() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        when(request.getCookies()).thenReturn(new Cookie[]{cookie, cookie, cookie});
        when(cookie.getName()).thenReturn("auth", "name", "JSESSIONID");
        new LogoutController().doPost(request, response);
        verify(response, times(3)).addCookie(any(cookie.getClass()));
    }
}