package com.epam.model;

import java.util.Date;

/**
 * Created by Olga Kramska on 10-May-16.
 */
public class MetaData {
    private int id;
    private int cookieId;
    private int userId;
    private Date timeAdded;

    public MetaData(int id, int cookieId, int userId, Date timeAdded) {
        this.id = id;
        this.cookieId = cookieId;
        this.userId = userId;
        this.timeAdded = timeAdded;
    }

    public MetaData(int cookieId, int userId, Date timeAdded) {
        this.cookieId = cookieId;
        this.userId = userId;
        this.timeAdded = timeAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCookieId() {
        return cookieId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getTimeAdded() {
        return timeAdded;
    }
}
