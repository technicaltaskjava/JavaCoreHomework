package task2;

import java.sql.SQLException;
import java.util.List;

public class Run {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CookieDAO cookieDAO = new CookieDAO();
        cookieDAO.updateCookie("UpdatedTxt", 4);
        Cookie cookie = cookieDAO.getCookie(4);
        System.out.println(cookie.getCookie());
        cookieDAO.deleteCookie(4);
        cookieDAO.insertCookie("InsertedText", 4);
        System.out.println(cookieDAO.getCookie(4).getCookie());
    }
}
