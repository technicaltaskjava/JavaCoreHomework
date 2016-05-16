package javase.t02.demo;

import javase.common.connectionpool.ConnectionPoolException;
import javase.common.viewresultset.ViewResultSet;
import javase.t02.dao.datasource.*;
import javase.t02.dao.factory.DAOFactory;
import javase.t02.dao.factoryh2.H2DAOFactory;
import javase.t02.dao.transfer.*;

import java.sql.*;

/**
 * Demo class of Abstract Factory pattern implementation for DAO
 * Created by Yury Vislobodsky on 07.05.2016.
 */
public class DAODemo {
    private DAODemo() {
    }

    private static void usersDAODemo(UsersDAO users) throws SQLException {
        User user = new User();

        System.out.print("After insert user ID=20:");
        user.setId(20);
        user.setUserName("Barmaleykin");
        user.setPassword("123456");
        user.setEmail("barmaleykin@example.com");
        user.setYearOfBirth(1978);
        users.insert(user);
        ViewResultSet.view(users.selectAll());

        System.out.print("After update password of user ID=20:");
        user.setPassword("654321");
        users.update(user);
        ViewResultSet.view(users.selectAll());

        System.out.print("After delete user ID=20:");
        users.delete(user.getId());
        ViewResultSet.view(users.selectAll());

        System.out.print("After search users which names contain `ich`:");
        ViewResultSet.view(users.selectByName("ich"));
        users.closeResultSet();
    }

    private static void cookiesDAODemo(CookiesDAO cookies) throws SQLException {
        Cookie cookie = new Cookie();

        System.out.print("After insert cookie ID=20:");
        cookie.setId(20);
        cookie.setCookie("Cookie 20");
        cookie.setMessage("Well done is better than well said.");
        cookie.setExpired(Date.valueOf("2016-12-31"));
        cookies.insert(cookie);
        ViewResultSet.view(cookies.selectAll());

        System.out.print("After update message of cookie ID=20:");
        cookie.setMessage("Smile. Tomorrow is another day.");
        cookies.update(cookie);
        ViewResultSet.view(cookies.selectAll());

        System.out.print("After delete cookie ID=20:");
        cookies.delete(cookie.getId());
        ViewResultSet.view(cookies.selectAll());

        System.out.print("After search cookies which messages contain `your`:");
        ViewResultSet.view(cookies.selectByMessage("your"));
        cookies.closeResultSet();
    }

    private static void metadataDAODemo(MetadataDAO metadata) throws SQLException {
        Metadata md = new Metadata();

        System.out.print("After insert metadata ID=20:");
        md.setId(20);
        md.setUsersId(3);
        md.setCookiesId(5);
        md.setTimeAdded(Timestamp.valueOf("2016-12-31 23:59:59"));
        md.setEnabled(true);
        metadata.insert(md);
        ViewResultSet.view(metadata.selectAll());

        System.out.print("After update enabled value of metadata ID=20:");
        md.setEnabled(false);
        metadata.update(md);
        ViewResultSet.view(metadata.selectAll());

        System.out.print("After delete metadata ID=20:");
        metadata.delete(md.getId());
        ViewResultSet.view(metadata.selectAll());

        System.out.print("After search metadata of user ID=5:");
        ViewResultSet.view(metadata.selectByUserId(5));
        metadata.closeResultSet();
    }

    public static void main(String[] args) throws ConnectionPoolException, SQLException {
        DAOFactory daoFactory = new H2DAOFactory();
        Connection connection = daoFactory.getConnection();

        usersDAODemo(daoFactory.getUsersDAO(connection));
        cookiesDAODemo(daoFactory.getCookiesDAO(connection));
        metadataDAODemo(daoFactory.getMetadataDAO(connection));

        daoFactory.freeConnection(connection);
    }
}
