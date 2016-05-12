package epam.com.task2;

import epam.com.task2.connectionpool.DataSource;
import epam.com.task2.cookie.CookieDAO;
import epam.com.task2.cookie.CookieDTO;
import epam.com.task2.metadata.MetadataDAO;
import epam.com.task2.metadata.MetadataDTO;
import epam.com.task2.user.UserDAO;
import epam.com.task2.user.UserDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Run {
    private Run() {
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        getReturnFromPull();

        workWithCookie();
        workWithUser();
        workWithMetadata();

    }

    public static void workWithMetadata() {
        MetadataDAO metadataDAO = new MetadataDAO();
        MetadataDTO metadataDTO = new MetadataDTO(33, 33, new Timestamp(new java.util.Date().getTime()));
        metadataDAO.insertData(metadataDTO);
        MetadataDTO metadata = metadataDAO.getData(metadataDTO);
        System.out.println(metadata.getTimestamp() + " " + metadata.getUserId() + " " + metadata.getCookieId());

        paintLine();
    }

    public static void workWithUser() {
        UserDAO userDAO = new UserDAO();
        UserDTO userDTO = new UserDTO(33, "TestUser", "12345678", "test@gmail.com");
        userDAO.insertData(userDTO);
        UserDTO user = userDAO.getData(userDTO);
        System.out.println(user.getUserName());

        paintLine();
    }

    public static void workWithCookie() {
        CookieDAO cookieDAO = new CookieDAO();
        CookieDTO cookieDTO = new CookieDTO("Updated111Txt", 5);
        cookieDAO.updateData(cookieDTO);
        CookieDTO cookie = cookieDAO.getData(cookieDTO);
        System.out.println(cookie.getCookie());
        cookieDAO.deleteData(cookieDTO);
        cookieDTO.setCookieId(33);
        cookieDTO.setCookie("!!!!!!!!!!!!!!!!!!!!");
        cookieDAO.insertData(cookieDTO);
        cookie = cookieDAO.getData(cookieDTO);
        System.out.println(cookie.getCookie());

        paintLine();
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
        paintLine();

    }

    public static void paintLine() {
        System.out.println("\n___________________________________________\n");
    }
}
