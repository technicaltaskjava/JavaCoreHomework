package abstractfactory;


import abstractfactory.vehicle.Bus;
import abstractfactory.vehicle.Car;
import abstractfactory.vehicle.Vehicle;
import abstractfactory.vehicle.Bicycle;

import abstractfactory.plane.Plane;

import java.util.NoSuchElementException;

/**
 * @author Sergey Solovyov
 */
public class VehicleFactory implements AbstractFactory {
    @Override
    public Vehicle createVehicle(VehicleType vehicleType) {
        Vehicle vehicle;

        switch (vehicleType) {
            case CAR:
                vehicle = new Car();
                break;
            case BUS:
                vehicle = new Bus();
                break;
            case BICYCLE:
                vehicle = new Bicycle();
                break;
            default:throw new NoSuchElementException();
        }
        return vehicle;
    }

    @Override
    public Plane createPlane(PlaneType planeType) {
        throw new UnsupportedOperationException();
    }
}
