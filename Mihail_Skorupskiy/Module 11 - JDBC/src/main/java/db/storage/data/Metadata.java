package db.storage.data;

import db.storage.DataObject;
import db.storage.DataTypes;

import java.sql.Timestamp;

public class Metadata implements DataObject {
    private int cookieId;
    private String userId;
    private Timestamp timeAdded;

    public Metadata(int cookieId, String userId, Timestamp timeAdded) {
        this.cookieId = cookieId;
        this.userId = userId;
        this.timeAdded = timeAdded;
    }

    public int getCookieId() {
        return cookieId;
    }

    public void setCookieId(int cookieId) {
        this.cookieId = cookieId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Timestamp timeAdded) {
        this.timeAdded = timeAdded;
    }

    @Override
    public String get(){
        return "\nCookie ID:" + cookieId + "\nUser: " + userId + "\nTime added: " + timeAdded;
    }

    @Override
    public DataTypes getType(){
        return DataTypes.METADATA;
    }
}
