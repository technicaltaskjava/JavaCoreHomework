package com.epam.singleton;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class Elvis {
    private static final Lock lockElvis = new ReentrantLock();

    private static volatile Elvis elvis;

    private Elvis() {
    }

    public static Elvis getElvis() {
        if (elvis == null) {
            lockElvis.lock();
            try {
                if (elvis == null) {
                    elvis = new Elvis();
                }
            } finally {
                lockElvis.unlock();
            }
        }
        return elvis;
    }
}
