package com.epam.mydao.user;


import java.io.Serializable;

public class Users implements Serializable {
    private int id;
    private String password;
    private String email;

    public Users(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
      return "[id " + getId()  + " " + "PASSWORD " + getPassword() + " " + "EMAIL " + getEmail() + "]";
    }
}
