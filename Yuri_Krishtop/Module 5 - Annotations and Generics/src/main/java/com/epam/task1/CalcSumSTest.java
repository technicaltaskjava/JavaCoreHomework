package com.epam.task1;

/**
 * Created by Yuriy Krishtop on 25.03.2016.
 */
public class CalcSumSTest {

    @Test()
    public void testSumNumbers() {
        Calculator myCalc;
        myCalc = new Calculator();
        assert 4 == myCalc.sumNumbers(1, 3);
    }
}
