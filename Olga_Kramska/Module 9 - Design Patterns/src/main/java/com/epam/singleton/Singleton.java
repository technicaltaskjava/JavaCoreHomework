package com.epam.singleton;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class Singleton {

    private Singleton() {
    }

    private static class SingleHolder {
        private static final Singleton INSTANCE = new Singleton();

        private SingleHolder() {
        }
    }

    public static Singleton getInstance() {
        return SingleHolder.INSTANCE;
    }
}
