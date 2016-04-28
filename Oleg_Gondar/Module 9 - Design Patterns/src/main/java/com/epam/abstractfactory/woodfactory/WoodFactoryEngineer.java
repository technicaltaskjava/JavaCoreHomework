package com.epam.abstractfactory.woodfactory;

import com.epam.abstractfactory.abstractions.Engineer;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class WoodFactoryEngineer implements Engineer {
    public void develop() {
        System.out.println("WoodFactoryEngineer is developing!");
    }
}
