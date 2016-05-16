package com.epam.task2;

import com.epam.task2.entities.Cookie;
import com.epam.task2.entities.Metadata;
import com.epam.task2.entities.User;
import com.epam.task2.h2.H2DaoFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Yuriy Krishtop on 09.05.2016.
 */
public class DemoDao {

    protected static Logger log = Logger.getLogger(DemoDao.class);

    private DemoDao() {
    }

    public static void main() {
        DaoFactory factory = new H2DaoFactory();
        try {
            Connection connection = (Connection) factory.getContext();
            GenericDao dao = factory.getDao(connection, Cookie.class);
            LinkedList<Cookie> cookies = (LinkedList<Cookie>) dao.getAll();
            for(Cookie c : cookies) {
                System.out.println(c);
            }
            Cookie cookie = (Cookie) dao.getById(4);
            System.out.println("Selected cookie:");
            System.out.println(cookie);
            Cookie newCookie = new Cookie();
            newCookie.setPrediction("New prediction");
            Cookie updatedCookie = new Cookie();
            updatedCookie.setPrediction("Updated prediction");
            updatedCookie.setId(2);
            dao.create(newCookie);
            dao.delete(newCookie);
            dao.update(updatedCookie);
            System.out.println("After changing table Cookies...");
            LinkedList<Cookie> changedCookies = (LinkedList<Cookie>) dao.getAll();
            for(Cookie c : changedCookies) {
                System.out.println(c);
            }
        } catch (SQLException e) {
            log.error(e);
        }

        DaoFactory factoryUser = new H2DaoFactory();
        try {
            Connection connectionUser = (Connection) factoryUser.getContext();
            GenericDao daoUser = factoryUser.getDao(connectionUser, User.class);
            LinkedList<User> users = (LinkedList<User>) daoUser.getAll();
            for(User u : users) {
                System.out.println(u);
            }
            User user = (User) daoUser.getById(2);
            System.out.println("Selected user:");
            System.out.println(user);
            User updatedUser = new User();
            updatedUser.setLogin("login");
            updatedUser.setName("name");
            updatedUser.setId(3);
            updatedUser.setSurname("surname");
            updatedUser.setEmail("email@.com");
            updatedUser.setPass("password");
            updatedUser.setDob(null);
            updatedUser.setRegDate(null);
            updatedUser.setPhone(null);
            daoUser.update(updatedUser);
            User newUser = new User();
            newUser.setLogin("newlogin");
            newUser.setName("newname");
            newUser.setId(3);
            newUser.setSurname("newsurname");
            newUser.setEmail("newemail@.com");
            newUser.setPass("newpassword");
            newUser.setDob(null);
            newUser.setRegDate(null);
            newUser.setPhone(null);
            daoUser.create(newUser);
            System.out.println("After changing table Users...");
            LinkedList<User> changedUsers = (LinkedList<User>) daoUser.getAll();
            for(User u : changedUsers) {
                System.out.println(u);
            }

        } catch (SQLException e) {
            log.error(e);
        }

        DaoFactory factoryMetadata = new H2DaoFactory();
        try {
            Connection connectionMetadata = (Connection) factoryMetadata.getContext();
            GenericDao daoMetadata = factoryMetadata.getDao(connectionMetadata, Metadata.class);
            LinkedList<Metadata> metadataList = (LinkedList<Metadata>) daoMetadata.getAll();
            for(Metadata m : metadataList) {
                System.out.println(m);
            }
            Metadata selectedMetadata = (Metadata) daoMetadata.getById(2);
            System.out.println("Selected metadata:");
            System.out.println(selectedMetadata);
            Metadata newMetadata = new Metadata();
            java.sql.Date date = java.sql.Date.valueOf("2016-01-01");
            newMetadata.setUserId(5);
            newMetadata.setCookieId(5);
            newMetadata.setTimeAdded(date);
            Metadata updatedMetadata = new Metadata();
            updatedMetadata.setId(2);
            updatedMetadata.setUserId(2);
            updatedMetadata.setCookieId(2);
            updatedMetadata.setTimeAdded(date);
            Metadata delMetadata = new Metadata();
            delMetadata.setId(7);
            daoMetadata.create(newMetadata);
            daoMetadata.update(updatedMetadata);
            daoMetadata.delete(delMetadata);
            System.out.println("After changing table Metadata...");
            LinkedList<Metadata> changedMetadataList = (LinkedList<Metadata>) daoMetadata.getAll();
            for(Metadata m : changedMetadataList) {
                System.out.println(m);
            }
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
