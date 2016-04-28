package com.epam.task3;

/**
 * Created by Yuriy Krishtop on 25.04.2016.
 */
public class ComputerDirector {
    private ComputerBuilder computerBuilder;

    public void setComputerBuilder(ComputerBuilder computerBuilder) {
        this.computerBuilder = computerBuilder;
    }

    public Computer getComputer() {
        return computerBuilder.getComputer();
    }

    public void constructComputer() {
        computerBuilder.createNewComputer();
        computerBuilder.buildCpu();
        computerBuilder.buildHdd();
        computerBuilder.buildMotherboard();
        computerBuilder.buildRam();
        computerBuilder.buildVideo();
    }
}
