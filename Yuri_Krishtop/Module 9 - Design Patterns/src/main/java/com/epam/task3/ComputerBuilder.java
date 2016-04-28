package com.epam.task3;

/**
 * Created by Yuriy Krishtop on 25.04.2016.
 */
public abstract class ComputerBuilder {

    protected Computer computer;

    public Computer getComputer() {
        return computer;
    }

    public void createNewComputer() {
        computer = new Computer();
    }

    public abstract void buildCpu();

    public abstract void buildMotherboard();

    public abstract void buildRam();

    public abstract void buildHdd();

    public abstract void buildVideo();
}
