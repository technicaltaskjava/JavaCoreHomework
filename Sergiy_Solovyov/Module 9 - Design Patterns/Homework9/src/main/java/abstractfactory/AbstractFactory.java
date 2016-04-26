package abstractfactory;


import abstractfactory.vehicle.Vehicle;
import abstractfactory.plane.Plane;

/**
 * @author Sergey Solovyov
 */
public interface AbstractFactory {

     Vehicle createVehicle(VehicleType vehicleType);
     Plane createPlane(PlaneType planeType);

}
