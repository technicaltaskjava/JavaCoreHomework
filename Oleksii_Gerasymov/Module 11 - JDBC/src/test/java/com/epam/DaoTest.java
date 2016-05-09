package com.epam;

import com.epam.dao.dao.DaoImplement;
import com.epam.dao.objects.Cookie;
import com.epam.dao.objects.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DaoTest {
    @Test
    public void connectionPoolTest() {
        DaoImplement daoImplement = new DaoImplement();

        User currentUser = daoImplement.getUser(3);
        Assert.assertEquals("account", currentUser.getUserName());

        Cookie currentCookie = daoImplement.getCookie(3);
        Assert.assertEquals("Your trouble will pass away soon.", currentCookie.getCookieName());

        List<User> currentUserList = daoImplement.getUserByName("admin");
        Assert.assertEquals(1, currentUserList.get(0).getId());

        List<Cookie> currentCookieList = daoImplement.findCookieByText("Remember");
        Assert.assertEquals(6, currentCookieList.get(0).getId());

    }
}
