package com.epam.dao.objects;

public class Cookie {
    private int id;
    private String cookieName;
    private boolean active;

    public Cookie(int id, String cookieName, boolean active) {
        this.id = id;
        this.cookieName = cookieName;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookie) {
        this.cookieName = cookie;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
