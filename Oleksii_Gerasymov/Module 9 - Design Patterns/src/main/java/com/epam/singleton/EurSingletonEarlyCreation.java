package com.epam.singleton;

public class EurSingletonEarlyCreation {
    private static final EurSingletonEarlyCreation INSTANCE = new EurSingletonEarlyCreation();
    private float rate;

    private EurSingletonEarlyCreation() {
        this.rate = 28.8f;
    }

    public static EurSingletonEarlyCreation getInstance() {
        return INSTANCE;
    }

    public float getRate() {
        return rate;
    }
}
