package javase.t02.dao.datasourceh2;

import javase.common.connectionpool.ConnectionPoolException;
import javase.t02.dao.datasource.CookiesDAO;
import javase.t02.dao.factory.DAOFactory;
import javase.t02.dao.factoryh2.H2DAOFactory;
import javase.t02.dao.transfer.Cookie;
import org.junit.Assert;
import org.junit.Test;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Test for H2CookiesDAO
 * Checked a work of methods: insert, update, delete and getRecordCount
 * Created by Yury Vislobodsky on 08.05.2016.
 */
public class H2CookiesDAOTest {

    @Test
    public void testAll() throws ConnectionPoolException, SQLException {
        DAOFactory daoFactory = new H2DAOFactory();
        Connection connection = daoFactory.getConnection();
        CookiesDAO cookies = daoFactory.getCookiesDAO(connection);

        int recordCount = cookies.getRecordCount();

        Cookie cookie = new Cookie();
        cookie.setId(30);
        cookie.setCookie("Cookie 30");
        cookie.setMessage("You have an ambitious nature and may make a name for yourself.");
        cookie.setExpired(Date.valueOf("2017-01-01"));
        cookies.insert(cookie);
        Assert.assertEquals(recordCount + 1, cookies.getRecordCount());

        cookie.setMessage("Do not be hasty, prosperity will knock on your door soon.");
        Assert.assertEquals(1, cookies.update(cookie));

        cookies.delete(cookie.getId());
        Assert.assertEquals(recordCount, cookies.getRecordCount());
    }
}