package com.epam.task2.entities;

import com.epam.task2.Idable;

import java.util.Date;

/**
 * Created by Yuriy Krishtop on 08.05.2016.
 */
public class Metadata implements Idable {

    private int id;
    private int userId;
    private int cookieId;
    private Date timeAdded;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCookieId() {
        return cookieId;
    }

    public void setCookieId(int cookieId) {
        this.cookieId = cookieId;
    }

    public Date getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "id=" + id +
                ", userId=" + userId +
                ", cookieId=" + cookieId +
                ", timeAdded=" + timeAdded +
                '}';
    }
}
