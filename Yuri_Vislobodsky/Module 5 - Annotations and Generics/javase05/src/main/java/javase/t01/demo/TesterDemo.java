package javase.t01.demo;

import javase.t01.testclass.Operation;
import javase.t01.testclass.TestClass;
import javase.t01.tester.Tester;

/**
 * TesterDemo class runs analysis of TestClass with reflection
 * Created by Yury Vislobodsky on 24.03.2016.
 */
public class TesterDemo {
    private TesterDemo() {
    }

    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.analyze(new TestClass(2, Operation.DIVIDE, 0));
    }
}
