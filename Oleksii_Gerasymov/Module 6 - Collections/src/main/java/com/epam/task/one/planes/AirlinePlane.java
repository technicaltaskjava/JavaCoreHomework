package com.epam.task.one.planes;

public class AirlinePlane extends Plane {
    private final int planeCapacity;

    public AirlinePlane(String planeId, String planeModel, String planeType, Integer planeRange,
                        Integer planeCapacity) {
        super(planeId, planeModel, planeType, planeRange);
        this.planeCapacity = planeCapacity;
    }

    public int getPlaneCapacity() {
        return planeCapacity;
    }

    @Override
    public String outPlaneData() {
        return String.format("%-20s%-20s%-20s%-20s%-20s", this.getPlaneId(), this.getPlaneModel(),
                this.getPlaneType(), this.getPlaneRange(), this.getPlaneCapacity());
    }

    @Override
    public boolean equals(Object o) throws NullPointerException {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AirlinePlane)) {
            return false;
        }
        AirlinePlane airlinePlane = (AirlinePlane) o;
        return getPlaneCapacity() == airlinePlane.getPlaneCapacity();
    }

    @Override
    public int hashCode() {
        int result = getPlaneId().hashCode();
        result = 31 * result + getPlaneModel().hashCode();
        result = 31 * result + getPlaneType().hashCode();
        result = 31 * result + getPlaneRange().hashCode();
        return result;
    }
}