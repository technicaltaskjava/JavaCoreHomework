package com.epam.task3.version2;

/**
 * Created by Olga Kramska on 26-Mar-16.
 */
public class Unit<T> {
    private T element;

    private Unit(T element) {
        this.element = element;
    }

    public static <T> Unit create(T element) {
        return new Unit<>(element);
    }

    public T getElement() {
        return element;
    }
}
