package com.epam.mydao.exeptions;


public class DAOConfigExcp  extends RuntimeException{
    public DAOConfigExcp(String message) {
        super(message);
    }
    public DAOConfigExcp(String message, Throwable cause) {
        super(message, cause);
    }
}
