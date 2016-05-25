package com.epam.dao.beans;

/**
 * Created by Oleg on 5/25/2016.
 */
public class UserBean {

    private String userName;
    private String email;
    private String password;

    public UserBean() {
        //
    }

    public UserBean(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserBean(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
