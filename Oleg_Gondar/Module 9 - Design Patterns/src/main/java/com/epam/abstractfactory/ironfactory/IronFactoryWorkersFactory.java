package com.epam.abstractfactory.ironfactory;

import com.epam.abstractfactory.abstractions.Engineer;
import com.epam.abstractfactory.abstractions.Manager;
import com.epam.abstractfactory.abstractions.WorkersFactory;
import com.epam.abstractfactory.abstractions.Workman;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class IronFactoryWorkersFactory extends WorkersFactory {
    @Override
    public Workman crateWorkman() {
        return new IronFactoryWorkman();
    }

    @Override
    public Engineer createEngineer() {
        return new IronFactoryEngineer();
    }

    @Override
    public Manager createManager() {
        return new IronFactoryManager();
    }
}
