package javase.t01.testclass;

import javase.t01.annotation.Test;

/**
 * Test class with annotation using
 * Created by Yury on 23.03.2016.
 */
public class TestClass {
    private double register1;
    private double register2;
    private double result;
    private Operation operation;

    public TestClass(double register1, Operation operation, double register2) {
        this.register1 = register1;
        this.register2 = register2;
        this.operation = operation;
    }

    @Test()
    public double getResult() {
        return result;
    }

    @Test(ignore = true)
    public void reset() {
        register1 = 0;
        register2 = 0;
        operation = Operation.PLUS;
    }

    @Test
    @Override
    public String toString() {
        return String.format("Result: %6.3f %s %6.3f = %6.3f", register1, operation, register2, result);
    }

    @Test(expected = ArithmeticException.class)
    public void execute() throws Exception {
        result = operation.calculate(register1, register2);
        if (Double.isInfinite(result)) {
            throw new ArithmeticException("Result is infinite");
        }
    }
}
