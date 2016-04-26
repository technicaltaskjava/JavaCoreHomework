package com.epam.singleton;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class Run {
    private Run() {
    }

    public static void main(String[] args) throws InterruptedException {

        CommonConfiguration.getInstance().setProperty("test", "testPropertyValue");
        System.out.println(CommonConfiguration.getInstance().getProperty("test"));
        TestInAnotherTread testInAnotherTread = new TestInAnotherTread();
        testInAnotherTread.start();
        testInAnotherTread.join();
        System.out.println(CommonConfiguration.getInstance().getProperty("test1"));


    }
}
