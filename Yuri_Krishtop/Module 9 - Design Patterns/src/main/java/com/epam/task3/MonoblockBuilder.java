package com.epam.task3;

/**
 * Created by Yuriy Krishtop on 25.04.2016.
 */
public class MonoblockBuilder extends ComputerBuilder {

    @Override
    public void buildMotherboard() {
        computer.setMotherboard("Dell O23I3410DIW-35");
    }

    @Override
    public void buildCpu() {
        computer.setCpu("Intel Core i3-6100U 2.3Ghz");
    }

    @Override
    public void buildRam() {
        computer.setRam("Goodram 4GB DDR3L SODIMM 1600MHz GR1600S3V64L11S/4G");
    }

    @Override
    public void buildVideo() {
        computer.setVideo("Intel HD Graphics 520");
    }

    @Override
    public void buildHdd() {
        computer.setHdd("Kingston HyperX Savage SSD 480GB SHSS37A/480G");
    }
}
