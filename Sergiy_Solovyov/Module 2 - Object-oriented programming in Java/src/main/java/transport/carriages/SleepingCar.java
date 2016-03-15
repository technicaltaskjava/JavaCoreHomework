package transport.carriages;
/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 07.03.2016
 */
public class SleepingCar extends Carriage {

    private int guardQuantity;

    public SleepingCar(int age, double luggageTotalWeight, int passengerCapacity, String colour, int guardQuantity) {
        super(age, luggageTotalWeight, passengerCapacity, colour);
        this.guardQuantity = guardQuantity;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SleepingCar:\n{");
        stringBuilder.append(super.toString());
        stringBuilder.append(" guardQuantity=");
        stringBuilder.append(guardQuantity);
        stringBuilder.append("} \n");

        return  stringBuilder.toString();
    }
}