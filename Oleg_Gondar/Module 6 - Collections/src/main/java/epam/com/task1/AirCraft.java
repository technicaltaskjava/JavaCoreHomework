package epam.com.task1;

/**
 * Created by O.Gondar on 05.04.2016.
 */
public class AirCraft {

    private Distances flyDistance;
    private int holdingCapacity;
    private int bearingCapacity;


    public AirCraft(Distances flyDistance, int holdingCapacity, int bearingCapacity) {
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

    public int getHoldingCapacity() {
        return holdingCapacity;
    }

    public void setHoldingCapacity(int holdingCapacity) {
        this.holdingCapacity = holdingCapacity;
    }

    public int getBearingCapacity() {
        return bearingCapacity;
    }

    public void setBearingCapacity(int bearingCapacity) {
        this.bearingCapacity = bearingCapacity;
    }


}
