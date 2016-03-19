package com.epam.javase03.task02.Exception;

public class KeyUnknownException extends Exception {
    public KeyUnknownException() {}
    public KeyUnknownException(String gripe) {
        super(gripe);
    }
}
