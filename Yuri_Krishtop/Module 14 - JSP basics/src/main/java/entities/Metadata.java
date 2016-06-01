package entities;

import dao.IdAble;

import java.sql.Timestamp;

/**
 * Created by Yuriy Krishtop on 01.06.2016.
 */
public class Metadata implements IdAble {
    int id;
    int userId;
    int cookieId;
    Timestamp timeAdded;

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

    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Timestamp timeAdded) {
        this.timeAdded = timeAdded;
    }
}
