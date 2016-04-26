package com.epam.singleton;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SingletonTest {
    @Test
    public void singletonTest() {
        assertEquals(28.8f, EurSingletonEarlyCreation.getInstance().getRate());
        assertEquals(25.5f, UsdSingletonSynchronized.getInstance().getRate());
        assertEquals(0.3f, RubSingletonDoubleLocking.getInstance().getRate());
    }
}

