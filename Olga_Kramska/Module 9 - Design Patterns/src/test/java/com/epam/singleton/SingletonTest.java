package com.epam.singleton;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class SingletonTest {
    @Test
    public void testElvis() {
        Elvis elvis1 = Elvis.getElvis();
        Elvis elvis2 = Elvis.getElvis();
        assertEquals(elvis1, elvis2);
    }

    @Test
    public void testSingleton() {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        assertEquals(singleton1, singleton2);
    }
}
