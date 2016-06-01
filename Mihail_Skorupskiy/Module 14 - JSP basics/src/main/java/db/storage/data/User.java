package db.storage.data;

import db.storage.DataObject;
import db.storage.DataTypes;

public class User implements DataObject {
    private String username;
    private String email;
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String get(){
        return "\nUsername: " + username + "\nE-mail: " + email + "\nPassword: " + password;
    }

    @Override
    public DataTypes getType(){
        return DataTypes.USER;
    }

}
