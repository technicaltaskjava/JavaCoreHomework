package db.storage.data;

import db.storage.DataObject;
import db.storage.DataTypes;

public class Cookie implements DataObject {
    private int id;
    private String name;
    private int price;
    private String message;

    public Cookie(int id, String name, int price, String message) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String get(){
        return "\nID: " + id + "\nCookie: " + name + "\nPrice: " + price + "\nMessage: " + message;
    }

    @Override
    public DataTypes getType(){
        return DataTypes.COOKIE;
    }

}
