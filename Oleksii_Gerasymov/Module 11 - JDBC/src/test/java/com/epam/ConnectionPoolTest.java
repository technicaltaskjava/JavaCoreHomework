package com.epam;

import com.epam.dao.DbConnectionPool;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

public class ConnectionPoolTest {

    @Test
    public void connectionPoolTest() {
        Logger log = Logger.getLogger(ConnectionPoolTest.class.getName());
        DbConnectionPool dbConnectionPool;
        try {
            dbConnectionPool = new DbConnectionPool();
        }
        catch (Exception poolException) {
            log.info(String.valueOf(poolException));
            return;
        }
        Connection c1;
        Connection c2;
        Connection c3;
        Connection c4;
        Connection c5;
        PreparedStatement pst1;
        PreparedStatement pst2;
        PreparedStatement pst3;
        PreparedStatement pst4;
        PreparedStatement pst5;
        ResultSet rs1;
        ResultSet rs2;
        ResultSet rs3;
        ResultSet rs4;
        ResultSet rs5;
        c1 = dbConnectionPool.getConnection();
        c2 = dbConnectionPool.getConnection();
        c3 = dbConnectionPool.getConnection();
        c4 = dbConnectionPool.getConnection();
        c5 = dbConnectionPool.getConnection();
        String sql1 = "select id from cookies where cookie like '%Demonstrate%'";
        String sql2 = "select id from cookies where cookie like '%Financial%'";
        String sql3 = "select id from users where password = 99999";
        String sql4 = "select id from users where username = 'boss'";
        String sql5 = "select username from users where id = 5";

        try {
            pst1 = c1.prepareStatement(sql1); //NOSONAR -- Connection will be closed by pool
            pst2 = c2.prepareStatement(sql2); //NOSONAR -- -"-
            pst3 = c3.prepareStatement(sql3); //NOSONAR -- -"-
            pst4 = c4.prepareStatement(sql4); //NOSONAR -- -"-
            pst5 = c5.prepareStatement(sql5); //NOSONAR -- -"-
            rs1 = pst1.executeQuery();
            rs2 = pst2.executeQuery();
            rs3 = pst3.executeQuery();
            rs4 = pst4.executeQuery();
            rs5 = pst5.executeQuery();
            while (rs1.next()) {
                Assert.assertEquals(7, rs1.getInt(1));
            }
            rs1.close();
            pst1.close();
            while (rs2.next()) {
                Assert.assertEquals(8, rs2.getInt(1));
            }
            rs2.close();
            pst2.close();
            while (rs3.next()) {
                Assert.assertEquals(9, rs3.getInt(1));
            }
            rs3.close();
            pst3.close();
            while (rs4.next()) {
                Assert.assertEquals(2, rs4.getInt(1));
            }
            rs4.close();
            pst4.close();
            while (rs5.next()) {
                Assert.assertEquals("user2", rs5.getString(1));
            }
            rs5.close();
            pst5.close();
        }
        catch (Exception poolException) {
            log.info(String.valueOf(poolException));
            return;
        }
        dbConnectionPool.destroy();
    }
}
