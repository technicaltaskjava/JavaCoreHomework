package com.epam.task1.appliances;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.*;

/**
 * Created by Yuriy Krishtop on 02.04.2016.
 */
public class Controller<T extends Appliance> {

    private List<Appliance> appliances;

    public Controller(Appliance... appliances) {
        this.appliances = new ArrayList(appliances.length);
        for (Appliance a : appliances) {
            this.appliances.add(a);
        }
    }

    public void add(T appliance) {
        this.appliances.add(appliance);
    }

    public List<Appliance> sortByPower() {
        Collections.sort(appliances);
        return appliances;
    }

    public List<Appliance> sortByPrice() {
        Comparator<Appliance> comp = new Comparator<Appliance>() {
            @Override
            public int compare(Appliance o1, Appliance o2) {
                return o1.getPrice() - o2.getPrice();
            }
        };
        Collections.sort(appliances, comp);
        return appliances;
    }

    public int powerCalculator() {
        int powerSum = 0;
        for (Appliance a : appliances) {
            if (a.isSwitched()) {
                powerSum += a.getPower();
            }
        }
        return powerSum;
    }

    public List<Appliance> getByPowerRange(int minPower, int maxPower) {
        List<Appliance> appliancesPowerRange = new ArrayList();
        for (Appliance a : appliances) {
            if ((a.getPower() >= minPower) && (a.getPower() <= maxPower)) {
                appliancesPowerRange.add(a);
            }
        }
        return appliancesPowerRange;
    }

    public List<Appliance> getByPriceRange(int minPrice, int maxPrice) {
        List<Appliance> appliancesPriceRange = new ArrayList();
        for (Appliance a : appliances) {
            if ((a.getPrice() >= minPrice) && (a.getPrice() <= maxPrice)) {
                appliancesPriceRange.add(a);
            }
        }
        return appliancesPriceRange;
    }

    public List<Appliance> getAppliances() {
        return appliances;
    }
}
