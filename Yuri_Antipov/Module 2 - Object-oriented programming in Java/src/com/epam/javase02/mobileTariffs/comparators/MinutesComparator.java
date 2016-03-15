package com.epam.javase02.mobileTariffs.comparators;

import com.epam.javase02.mobileTariffs.allTariffs.MobileTariff;

import java.util.Comparator;

/**
 * Created by Юра on 06 Mar 2016.
 */
public class MinutesComparator implements Comparator<MobileTariff> {
    @Override
    public int compare(MobileTariff o1, MobileTariff o2) {
        return Integer.compare(o1.getMinutesPerMonth(), o2.getMinutesPerMonth());
    }
}
