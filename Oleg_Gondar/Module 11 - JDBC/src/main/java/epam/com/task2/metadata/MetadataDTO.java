package epam.com.task2.metadata;

import java.sql.Timestamp;

public class MetadataDTO {

    private int userId;
    private int cookieId;
    private Timestamp timestamp;

    public MetadataDTO(int userId, int cookieId, Timestamp timestamp) {
        this.userId = userId;
        this.cookieId = cookieId;
        this.timestamp = timestamp;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
