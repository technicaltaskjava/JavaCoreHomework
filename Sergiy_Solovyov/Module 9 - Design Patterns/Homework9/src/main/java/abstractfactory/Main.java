package abstractfactory;

import abstractfactory.plane.Plane;
import abstractfactory.vehicle.Vehicle;


/**
 * @author Sergey Solovyov
 */
public class Main {

    private Main(){}

    public static void main(String[] args) {
        AbstractFactory factory = new PlaneFactory();
        Plane plane = factory.createPlane(PlaneType.BOEING);
        plane.fly();
        factory = new VehicleFactory();
        Vehicle vehicle = factory.createVehicle(VehicleType.BUS);
        vehicle.move();
    }
}
