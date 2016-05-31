package com.epam.dao.beans;

/**
 * Created by Oleg on 5/31/2016.
 */
public class CookieBean {
    private long id;
    private String cookie;

    public CookieBean() {
    }

    public CookieBean(long id, String cookie) {
        this.id = id;
        this.cookie = cookie;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
