package javase05.t01.demo;

import javase05.t01.testclass.Operation;
import javase05.t01.testclass.TestClass;
import javase05.t01.tester.Tester;

/**
 * TesterDemo class runs analysis of TestClass with reflection
 * Created by Yury Vislobodsky on 24.03.2016.
 */
public class TesterDemo {
    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.Analyze(new TestClass(2, Operation.DIVIDE, 0));
    }
}
