package com.epam.abstractfactory.abstractions;

/**
 * Created by o.gondar on 26.04.2016.
 */
public abstract class WorkersFactory {

    public abstract Workman crateWorkman();

    public abstract Engineer createEngineer();

    public abstract Manager createManager();

}
