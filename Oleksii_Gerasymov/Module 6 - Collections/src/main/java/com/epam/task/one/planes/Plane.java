package com.epam.task.one.planes;

public class Plane implements Comparable<Plane> {
    private final String planeId;
    private final String planeModel;
    private final String planeType;
    private final Integer planeRange;

    protected Plane(String planeId, String planeModel, String planeType, Integer planeRange) {
        this.planeId = planeId;
        this.planeModel = planeModel;
        this.planeType = planeType;
        this.planeRange = planeRange;
    }

    public String getPlaneId() {
        return planeId;
    }

    public String getPlaneModel() {
        return planeModel;
    }

    public String getPlaneType() {
        return planeType;
    }

    public Integer getPlaneRange() {
        return planeRange;
    }

    public String outPlaneData() {
        return String.format("%-20s%-20s%-20s%-20s", this.getPlaneId(),this.getPlaneModel(),
                this.getPlaneType(), this.getPlaneRange());
    }

    @Override
    public int compareTo(Plane anotherPlane) {
        int anotherPlaneRange = anotherPlane.getPlaneRange();
        return this.getPlaneRange() - anotherPlaneRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Plane)) {
            return false;
        }
        Plane plane = (Plane) o;
        if (!getPlaneId().equals(plane.getPlaneId())) {
            return false;
        }
        if (!getPlaneModel().equals(plane.getPlaneModel())) {
            return false;
        }
        if (!getPlaneType().equals(plane.getPlaneType())) {
            return false;
        }
        return getPlaneRange().equals(plane.getPlaneRange());
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
