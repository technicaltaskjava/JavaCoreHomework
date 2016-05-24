package daolayer.dao;

import daolayer.entity.Cookie;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Sergey Solovyov
 */
public interface CookieDAO {

    List<Cookie> findAll() throws SQLException;
    Cookie getCookieById(int id) throws SQLException;
    int insertCookie(Cookie cookie) throws SQLException;
    int deleteCookie(int id) throws SQLException;
    int updateCookie(Cookie cookie) throws SQLException;
    int size() throws SQLException;

}
