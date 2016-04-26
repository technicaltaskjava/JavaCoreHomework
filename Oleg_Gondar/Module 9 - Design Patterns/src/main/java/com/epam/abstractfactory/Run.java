package com.epam.abstractfactory;

import com.epam.abstractfactory.abstractions.Engineer;
import com.epam.abstractfactory.abstractions.Manager;
import com.epam.abstractfactory.abstractions.WorkersFactory;
import com.epam.abstractfactory.abstractions.Workman;
import com.epam.abstractfactory.ironfactory.IronFactoryWorkersFactory;
import com.epam.abstractfactory.woodfactory.WoodFactoryWorkersFactory;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class Run {
    private Run(){}

    public static void main(String[] args) {
        createAndTestFactory(new IronFactoryWorkersFactory());
        createAndTestFactory(new WoodFactoryWorkersFactory());

    }

    public static void createAndTestFactory(WorkersFactory someWorkersFactory) {
        WorkersFactory workersFactory = someWorkersFactory;
        Workman workman = workersFactory.crateWorkman();
        Engineer engineer = workersFactory.createEngineer();
        Manager manager = workersFactory.createManager();

        workman.work();
        engineer.develop();
        manager.manage();
    }
}
