package com.epam.task3;

/**
 * Created by Yuriy Krishtop on 25.04.2016.
 */
public class LaptopBuilder extends ComputerBuilder {

    @Override
    public void buildMotherboard() {
        computer.setMotherboard("Intel Lynx Point-LP");
    }

    @Override
    public void buildHdd() {
        computer.setHdd("500 GB (WDC WD500LPVX-75V0TT0)");
    }

    @Override
    public void buildRam() {
        computer.setRam("4 GB DDR3-1600");
    }

    @Override
    public void buildVideo() {
        computer.setVideo("NVIDIA GeForce 820M (2 GB GDDR3)");
    }

    @Override
    public void buildCpu() {
        computer.setCpu("Intel Core i5-4510U: 1.7 Ghz");
    }
}
