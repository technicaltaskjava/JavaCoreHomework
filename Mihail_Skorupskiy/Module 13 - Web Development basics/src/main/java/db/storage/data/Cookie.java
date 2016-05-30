package db.storage.data;

import db.storage.DataObject;
import db.storage.DataTypes;

public class Cookie implements DataObject {
    private int id;
    private String message;

    public Cookie(int id, String message) {

        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String get(){
        return "\nID: " + id + "\nMessage: " + message;
    }

    @Override
    public DataTypes getType(){
        return DataTypes.COOKIE;
    }

}
