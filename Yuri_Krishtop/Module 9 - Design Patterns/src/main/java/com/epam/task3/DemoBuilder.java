package com.epam.task3;

/**
 * Created by Yuriy Krishtop on 25.04.2016.
 */
public class DemoBuilder {

    private DemoBuilder() {
    }

    public static void main() {
        ComputerDirector computerDesktopDirector = new ComputerDirector();
        ComputerBuilder desktopBuilder = new DesktopBuilder();
        computerDesktopDirector.setComputerBuilder(desktopBuilder);
        computerDesktopDirector.constructComputer();
        Computer desktopComputer = computerDesktopDirector.getComputer();
        System.out.println(desktopComputer);

        ComputerDirector computerLaptopDirector = new ComputerDirector();
        ComputerBuilder laptopBuilder = new LaptopBuilder();
        computerLaptopDirector.setComputerBuilder(laptopBuilder);
        computerLaptopDirector.constructComputer();
        Computer laptopComputer = computerLaptopDirector.getComputer();
        System.out.println(laptopComputer);

        ComputerDirector computerMonoblockDirector = new ComputerDirector();
        ComputerBuilder monoblockBuilder = new MonoblockBuilder();
        computerMonoblockDirector.setComputerBuilder(monoblockBuilder);
        computerMonoblockDirector.constructComputer();
        Computer monoblockComputer = computerMonoblockDirector.getComputer();
        System.out.println(monoblockComputer);

    }
}
