package javase.t02.dao.transfer;

import java.sql.Timestamp;

/**
 * Metadata Transfer Object class
 * Created by Yury Vislobodsky on 07.05.2016.
 */
public class Metadata {
    private int id;
    private int usersId;
    private int cookiesId;
    private Timestamp timeAdded;
    private boolean enabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public int getCookiesId() {
        return cookiesId;
    }

    public void setCookiesId(int cookiesId) {
        this.cookiesId = cookiesId;
    }

    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Timestamp timeAdded) {
        this.timeAdded = timeAdded;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
