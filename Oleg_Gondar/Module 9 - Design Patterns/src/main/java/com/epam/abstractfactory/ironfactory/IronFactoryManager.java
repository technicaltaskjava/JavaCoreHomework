package com.epam.abstractfactory.ironfactory;

import com.epam.abstractfactory.abstractions.Manager;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class IronFactoryManager implements Manager {
    public void manage() {
        System.out.println("IronFactoryManager is managing!");
    }
}
