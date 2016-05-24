package controller.servlet;

import dao.impl.AbstractDaoTest;
import exception.DaoException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CookieControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private PrintWriter writer;

    @Before
    public void setUp() throws DaoException {
        MockitoAnnotations.initMocks(this);
        AbstractDaoTest.initialize();
    }

    @Test
    public void testDoGet() throws ServletException {
        try {
            when(request.getParameter("id")).thenReturn("1");
            when(response.getWriter()).thenReturn(writer);
            new CookieController().doGet(request, response);
            verify(response).getWriter();
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }
}