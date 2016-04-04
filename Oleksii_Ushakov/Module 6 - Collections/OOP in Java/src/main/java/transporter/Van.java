package transporter;

import coffee.CoffeePacking;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexey Ushakov
 */
public class Van {
    private final int transportableWeight;
    private int freeWeight;
    private List<CoffeePacking> coffeePacking = new LinkedList<>();

    public Van(int weight) {
        this.transportableWeight = weight;
        this.freeWeight = weight;
    }

    public int getTransportableWeight() {
        return transportableWeight;
    }

    public int getTransportableWorkload() {
        return transportableWeight - freeWeight;
    }

    public int getFreeWeight() {
        return freeWeight;
    }

    public boolean canAddCoffeePacking(CoffeePacking packing) {
        return freeWeight >= packing.getWeigh();
    }

    public void addCoffeePacking(CoffeePacking packing) {
        if (canAddCoffeePacking(packing)) {
            freeWeight -= packing.getWeigh();
            coffeePacking.add(packing);
        } else {
            throw new VanOverloadException("Van overloaded. Free weight is " + freeWeight);
        }
    }

    public void removeCoffeePacking(CoffeePacking packing) {
        coffeePacking.remove(packing);
    }

    public List<CoffeePacking> getCoffeePacking() {
        return coffeePacking;
    }

    public List<CoffeePacking> findByWeight(int weight) {
        List<CoffeePacking> searchResult = new LinkedList<>();

        for (CoffeePacking current : coffeePacking) {
            if (current.getWeigh() == weight) {
                searchResult.add(current);
            }
        }

        return searchResult;
    }

    public void sortByPrice() {
        Collections.sort(coffeePacking);
    }
}
