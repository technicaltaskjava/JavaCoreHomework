package javase.dao.transfer;

import java.sql.Date;

/**
 * Cookie Transfer Object class
 * Created by Yury Vislobodsky on 07.05.2016.
 */
public class Cookie {
    private int id;
    private String cookieName;
    private String message;
    private Date expired;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookie(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }
}
