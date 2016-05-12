package com.epam.task1;

import com.epam.AbstractDaoTest;
import com.epam.model.User;
import com.epam.task1.repository.UserDBRepository;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Olga Kramska on 10-May-16.
 */
public class UserDBRepositoryTest extends AbstractDaoTest {

    @Test
    public void testGetUser() throws SQLException {
        UserDBRepository userRepository = new UserDBRepository(dataSource().getConnection());
        User user = userRepository.getUser(1);
        assertNotNull(user);
        assertEquals("John", user.getUsername());
    }

    @Test
    public void testUpdateUser() throws SQLException {
        final String name = "Joe";
        UserDBRepository userRepository = new UserDBRepository(dataSource().getConnection());
        User user = userRepository.getUser(1);
        user.setUsername(name);
        userRepository.updateUser(user);
        assertEquals(name, userRepository.getUser(1).getUsername());
    }
}
