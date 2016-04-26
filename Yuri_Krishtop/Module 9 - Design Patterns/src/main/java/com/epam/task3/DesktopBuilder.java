package com.epam.task3;

/**
 * Created by Yuriy Krishtop on 25.04.2016.
 */
public class DesktopBuilder extends ComputerBuilder {

    @Override
    public void buildCpu() {
        computer.setCpu("Intel Celeron G1620 2.7GHz/LGA1155/2MB (BX80637G1620)");
    }

    @Override
    public void buildMotherboard() {
        computer.setMotherboard("ASUS P8H61-M LE R2.0 (Intel H61, LGA1155)");
    }

    @Override
    public void buildRam() {
        computer.setRam("Goodram 4GB DDR3 1600MHz GR1600D364L11/4G");
    }

    @Override
    public void buildHdd() {
        computer.setHdd("Toshiba 500GB 7200rpm 32MB DT01ACA050");
    }

    @Override
    public void buildVideo() {
        computer.setVideo("ASUS GeForce GT 720 (GT720-SL-1GD3-BRK)");
    }
}
