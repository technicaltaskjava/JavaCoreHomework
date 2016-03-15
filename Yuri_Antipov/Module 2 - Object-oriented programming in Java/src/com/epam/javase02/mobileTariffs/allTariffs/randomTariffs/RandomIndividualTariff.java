package com.epam.javase02.mobileTariffs.allTariffs.randomTariffs;

import com.epam.javase02.mobileTariffs.allTariffs.contractTariffs.IndividualTariff;

public class RandomIndividualTariff extends IndividualTariff {
    public RandomIndividualTariff(String tariffName, String accountType) {
        super(tariffName, accountType);
    }
    public void setParameters() {
        int[] minutesAmountIndividual = {40, 100, 150};
        int[] megabytesPerMonthIndividual = {4000, 7000, 10_000};
        int[] smsAndMmsAmountIndividual = {40, 100, 150};
        int[] customerAmountIndividual = {52_350, 21_412, 18_741};

        int minutesPerMonth = minutesAmountIndividual[(int) (Math.random() * minutesAmountIndividual.length)];
        int megabytesPerMonth = megabytesPerMonthIndividual[(int) (Math.random() * megabytesPerMonthIndividual.length)];
        int smsAndMmsPerMonth = smsAndMmsAmountIndividual[(int) (Math.random() * smsAndMmsAmountIndividual.length)];
        int customerAmount = customerAmountIndividual[(int) (Math.random() * customerAmountIndividual.length)];

        setMinutesPerMonth(minutesPerMonth);
        setMegabytesPerMonth(megabytesPerMonth);
        setSmsAndMmsPerMonth(smsAndMmsPerMonth);
        setCustomerAmount(customerAmount);
    }
}
