package com.epam.dao.beans;

/**
 * Created by Oleg on 5/25/2016.
 */
public class CookieBean {

    private int cookieId;
    private int cookie;

    public CookieBean() {
    }

    public CookieBean(int cookieId, int cookie) {
        this.cookieId = cookieId;
        this.cookie = cookie;
    }

    public int getCookie() {
        return cookie;
    }

    public void setCookie(int cookie) {
        this.cookie = cookie;
    }

    public int getCookieId() {
        return cookieId;
    }

    public void setCookieId(int cookieId) {
        this.cookieId = cookieId;
    }
}
