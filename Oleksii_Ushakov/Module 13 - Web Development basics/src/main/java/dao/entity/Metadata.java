package dao.entity;

import java.sql.Date;

/**
 * @author Alexey Ushakov
 */
public class Metadata {
    private final int cookieID;
    private final int userID;
    private Date timeAdded;

    public Metadata(int cookieID, int userID) {
        this.cookieID = cookieID;
        this.userID = userID;
    }

    public int getCookieID() {
        return cookieID;
    }

    public int getUserID() {
        return userID;
    }

    public Date getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Metadata metadata = (Metadata) o;

        if (cookieID != metadata.cookieID) {
            return false;
        }

        if (userID != metadata.userID) {
            return false;
        }

        return timeAdded.equals(metadata.timeAdded);
    }

    @Override
    public int hashCode() {
        int result = cookieID;
        result = 31 * result + userID;
        result = 31 * result + (timeAdded != null
                ? timeAdded.hashCode()
                : 0);
        return result;
    }
}
