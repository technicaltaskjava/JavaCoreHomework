package com.epam.abstractfactory.woodfactory;

import com.epam.abstractfactory.abstractions.Manager;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class WoodFactoryManager implements Manager {
    public void manage() {
        System.out.println("WoodFactoryManager is managing!");
    }
}
