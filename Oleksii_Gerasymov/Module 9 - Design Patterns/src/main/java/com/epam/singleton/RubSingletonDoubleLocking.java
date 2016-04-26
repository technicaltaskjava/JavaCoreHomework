package com.epam.singleton;

public class RubSingletonDoubleLocking {
    private static volatile RubSingletonDoubleLocking uniqueInstance = new RubSingletonDoubleLocking();
    private float rate;

    private RubSingletonDoubleLocking() {
        this.rate = 0.3f;
    }

    public static synchronized RubSingletonDoubleLocking getInstance() {
        if (uniqueInstance == null) {
            synchronized (RubSingletonDoubleLocking.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new RubSingletonDoubleLocking();
                }
            }
        }
        return uniqueInstance;
    }

    public float getRate() {
        return rate;
    }
}
