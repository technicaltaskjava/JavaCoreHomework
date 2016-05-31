package servlets.helpers;

import dao.util.UserStatus;

import javax.servlet.http.Cookie;

/**
 * @author augustprime
 */
public class ServletHelper {

    private ServletHelper() {
    }

    public static boolean isUserRegistered(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("Status".equals(c.getName())) {
                    return UserStatus.fromString(c.getValue()) == UserStatus.REGISTERED;
                }
            }
        }
        return false;
    }
}
