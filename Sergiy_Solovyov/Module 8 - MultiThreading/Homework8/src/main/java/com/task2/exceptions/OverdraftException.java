package com.task2.exceptions;

/**
 * @author Sergey Solovyov
 */
public class OverdraftException extends Exception {

    public OverdraftException(String message){
        super(message);
    }
    public OverdraftException(String message, Throwable cause){
        super(message, cause);
    }
}
