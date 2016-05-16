package com.epam.task2;

import com.epam.task2.util.ConnectionManager;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class ConnectionManagerTest {

    @Test
    public void testOneInstanceConnectionManager() {
        assertSame(ConnectionManager.getInstance(), ConnectionManager.getInstance());
    }

    @Test
    public void testConnection() {
        ConnectionManager manager = ConnectionManager.getInstance();
        assertNotNull(manager.getConnection());
        assertNotNull(manager.getConnection());
    }
}
