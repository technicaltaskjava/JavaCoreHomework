package com.kokhanyuk.javase05.myjunit.test;

import com.kokhanyuk.javase05.myjunit.AreaCalculator;
import com.kokhanyuk.javase05.myjunit.NegativeRadiusException;

/**
 * AreaCalculatorTest
 *
 *This class contains methods for testing AreaCalculator
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */


public class AreaCalculatorTest {


    @Test(expected = NegativeRadiusException.class)
    public void testAreaRange() throws Exception {
        AreaCalculator calc = new AreaCalculator();
        calc.areaRange(-5);
    }

    @Test
    public void testAreaRectangle() throws Exception{
        AreaCalculator calc = new AreaCalculator();
        int i = calc.areaRectangle(5, 8);
        assert (i==40):"Error calculation";
    }

    @Test(ignore = true)
    public void testAreaTriangle() throws Exception {
        AreaCalculator calc = new AreaCalculator();
        int i = calc.areaRectangle(50, 18);
    }

}
