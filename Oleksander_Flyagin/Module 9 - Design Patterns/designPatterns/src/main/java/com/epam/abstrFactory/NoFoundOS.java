package com.epam.abstrfactory;

public class NoFoundOS extends NullPointerException {
    public NoFoundOS() {
        super("Unsupported os ");
    }

    public NoFoundOS(String message) {
        super("Unsupported os " + message);
    }
}
