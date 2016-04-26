package com.epam.abstrFactory;

public class NoFoundOS extends NullPointerException {
    public NoFoundOS() {
        super("Unsupported OS ");
    }

    public NoFoundOS(String message) {
        super("Unsupported OS " + message);
    }
}
