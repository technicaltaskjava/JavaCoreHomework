package com.epam.task1;

/**
 * Created by Olga Kramska on 26-Mar-16.
 */
public class A {

    @Test(ignore = true)
    public void f() {
        System.out.println("f()...");
    }

    @Test(ignore = false)
    public void w() {
        System.out.println("w()...");
        assert 5 == sum(1, 3);
    }

    private int sum(int a, int b) {
        return a + b;
    }

    @Test(expected = IllegalArgumentException.class)
    public void g() {
        System.out.println("g()...");
        h(0);
    }

    private void h(int a) {
        if (a <= 0) {
            throw new IllegalArgumentException("wrong value of a: " + a);
        }
        System.out.println("a = " + a);
    }
}
