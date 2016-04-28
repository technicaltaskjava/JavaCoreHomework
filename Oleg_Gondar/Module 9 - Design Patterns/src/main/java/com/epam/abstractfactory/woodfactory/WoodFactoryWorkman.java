package com.epam.abstractfactory.woodfactory;

import com.epam.abstractfactory.abstractions.Workman;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class WoodFactoryWorkman implements Workman {
    public void work() {
        System.out.println("WoodFactoryWorkman is working!");
    }
}
