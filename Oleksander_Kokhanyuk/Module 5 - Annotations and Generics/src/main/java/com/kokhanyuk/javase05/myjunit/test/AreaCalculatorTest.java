package com.kokhanyuk.javase05.myjunit.test;

import com.kokhanyuk.javase05.myjunit.AreaCalculator;
import com.kokhanyuk.javase05.myjunit.NegativeRadiusException;


/**
 * AreaCalculatorTest
 * <p/>
 * This class contains methods for testing AreaCalculator
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */


public class AreaCalculatorTest {

    @Test(expected = NegativeRadiusException.class)
    public void testAreaRange() throws NegativeRadiusException {
        AreaCalculator calc = new AreaCalculator();
        calc.areaRange(-5);
    }

    @Test
    public void testAreaRectangle() throws AssertionError {
        AreaCalculator calc = new AreaCalculator();
        boolean rez = calc.areaRectangle(5, 8) == 40;
        assert rez : "Error calculation";
    }

    @Test(ignore = true)
    public void testAreaTriangle() throws AssertionError {
        AreaCalculator calc = new AreaCalculator();
        boolean rez= calc.areaTriangle(50, 18) == 450;
        assert rez : "Error calculation";
    }
}
