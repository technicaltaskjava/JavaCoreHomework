package com.kokhanyuk.servlets.login;

import java.io.Serializable;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class UserInfo implements Serializable {
    String userName;
    String errorText;
    boolean loginFlag;

    public String getUser() {
        return userName;
    }

    public void login(String theUser) {
        loginFlag = true;
        errorText = "";
        userName = theUser;
    }

    public void setError(String theText) {
        errorText = theText;
    }

    public String getError() {
        return errorText;
    }

    public String isLogin() {
        if (loginFlag) {
            return "true";
        }
        return "false";
    }

    public void logOut() {
        loginFlag = false;
        userName = "";
        errorText = "";
    }

    @Override
    public String toString() {
        return userName;
    }

}
