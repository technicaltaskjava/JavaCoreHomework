package com.epam.task1;

import static java.util.Arrays.sort;

/**
 * Created by Yuriy Krishtop on 24.03.2016.
 */
public class Calculator {

    public int sumNumbers(int a, int b) {
        return a + b;
    }

    public int difNumbers(int a, int b) {
        return a - b;
    }

    public int getArrayElement(int[] array, int ordinal) {
        sort(array);
        return array[ordinal];
    }

    public Object getInteger(int num) {
        if (num == 0) {
            return null;
        } else return num;
    }
}
