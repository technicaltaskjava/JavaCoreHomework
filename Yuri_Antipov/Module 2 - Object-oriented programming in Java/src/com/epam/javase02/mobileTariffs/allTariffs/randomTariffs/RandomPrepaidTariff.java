package com.epam.javase02.mobileTariffs.allTariffs.randomTariffs;

import com.epam.javase02.mobileTariffs.allTariffs.prepaidTariffs.PrepaidTariff;

public class RandomPrepaidTariff extends PrepaidTariff {
    public RandomPrepaidTariff(String tariffName, String accountType) {
        super(tariffName, accountType);
    }
    public void setParameters() {
        int[] minutesAmountPrepaid = {30, 50, 90};
        int[] megabytesPerMonthPrepaid = {500, 2000, 5000};
        int[] smsAndMmsAmountPrepaid = {30, 50, 90};
        int[] customerAmountPrepaid = {152_350, 321_412, 28_741};

        int index1 = (int) (Math.random() * minutesAmountPrepaid.length);
        int minutesPerMonth = minutesAmountPrepaid[index1];
        int index2 = (int) (Math.random() * megabytesPerMonthPrepaid.length);
        int megabytesPerMonth = megabytesPerMonthPrepaid[index2];
        int index3 = (int) (Math.random() * smsAndMmsAmountPrepaid.length);
        int smsAndMmsPerMonth = smsAndMmsAmountPrepaid[index3];
        int index4 = (int) (Math.random() * customerAmountPrepaid.length);
        int customerAmount = customerAmountPrepaid[index4];

        setMinutesPerMonth(minutesPerMonth);
        setMegabytesPerMonth(megabytesPerMonth);
        setSmsAndMmsPerMonth(smsAndMmsPerMonth);
        setCustomerAmount(customerAmount);
    }
}
