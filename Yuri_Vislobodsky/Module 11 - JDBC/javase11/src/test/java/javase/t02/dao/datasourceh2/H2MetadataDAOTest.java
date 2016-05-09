package javase.t02.dao.datasourceh2;

import javase.common.connectionpool.ConnectionPoolException;
import javase.t02.dao.datasource.MetadataDAO;
import javase.t02.dao.factory.DAOFactory;
import javase.t02.dao.factoryh2.H2DAOFactory;
import javase.t02.dao.transfer.Metadata;
import org.junit.Assert;
import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Test for H2MetadataDAO
 * Checked a work of methods: insert, update, delete and getRecordCount
 * Created by Yury Vislobodsky on 08.05.2016.
 */
public class H2MetadataDAOTest {

    @Test
    public void testAll() throws ConnectionPoolException, SQLException {
        DAOFactory daoFactory = new H2DAOFactory();
        Connection connection = daoFactory.getConnection();
        MetadataDAO metadata = daoFactory.getMetadataDAO(connection);

        int recordCount = metadata.getRecordCount();

        Metadata md = new Metadata();
        md.setId(30);
        md.setUsersId(4);
        md.setCookiesId(2);
        md.setTimeAdded(Timestamp.valueOf("2017-01-31 23:59:59"));
        md.setEnabled(true);
        metadata.insert(md);
        Assert.assertEquals(recordCount + 1, metadata.getRecordCount());

        md.setEnabled(false);
        Assert.assertEquals(1, metadata.update(md));

        metadata.delete(md.getId());
        Assert.assertEquals(recordCount, metadata.getRecordCount());
    }
}