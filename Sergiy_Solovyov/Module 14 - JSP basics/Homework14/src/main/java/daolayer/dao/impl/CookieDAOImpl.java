package daolayer.dao.impl;

import daolayer.dao.CookieDAO;
import daolayer.entity.Cookie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static daolayer.dao.Constant.*;

/**
 * @author Sergey Solovyov
 */
public class CookieDAOImpl implements CookieDAO {

    private int noOfRecords;
    private Connection connection;

    public CookieDAOImpl(Connection connection)  {
        this.connection = connection;
    }
    @Override
    public List<Cookie> findAll(int offset,
                                int noOfRecords) throws SQLException {
        List<Cookie> cookies = new ArrayList<>();
        Statement stmt = connection.createStatement();
        String query = "select * from COOKIES limit "
                + offset + ", " + noOfRecords;
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()){
            Cookie cookie = new Cookie();
            cookie.setId(rs.getInt(ID));
            cookie.setCookieText(rs.getString(COOKIE));
            cookies.add(cookie);
        }
        rs.close();
        rs = stmt.executeQuery("SELECT * FROM COOKIES");
        System.out.println(cookies);
        List<Cookie> cookies2 = new ArrayList<>();
        while (rs.next()){
            Cookie cookie = new Cookie();
            cookie.setId(rs.getInt(ID));
            cookie.setCookieText(rs.getString(COOKIE));
            cookies2.add(cookie);
        }
        this.noOfRecords = cookies2.size();
        rs.close();
        stmt.close();
        return cookies;
    }
    @Override
    public List<Cookie> find() throws SQLException {
        List<Cookie> cookies = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM COOKIES;");

        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Cookie cookie = new Cookie();
            cookie.setId(rs.getInt(ID));
            cookie.setCookieText(rs.getString(COOKIE));;
            cookies.add(cookie);
        }
        rs.close();
        ps.close();
        return cookies;
    }

    @Override
    public Cookie getCookieById(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM COOKIES WHERE ID =?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Cookie cookie = new Cookie();
        cookie.setId(rs.getInt(ID));
        cookie.setCookieText(rs.getString(COOKIE));
        rs.close();
        ps.close();
        return cookie;
    }

    @Override
    public int insertCookie(Cookie cookie) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO COOKIES (`cookie`) VALUES (?);");
        ps.setString(1, cookie.getCookieText());
        int count =  ps.executeUpdate();
        ps.close();
        return count;
    }

    @Override
    public int deleteCookie(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("DELETE FROM COOKIES WHERE id=?");
        ps.setInt(1, id);
        int count = ps.executeUpdate();
        ps.close();
        return count;
    }

    @Override
    public int updateCookie(Cookie cookie) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("UPDATE COOKIES SET `cookie`=? WHERE `id`=?;");
        ps.setString(1, cookie.getCookieText());
        ps.setInt(2, cookie.getId());
        int count =  ps.executeUpdate();
        ps.close();
        return count;
    }

    @Override
    public int size() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT count(*) FROM COOKIES");
        ResultSet rs = ps.executeQuery();
        int rowCount = 0;
        while(rs.next()) {
            rowCount = Integer.parseInt(rs.getString("count(*)"));
        }
        ps.close();
        return rowCount;
    }
    public int getNoOfRecords() {
        return noOfRecords;
    }
}
