package epam.com.task2;

import epam.com.task2.connectionpool.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Run {
    private Run() {
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        getReturnFromPull();
        workWithDAOAndConnectionPoll();
    }

    public static void workWithDAOAndConnectionPoll() {
        CookieDAO cookieDAO = new CookieDAO();
        CookieDTO cookieDTO = new CookieDTO("Updated111Txt", 5);
        cookieDAO.updateData(cookieDTO);
        CookieDTO cookie = cookieDAO.getData(cookieDTO);
        System.out.println(cookie.getCookie());
        cookieDAO.deleteData(cookieDTO);
        cookieDTO.setCookie("InsertedText");
        cookieDAO.insertData(cookieDTO);
        cookie = cookieDAO.getData(cookieDTO);
        System.out.println(cookie.getCookie());
    }

    public static void getReturnFromPull() {
        Connection connection1 = DataSource.getConnection();
        Connection connection2 = DataSource.getConnection();
        Connection connection3 = DataSource.getConnection();
        Connection connection4 = DataSource.getConnection();
        Connection connection5 = DataSource.getConnection();

        DataSource.returnConnection(connection1);
        DataSource.returnConnection(connection2);
        DataSource.returnConnection(connection3);
        DataSource.returnConnection(connection4);
        DataSource.returnConnection(connection5);

    }
}
