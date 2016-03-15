package com.epam.javase02.mobileTariffs.allTariffs.randomTariffs;

import com.epam.javase02.mobileTariffs.allTariffs.contractTariffs.BusinessTariff;

public class RandomBusinessTariff extends BusinessTariff {
    public RandomBusinessTariff(String tariffName, String accountType){
        super(tariffName, accountType);
    }
    public void setParameters() {
        int[] minutesAmountBusiness = {200, 290, 490};
        int[] megabytesPerMonthBusiness = {10_000, 20_000, 50_000};
        int[] smsAndMmsAmountBusiness = {200, 300, 500};
        int[] customerAmountBusiness = {17_951, 11_312, 8_740};

        int minutesPerMonth = minutesAmountBusiness[(int) (Math.random() * minutesAmountBusiness.length)];
        int megabytesPerMonth = megabytesPerMonthBusiness[(int) (Math.random() * megabytesPerMonthBusiness.length)];
        int smsAndMmsPerMonth = smsAndMmsAmountBusiness[(int) (Math.random() * smsAndMmsAmountBusiness.length)];
        int customerAmount = customerAmountBusiness[(int) (Math.random() * customerAmountBusiness.length)];

        setMinutesPerMonth(minutesPerMonth);
        setMegabytesPerMonth(megabytesPerMonth);
        setSmsAndMmsPerMonth(smsAndMmsPerMonth);
        setCustomerAmount(customerAmount);
    }
}
