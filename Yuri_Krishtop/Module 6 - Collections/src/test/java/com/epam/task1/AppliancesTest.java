package com.epam.task1;

import com.epam.task1.appliances.Appliance;
import com.epam.task1.appliances.Controller;
import com.epam.task1.appliances.KitchenMachine;
import com.epam.task1.appliances.Oven;
import com.epam.task1.appliances.cleaner.DishwasherMachine;
import com.epam.task1.appliances.cleaner.WashingMachine;
import com.epam.task1.appliances.cooler.AirConditioner;
import com.epam.task1.appliances.cooler.Refrigerator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yuriy Krishtop on 03.04.2016.
 */
public class AppliancesTest {

    @Test
    public void testAppliances() {
        Refrigerator refrigerator = new Refrigerator("Nord", 700, 210, "freon", 41, 2, 230);
        AirConditioner airCond = new AirConditioner("Aukia", 880, 300, "R410A", 43, 2.6, 2640);
        Oven oven = new Oven("LG", 800, 90, 23);
        DishwasherMachine dishwasherMachine = new DishwasherMachine("Bosch", 1020, 360, 11.7, 4, 12);
        WashingMachine washingMachine = new WashingMachine("WHIRLPOOL", 750, 255, 45, 18, 6, 1000);
        KitchenMachine kitchenMachine = new KitchenMachine("Kenwood", 1100, 600, 4.6, "aluminium");
        Controller applController = new Controller(refrigerator, airCond, dishwasherMachine, washingMachine);
        applController.add(oven);
        applController.add(kitchenMachine);
        List<Appliance> appliances = applController.getAppliances();
        assertEquals(700, appliances.get(0).getPower());
        assertEquals(600, appliances.get(5).getPrice());
        List<Appliance> appliancesSortByPower = applController.sortByPower();
        assertEquals(700, appliancesSortByPower.get(0).getPower());
        assertEquals(1100, appliancesSortByPower.get(5).getPower());
        List<Appliance> appliancesSortByPrice = applController.sortByPrice();
        assertEquals(90, appliancesSortByPrice.get(0).getPrice());
        assertEquals(600, appliancesSortByPrice.get(5).getPrice());
        refrigerator.switchOn();
        washingMachine.switchOn();
        oven.switchOn();
        assertEquals(2250, applController.powerCalculator());
        refrigerator.switchOff();
        assertEquals(1550, applController.powerCalculator());
        List<Appliance> appliancesGetByPrice = applController.getByPriceRange(250, 290);
        assertEquals(255, appliancesGetByPrice.get(0).getPrice());
        List<Appliance> appliancesGetByPower = applController.getByPowerRange(1000, 1050);
        assertEquals(1020, appliancesGetByPower.get(0).getPower());
    }
}
