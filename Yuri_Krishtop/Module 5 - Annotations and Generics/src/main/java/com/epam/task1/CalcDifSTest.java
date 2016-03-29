package com.epam.task1;


/**
 * Created by Yuriy Krishtop on 25.03.2016.
 */
public class CalcDifSTest {
    @Test
    public void testDifNumbers() {
        Calculator myCalc = new Calculator();
        assert -2 == myCalc.difNumbers(1, 3);
    }
}
