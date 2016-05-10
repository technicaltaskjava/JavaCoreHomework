package daotask;

import daotask.dao.CookieDAO;
import daotask.dao.factory.DAOFactory;
import daotask.dao.UserDAO;
import daotask.entity.Cookie;
import daotask.entity.User;
import jdbctask.Constant;
import java.util.List;

/**
 * @author Sergey Solovyov
 */
public class Main {

    private Main() {
    }

    public static void main(String[] args) throws Exception {

        DAOFactory daoFactory = DAOFactory.getDAOFactory(1);
        UserDAO userDAO = daoFactory.getUserDAO();

        //UserDAO Demo

        printEvent("Users init table:");
        printList(userDAO.findAll());

        User user = new User();
        user.setUserName("Test1");
        user.setEmail("Test1@Test.Test");
        user.setPassword("Test1");
        user.setAge(50);
        userDAO.insertUser(user);

        printEvent("After insertion:");
        printList(userDAO.findAll());

        userDAO.deleteUser(1);

        printEvent("After removing (User ID = 1):");
        printList(userDAO.findAll());
        printEvent("User by ID:");
        System.out.println(userDAO.getUserById(4));

        printEvent("User by NAME:");
        System.out.println(userDAO.getUserByName("Bonif"));

        user.setId(10);
        user.setUserName("YYYYYYYY");
        user.setEmail("YYYYYY@YYYYY.YYYYY");
        user.setPassword("YYYYYYY");
        user.setAge(66);
        userDAO.updateUser(user);

        printEvent("After updating (User id = 10):");
        printList(userDAO.findAll());

         //CookieDAO Demo
        CookieDAO cookieDAO = daoFactory.getCookieDAO();
        printEvent("Cookie Init table:");
        printList(cookieDAO.findAll());

        printEvent("Cookie by ID:");
        System.out.println(cookieDAO.getCookieById(3));

        cookieDAO.deleteCookie(10);
        printEvent("After removing (Cookie ID = 10):");
        printList(cookieDAO.findAll());

        Cookie cookie = new Cookie();
        cookie.setCookie("TEST COOKIE");
        cookieDAO.insertCookie(cookie);
        printEvent("After insertion:");
        printList(cookieDAO.findAll());

        cookie.setId(1);
        cookie.setCookie("UPDATE");
        cookieDAO.updateCookie(cookie);
        printEvent("After updating (Cookie ID = 1):");
        printList(cookieDAO.findAll());

        daoFactory.destroyPool();
    }
    public static void printList(List objects){
        for (Object o : objects) {
            System.out.println(o);
        }
    }

    public static void printEvent(String event){
        System.out.println(Constant.SEPARATOR + "\n" +event+"\n");
    }

}
