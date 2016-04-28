package abstractfactory;

import abstractfactory.plane.Boeing;
import abstractfactory.plane.Bomber;
import abstractfactory.plane.CargoPlane;
import abstractfactory.plane.Plane;
import abstractfactory.vehicle.Vehicle;

import java.util.NoSuchElementException;

/**
 * @author Sergey Solovyov
 */
public class PlaneFactory implements AbstractFactory{

    @Override
   public Plane createPlane(PlaneType planeType) {

        Plane plane;

        switch (planeType) {
            case BOEING:
                plane = new Boeing();
                break;
            case BOMBER:
                plane = new Bomber();
                break;
            case CARGO_PLANE:
                plane = new CargoPlane();
                break;
            default:throw new NoSuchElementException();
        }
        return plane;
    }

    @Override
    public Vehicle createVehicle(VehicleType vehicleType) {
        throw new UnsupportedOperationException();
    }
}
