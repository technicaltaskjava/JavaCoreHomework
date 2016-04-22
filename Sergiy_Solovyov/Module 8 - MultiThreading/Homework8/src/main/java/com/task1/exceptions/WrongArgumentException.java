package com.task1.exceptions;

/**
 * @author Sergey Solovyov
 */
public class WrongArgumentException extends Exception {
    public WrongArgumentException(String message){
        super(message);
    }
}
