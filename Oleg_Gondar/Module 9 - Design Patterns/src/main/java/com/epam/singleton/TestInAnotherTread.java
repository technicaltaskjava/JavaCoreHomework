package com.epam.singleton;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class TestInAnotherTread extends Thread {
    @Override
    public void run() {
        System.out.println("Run tread");
        System.out.println("Get parameter from tread: " + CommonConfiguration.getInstance().getProperty("test"));
        CommonConfiguration.getInstance().setProperty("test1", "testPropertyValueFromTread");
    }
}
