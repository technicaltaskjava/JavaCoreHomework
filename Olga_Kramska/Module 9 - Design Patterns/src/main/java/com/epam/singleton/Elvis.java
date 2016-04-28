package com.epam.singleton;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class Elvis {
    private static volatile Elvis elvis;

    private Elvis() {
    }

    public static Elvis getElvis() {
        if (elvis == null) {
            synchronized (Elvis.class) {
                if (elvis == null) {
                    elvis = new Elvis();
                }
            }
        }
        return elvis;
    }
}
