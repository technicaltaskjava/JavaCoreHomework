package dao.accessor;

import dao.DAO;
import dao.entity.Cookie;
import dao.exeption.AddCookieException;
import dao.exeption.UpdateCookieException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author Alexey Ushakov
 */
public class CookiesAccessorTest {
    private static CookiesAccessor accessor = DAO.getInstance().getCookiesAccessor();
    private static Cookie testCookie;

    @BeforeClass
    public static void start() {
        Random random = new Random();
        int randomID = 1000 + random.nextInt(500);

        testCookie = new Cookie(randomID);
        testCookie.setPrice(2.0);
        testCookie.setDivination("testDivination");
        testCookie.setExpirationDate(new Date(1462827600000L));
    }

    @Test
    public void testGetCookieByID() {
        Cookie cookie = accessor.getCookieByID(1);

        assertEquals("You are a person of culture, cultivate it", cookie.getDivination());
    }

    @Test
    public void testGetCookieByDivination() {
        List<Cookie> cookieList = accessor.getCookieByDivination("Сookie that nobody buys");
        assertEquals(cookieList.get(0).getId(), 11);

    }

    @Test
    public void testGetCookieByExpirationDate() {
        accessor.addCookie(testCookie);
        List<Cookie> cookieList = accessor.getCookieByExpirationDate(testCookie.getExpirationDate());

        assertFalse(cookieList.isEmpty());
        assertEquals(cookieList.get(0).getExpirationDate(), testCookie.getExpirationDate());
    }

    @Test
    public void testGetCookieByPrice() {
        List<Cookie> cookieList = accessor.getCookieByPrice(1.0);

        assertTrue(cookieList.get(1).getPrice() == 1);
    }

    @Test
    public void testUpdateCookie() {
        Random random = new Random();
        int randomPrice = random.nextInt(20);

        Cookie updatedCookie = accessor.getCookieByID(1);
        updatedCookie.setPrice(randomPrice);

        accessor.updateCookie(updatedCookie);

        Cookie cookie = accessor.getCookieByID(1);
        assertTrue(cookie.getPrice() == randomPrice);
    }

    @Test(expected = UpdateCookieException.class)
    public void testUpdateCookieFail() {
        Cookie updatedCookie = accessor.getCookieByID(1);
        updatedCookie.setPrice(-1.00);

        accessor.updateCookie(updatedCookie);
    }

    @Test
    public void testAddCookie() {
        int beforeAdd = accessor.size();

        accessor.addCookie(testCookie);

        int afterAdd = accessor.size();

        assertTrue(beforeAdd < afterAdd);
    }

    @Test(expected = AddCookieException.class)
    public void testAddCookieFail() {
        Cookie cookie = new Cookie(1);

        accessor.addCookie(cookie);
    }

    @Test
    public void testDeleteCookie() {
        accessor.deleteCookieByID(testCookie.getId());
        accessor.addCookie(testCookie);

        int beforeDelete = accessor.getCookieList().size();

        accessor.deleteCookieByDivination("testDivination");

        int afterDelete = accessor.getCookieList().size();

        assertTrue(afterDelete < beforeDelete);
    }

    @AfterClass
    public static void end() {
        accessor.close();
    }
}