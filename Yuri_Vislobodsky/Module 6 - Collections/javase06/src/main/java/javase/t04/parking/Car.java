package javase.t04.parking;

/**
 * Class Car
 * Created by Yury on 02.04.2016.
 */
public class Car {
    private String carNo;

    public Car(String carNo) {
        this.carNo = carNo;
    }

    public String getCarNo() {
        return carNo;
    }

    @Override
    public int hashCode() {
        return carNo.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                this.getClass() == other.getClass() &&
                this.carNo.equals(((Car) other).carNo);
    }

    @Override
    public String toString() {
        return getCarNo();
    }
}
