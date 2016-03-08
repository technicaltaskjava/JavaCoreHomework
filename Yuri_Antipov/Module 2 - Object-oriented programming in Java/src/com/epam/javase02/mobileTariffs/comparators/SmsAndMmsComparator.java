package com.epam.javase02.mobileTariffs.comparators;

import com.epam.javase02.mobileTariffs.allTariffs.MobileTariff;

import java.util.Comparator;

public class SmsAndMmsComparator implements Comparator<MobileTariff> {
    @Override
    public int compare(MobileTariff o1, MobileTariff o2) {
        return Double.compare(o1.getSmsAndMmsPerMonth(), o2.getSmsAndMmsPerMonth());
    }
}
