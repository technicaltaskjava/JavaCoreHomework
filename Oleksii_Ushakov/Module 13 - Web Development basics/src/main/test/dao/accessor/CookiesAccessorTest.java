package dao.accessor;

import dao.DAO;
import dao.entity.FortuneCookie;
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
    private static FortuneCookie testCookie;

    @BeforeClass
    public static void start() {
        Random random = new Random();
        int randomID = random.nextInt(500);

        testCookie = new FortuneCookie(randomID);
        testCookie.setPrice(2.0);
        testCookie.setPredication("testPredication");
        testCookie.setExpirationDate(new Date(1462827600000L));
    }

    @Test
    public void testGetCookieByID() {
        FortuneCookie cookie = accessor.getCookieByID(1);

        assertEquals("You are a person of culture, cultivate it", cookie.getPredication());
    }

    @Test
    public void testGetCookieByPredication() {
        List<FortuneCookie> cookieList = accessor.getCookieByPredication("You will be an inspiration to others");
        assertEquals(cookieList.get(0).getId(), 2);

    }

    @Test
    public void testGetCookieByExpirationDate() {
        accessor.addCookie(testCookie);
        List<FortuneCookie> cookieList = accessor.getCookieByExpirationDate(testCookie.getExpirationDate());

        assertFalse(cookieList.isEmpty());
        assertEquals(cookieList.get(0).getExpirationDate(), testCookie.getExpirationDate());
    }

    @Test
    public void testGetCookieByPrice() {
        List<FortuneCookie> cookieList = accessor.getCookieByPrice(1.0);

        assertTrue(cookieList.get(1).getPrice() == 1);
    }

    @Test
    public void testUpdateCookie() {
        Random random = new Random();
        int randomPrice = random.nextInt(20);

        FortuneCookie updatedCookie = accessor.getCookieByID(1);
        updatedCookie.setPrice(randomPrice);

        accessor.updateCookie(updatedCookie);

        FortuneCookie cookie = accessor.getCookieByID(1);
        assertTrue(cookie.getPrice() == randomPrice);
    }

    @Test(expected = UpdateCookieException.class)
    public void testUpdateCookieFail() {
        FortuneCookie updatedCookie = accessor.getCookieByID(1);
        updatedCookie.setPredication("fail");

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
        FortuneCookie cookie = new FortuneCookie(1);

        accessor.addCookie(cookie);
    }

    @Test
    public void testDeleteCookie() {
        accessor.deleteCookieByID(testCookie.getId());
        accessor.addCookie(testCookie);

        int beforeDelete = accessor.getCookieList().size();

        accessor.deleteCookieByPredication("testPredication");

        int afterDelete = accessor.getCookieList().size();

        assertTrue(afterDelete < beforeDelete);

//        accessor.deleteCookie();
    }

    @AfterClass
    public static void end() {
        accessor.close();
    }
}