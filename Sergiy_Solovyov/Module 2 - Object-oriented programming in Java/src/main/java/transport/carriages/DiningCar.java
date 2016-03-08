package transport.carriages;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 07.03.2016
 */
public class DiningCar extends Carriage {

    private String mainDish;
    private int cookQuantity;

    public DiningCar(int age, double luggageTotalWeight, int passengerCapacity, String colour, String mainDish, int cookQuantity) {
        super(age, luggageTotalWeight, passengerCapacity, colour);
        this.mainDish = mainDish;
        this.cookQuantity = cookQuantity;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DiningCar:\n{");
        stringBuilder.append(super.toString());
        stringBuilder.append(" cookQuantity=");
        stringBuilder.append(cookQuantity);
        stringBuilder.append(", mainDish=");
        stringBuilder.append(mainDish);
        stringBuilder.append("} \n");

        return  stringBuilder.toString();
    }

    public int getCookerQuantity() {
        return cookQuantity;
    }

    public void setCookerQuantity(int cookerQuantity) {
        this.cookQuantity = cookQuantity;
    }

    public String getMainDish() {
        return mainDish;
    }

    public void setMainDish(String mainDish) {
        this.mainDish = mainDish;
    }


}
