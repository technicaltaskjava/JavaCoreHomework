package com.kokhanyuk.javase05;

import com.kokhanyuk.javase05.compareutils.Car;
import com.kokhanyuk.javase05.compareutils.SortedByName;
import com.kokhanyuk.javase05.compareutils.SortedByPrice;
import com.kokhanyuk.javase05.myjunit.test.TestAnnotationAnalyzer;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * CompareUtilsTest
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class CompareUtilsTest {

    static Logger logger = Logger.getLogger(TestAnnotationAnalyzer.class.getName());

    @Test
    public void testMax() {
        CompareUtils<Integer> test = new CompareUtils();
        Integer[] array = new Integer[]{5, 8, 2, 4, 1};
        int i = test.max(array);
        try {
            assertEquals(i, 8);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error: ", e);
        }
    }

    @Test
    public void testMid() {
        CompareUtils<Integer> test = new CompareUtils();
        Integer[] array = new Integer[]{5, 8, 2, 4, 1};
        int i = test.mid(array);
        try {
            assertEquals(i, 4);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error: ", e);
        }
    }

    @Test
    public void testMin() {
        CompareUtils<Integer> test = new CompareUtils();
        Integer[] array = new Integer[]{5, 8, 2, 4, 1};
        int i = test.min(array);
        try {
            assertEquals(i, 1);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error: ", e);
        }
    }

    @Test
    public void testMax1() {
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
        try {
            assertEquals(car, checkCase[0]);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error: ", e);
        }
    }

    @Test
    public void testMid1() {
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
        try {
            assertEquals(car, checkCase[0]);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }

    @Test
    public void testMin1() {
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
        try {
            assertEquals(car, checkCase[2]);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }
}