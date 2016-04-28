package com.epam.singleton;

public class UsdSingletonSynchronized {
    private static UsdSingletonSynchronized uniqueInstance = new UsdSingletonSynchronized();
    private float rate;

    private UsdSingletonSynchronized() {
        this.rate = 25.5f;
    }

    public static synchronized UsdSingletonSynchronized getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new UsdSingletonSynchronized();
        return uniqueInstance;
    }

    public float getRate() {
        return rate;
    }
}
