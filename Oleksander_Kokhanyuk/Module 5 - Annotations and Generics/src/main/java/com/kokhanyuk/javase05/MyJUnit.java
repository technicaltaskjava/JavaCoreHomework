package com.kokhanyuk.javase05;

import com.kokhanyuk.javase05.myjunit.test.AreaCalculatorTest;
import com.kokhanyuk.javase05.myjunit.test.TestAnnotationAnalyzer;

/**
 * MyJUnit
 *
 * This class realizes the possibility of testing using annotations.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MyJUnit {

    public static void main(String[] args) throws Exception {
        TestAnnotationAnalyzer analyzer = new TestAnnotationAnalyzer();
        AreaCalculatorTest test=new AreaCalculatorTest();
        analyzer.analyz(test.getClass());
    }
}
