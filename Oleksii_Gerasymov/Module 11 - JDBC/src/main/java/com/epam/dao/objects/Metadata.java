package com.epam.dao.objects;

public class Metadata {
    private int userId;
    private int cookieId;
    private String timeAdded;

    public Metadata(int userId, int cookieId, String timeAdded) {
        this.userId = userId;
        this.cookieId = cookieId;
        this.timeAdded = timeAdded;
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

    public String getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(String timeAdded) {
        this.timeAdded = timeAdded;
    }
}
