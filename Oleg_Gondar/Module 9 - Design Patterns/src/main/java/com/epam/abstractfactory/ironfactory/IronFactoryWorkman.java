package com.epam.abstractfactory.ironfactory;

import com.epam.abstractfactory.abstractions.Workman;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class IronFactoryWorkman implements Workman {
    public void work() {
        System.out.println("IronFactoryWorkman is working!");
    }
}
