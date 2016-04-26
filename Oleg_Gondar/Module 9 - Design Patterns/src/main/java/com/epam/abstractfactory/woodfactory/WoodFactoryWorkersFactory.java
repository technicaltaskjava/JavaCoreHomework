package com.epam.abstractfactory.woodfactory;

import com.epam.abstractfactory.abstractions.Engineer;
import com.epam.abstractfactory.abstractions.Manager;
import com.epam.abstractfactory.abstractions.WorkersFactory;
import com.epam.abstractfactory.abstractions.Workman;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class WoodFactoryWorkersFactory extends WorkersFactory {
    @Override
    public Workman crateWorkman() {
        return new WoodFactoryWorkman();
    }

    @Override
    public Engineer createEngineer() {
        return new WoodFactoryEngineer();
    }

    @Override
    public Manager createManager() {
        return new WoodFactoryManager();
    }
}
