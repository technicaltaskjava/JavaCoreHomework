package com.kokhanyuk.javase05;


import com.kokhanyuk.javase05.compareutils.Car;
import com.kokhanyuk.javase05.myjunit.test.TestAnnotationAnalyzer;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * TuplesTest
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
@SuppressWarnings("ALL")
public class TuplesTest {

    static Logger logger = Logger.getLogger(TestAnnotationAnalyzer.class.getName());

    @Test
    public void testUnit() {

        Tuples.Unit<Integer> unit1 = Tuples.unit(2);
        Tuples.Unit<String> unit2 = Tuples.unit("Mers");
        Tuples.Unit<Car> unit3 = Tuples.unit(new Car("Mers", 1250));
        int i = unit1.getFirst();
        assertEquals(i, 2);
        String s = unit2.getFirst();
        Car car = unit3.getFirst();
        try {
            assertEquals(s, "Mers");
            assertEquals(car, new Car("Mers", 1250));
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }

    @Test
    public void testPair() {

        Tuples.Pair<Car, String> pair = Tuples.pair(new Car("Caddy", 15000), "Kokhanyuk");
        Car car = pair.getFirst();
        String name = pair.getSecond();
        try {
            assertEquals(car, new Car("Caddy", 15000));
            assertEquals(name, "Kokhanyuk");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }

    @Test
    public void testTriplet() throws Exception {

        Car[] showCase = new Car[6];
        showCase[0] = new Car("Mers", 11500);
        showCase[1] = new Car("Audi", 14000);
        showCase[2] = new Car("BMW", 18000);
        showCase[3] = new Car("Opel", 12200);
        showCase[4] = new Car("Ford", 16300);
        showCase[5] = new Car("Opel", 11000);
        Tuples.Triplet<Car[], Car, Integer> triplet = Tuples.triplet(showCase, showCase[0], 10);
        Car[] checkCase = triplet.getFirst();
        Car car = triplet.getSecond();
        int i = triplet.getThirt();
        try {
            assertEquals(checkCase, showCase);
            assertEquals(car, showCase[0]);
            assertEquals(i, 10);
        }catch (Exception e){
            logger.log(Level.SEVERE, e.toString());
        }
    }
}