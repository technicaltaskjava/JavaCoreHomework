package com.kokhanyuk.javase05;

import com.kokhanyuk.javase05.compareutils.Car;
import com.kokhanyuk.javase05.compareutils.SortedByName;
import com.kokhanyuk.javase05.compareutils.SortedByPrice;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CompareUtilsTest
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class CompareUtilsTest {

    @Test
    public void testMax() throws Exception {
        CompareUtils<Integer> test = new CompareUtils();
        Integer[] array = new Integer[]{5, 8, 2, 4, 1};
        int i = test.max(array);
        assertEquals(i, 8);
    }

    @Test
    public void testMid() throws Exception {
        CompareUtils<Integer> test = new CompareUtils();
        Integer[] array = new Integer[]{5, 8, 2, 4, 1};
        int i = test.mid(array);
        assertEquals(i, 4);
    }

    @Test
    public void testMin() throws Exception {
        CompareUtils<Integer> test = new CompareUtils();
        Integer[] array = new Integer[]{5, 8, 2, 4, 1};
        int i = test.min(array);
        assertEquals(i, 1);
    }

    @Test
    public void testMax1() throws Exception {
        Car[] testCase = new Car[3];
        testCase[0] = new Car("C", 3);
        testCase[1] = new Car("B", 2);
        testCase[2] = new Car("A", 1);
        Car[] checkCase = new Car[3];
        checkCase[0] = new Car("C", 3);
        checkCase[1] = new Car("B", 2);
        checkCase[2] = new Car("A", 1);
        CompareUtils<Car> test = new CompareUtils();
        Car car = test.max(testCase, new SortedByName());
        assertEquals(car, checkCase[0]);
    }

    @Test
    public void testMid1() throws Exception {
        Car[] testCase = new Car[3];
        testCase[0] = new Car("B", 3);
        testCase[1] = new Car("A", 2);
        testCase[2] = new Car("C", 1);
        Car[] checkCase = new Car[3];
        checkCase[0] = new Car("B", 3);
        checkCase[1] = new Car("A", 2);
        checkCase[2] = new Car("C", 1);
        CompareUtils<Car> test = new CompareUtils();
        Car car = test.mid(testCase, new SortedByName());
        assertEquals(car, checkCase[0]);

    }

    @Test
    public void testMin1() throws Exception {
        Car[] testCase = new Car[3];
        testCase[0] = new Car("A", 3);
        testCase[1] = new Car("B", 2);
        testCase[2] = new Car("C", 1);
        Car[] checkCase = new Car[3];
        checkCase[0] = new Car("A", 3);
        checkCase[1] = new Car("B", 2);
        checkCase[2] = new Car("C", 1);
        CompareUtils<Car> test = new CompareUtils();
        Car car = test.min(testCase, new SortedByPrice());
        assertEquals(car, checkCase[2]);
    }
}