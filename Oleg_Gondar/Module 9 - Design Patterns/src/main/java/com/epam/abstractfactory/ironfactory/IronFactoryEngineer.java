package com.epam.abstractfactory.ironfactory;

import com.epam.abstractfactory.abstractions.Engineer;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class IronFactoryEngineer implements Engineer {
    public void develop() {
        System.out.println("IronFactoryEngineer is developing!");
    }
}
