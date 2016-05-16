package javase.t02.dao.datasourceh2;

import javase.common.connectionpool.ConnectionPoolException;
import javase.t02.dao.datasource.UsersDAO;
import javase.t02.dao.factory.DAOFactory;
import javase.t02.dao.factoryh2.H2DAOFactory;
import javase.t02.dao.transfer.User;
import org.junit.Assert;
import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Test for H2UsersDAO
 * Checked a work of methods: insert, update, delete and getRecordCount
 * Created by Yury Vislobodsky on 08.05.2016.
 */
public class H2UsersDAOTest {

    @Test
    public void testAll() throws ConnectionPoolException, SQLException {
        DAOFactory daoFactory = new H2DAOFactory();
        Connection connection = daoFactory.getConnection();
        UsersDAO users = daoFactory.getUsersDAO(connection);

        int recordCount = users.getRecordCount();

        User user = new User();
        user.setId(30);
        user.setUserName("Tester");
        user.setPassword("gkfkks32");
        user.setEmail("tester@example.com");
        user.setYearOfBirth(1990);
        users.insert(user);
        Assert.assertEquals(recordCount + 1, users.getRecordCount());

        user.setPassword("hktrjed");
        Assert.assertEquals(1, users.update(user));

        users.delete(user.getId());
        Assert.assertEquals(recordCount, users.getRecordCount());
    }
}