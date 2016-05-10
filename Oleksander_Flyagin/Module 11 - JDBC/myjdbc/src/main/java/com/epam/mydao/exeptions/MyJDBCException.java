package com.epam.mydao.exeptions;

public class MyJDBCException extends RuntimeException {
    public MyJDBCException(String message) {
        super(message);
    }
    public MyJDBCException(Throwable cause) {
        super(cause);
    }

}
