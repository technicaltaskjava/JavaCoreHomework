package dao.accessor;

import dao.DAO;
import dao.entity.User;
import dao.exeption.AddUserException;
import dao.exeption.UpdateUserException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexey Ushakov
 */
public class UserAccessorTest {
    private static UserAccessor accessor = DAO.getInstance().getUserAccessor();
    private static User testUser;

    @BeforeClass
    public static void start() {
        Random random = new Random();
        int randomID = 1000 + random.nextInt(500);

        testUser = new User(randomID);
        testUser.setLogin("login");
        testUser.setFirstName("testFirst");
        testUser.setSecondName("testSecond");
        testUser.setPassword("testPas");
        testUser.setEmail("test@test.test");
        testUser.setRegDate(new Date(1462654800000L));
    }

    @Test
    public void testGetUserByID() {
        User user = accessor.getUserByID(1);

        assertEquals(user.getId(), 1);
    }

    @Test
    public void testGetUserList() {
        List<User> userList = accessor.getUserList();

        assertEquals(userList.size(), accessor.size());
    }

    @Test
    public void testGetUserByFirstName() {
        List<User> userList = accessor.getUserByFirstName("first_name01");

        assertEquals(userList.get(0).getFirstName(), "first_name01");
    }

    @Test
    public void testGetUserBySecondName() {
        List<User> userList = accessor.getUserBySecondName("second_name01");

        assertEquals(userList.get(0).getSecondName(), "second_name01");
    }

    @Test
    public void testGetUserByEmail() {
        List<User> userList = accessor.getUserByEmail("email02@gmail.com");

        assertEquals(userList.get(0).getEmail(), "email02@gmail.com");
    }

    @Test
    public void testUpdateUser() {
        Random random = new Random();
        String randomEmail = "email" + random.nextInt(50) + "@gmail.com";

        User user = accessor.getUserByID(1);
        user.setEmail(randomEmail);

        accessor.updateUser(user);

        List<User> userList = accessor.getUserByEmail(randomEmail);

        assertEquals(userList.get(0).getEmail(), randomEmail);
    }

    @Test(expected = UpdateUserException.class)
    public void testUpdateUserFail() {
        String randomEmail = "emailFail";

        User user = accessor.getUserByID(1);
        user.setEmail(randomEmail);

        accessor.updateUser(user); /*email not like '__%@%_.__%'*/
    }

    @Test
    public void testAddUser() {
        int beforeAdd = accessor.size();

        accessor.addUser(testUser);

        int afterAdd = accessor.size();

        assertTrue(beforeAdd < afterAdd);

    }

    @Test(expected = AddUserException.class)
    public void testAddUserFail() {
        User user = new User(1);
        accessor.addUser(user);
    }

    @Test
    public void testDeleteUser() {
        accessor.deleteUserByID(testUser.getId());

        accessor.addUser(testUser);

        int beforeDelete = accessor.getUserList().size();

        accessor.deleteUserByFirstName("testFirst");

        int afterDelete = accessor.getUserList().size();

        assertTrue(afterDelete < beforeDelete);
    }

    @AfterClass
    public static void end() {
        accessor.deleteUserByFirstName("test");
        accessor.close();
    }
}