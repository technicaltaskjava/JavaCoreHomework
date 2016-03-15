package transport.carriages;
/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 07.03.2016
 */
public class LuggageVan extends Carriage {

    private int quantityCells;

    public LuggageVan(int age, double luggageTotalWeight, int passengerCapacity, String colour,int quantityCells) {
        super(age, luggageTotalWeight, passengerCapacity, colour);
        this.quantityCells = quantityCells;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LuggageVan:\n{");
        stringBuilder.append(super.toString());
        stringBuilder.append(" quantityCells=");
        stringBuilder.append(quantityCells);
        stringBuilder.append("} \n");

        return  stringBuilder.toString();
    }

}
