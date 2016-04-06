package epam.com.task1;

/**
 * Created by O.Gondar on 05.04.2016.
 */
public class AirCraft {

    private Distances flyDistance;
    private double holdingCapacity;
    private double bearingCapacity;


    public AirCraft(Distances flyDistance, float holdingCapacity, float bearingCapacity) {
        this.flyDistance = flyDistance;
        this.holdingCapacity = holdingCapacity;
        this.bearingCapacity = bearingCapacity;
    }

    public Distances getFlyDistance() {
        return flyDistance;
    }

    public void setFlyDistance(Distances flyDistance) {
        this.flyDistance = flyDistance;
    }

    public double getHoldingCapacity() {
        return holdingCapacity;
    }

    public void setHoldingCapacity(float holdingCapacity) {
        this.holdingCapacity = holdingCapacity;
    }

    public double getBearingCapacity() {
        return bearingCapacity;
    }

    public void setBearingCapacity(float bearingCapacity) {
        this.bearingCapacity = bearingCapacity;
    }

    public void showAll(){
        System.out.println("FlyDistance " + flyDistance);
        System.out.println("HoldingCapacity " + holdingCapacity);
        System.out.println("BearingCapacity " + bearingCapacity);
    }
}
